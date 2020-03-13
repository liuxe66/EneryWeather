package com.liuxe.energyweather.ui.weather

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionSet
import androidx.viewpager2.widget.ViewPager2
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.liuxe.energyweather.App
import com.liuxe.energyweather.MainActivity
import com.liuxe.energyweather.R
import com.liuxe.energyweather.base.BaseVMActivity
import com.liuxe.energyweather.bean.WeatherCityBean
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.ui.today.TodayDotRecycleAdapter
import com.liuxe.energyweather.ui.today.TodayFragment
import com.liuxe.energyweather.utils.SpUtils
import com.liuxe.energyweather.utils.StatusBarUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.recycler_vp_dot
import kotlinx.android.synthetic.main.fragment_today.*

class Main2Activity : BaseVMActivity() {
    var mMainViewModel: Main2ViewModel? = null
    var mFragmentAdapter:WeatherFragmentAdapter? = null
    var mFramentList = ArrayList<WeatherFragment>()
    var mWeatherCityList = ArrayList<WeatherCityBean>()
    var mDotAdapter: CityDotAdapter? = null

    //声明AMapLocationClient类对象
    var mLocationClient: AMapLocationClient? = null
    //声明AMapLocationClientOption对象
    var mLocationOption: AMapLocationClientOption? = null

    var curPosition: Int = 0

    var alertDialog:AlertDialog? = null


    override fun getLayout() = R.layout.activity_main2

    override fun init(savedInstanceState: Bundle?) {
        val statusLayoutParams: LinearLayout.LayoutParams =
            tv_status2?.layoutParams as LinearLayout.LayoutParams
        statusLayoutParams.height = StatusBarUtils.getStatusBarHeight(this)
        mMainViewModel = createViewModel()
        initView()

        //城市指示器
        mDotAdapter = CityDotAdapter(mWeatherCityList)
        val dotLayoutManager = LinearLayoutManager(this)
        dotLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_vp_dot.layoutManager = dotLayoutManager
        recycler_vp_dot.adapter = mDotAdapter
        //viewpager2

        vp_weather.registerOnPageChangeCallback(PageChangeCallBack())

        mMainViewModel?.mPositionList?.observe(this, Observer {
            it.forEach {
                if (it.isSelected){
                    tv_weather_city.text = it.city
                }
            }
            mDotAdapter?.setNewData(it)
        })

        mMainViewModel?.mWeatherCitiesList?.observe(this, Observer {

            val fragmentList = ArrayList<WeatherFragment>()
            for (index in it.indices){
                fragmentList.add(WeatherFragment(index))
            }
            mFragmentAdapter = WeatherFragmentAdapter(this,fragmentList)
            vp_weather.adapter = mFragmentAdapter
        })

    }

    override fun onResume() {
        super.onResume()
        initLocation()
    }

    private fun initView() {
        alertDialog = locationDialog()
        initData()
    }

    private fun initData() {
        val locationCityid = SpUtils.getString(this, SpUtils.CUR_CITY_ID, "101010100")
        val addCityId = SpUtils.getString(this, SpUtils.ADD_CITY_ID, "")
        var cityids = locationCityid + addCityId
        Logger.e("cityids:$cityids")
        if (locationCityid == "" && addCityId != "") {
            cityids = addCityId.replaceFirst(",", "")
        }
        mMainViewModel?.getWeatherInfo(cityids, curPosition)!!
    }

    /**
     * 初始化定位
     */
    private fun initLocation() {
        //初始化定位
        mLocationClient = AMapLocationClient(this)
        //初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        //给定位客户端对象设置定位参数
        mLocationClient?.setLocationOption(mLocationOption)
        //设置定位回调监听
        mLocationClient?.setLocationListener { amapLocation ->
            if (amapLocation != null) {
                if (amapLocation!!.errorCode == 0) {
                    val city = amapLocation.getCity()
                    val cityId = mMainViewModel?.getCityId(city)
                    SpUtils.putString(App.CONTEXT, SpUtils.CUR_CITY_ID, cityId)
                    mLocationClient?.stopLocation()//停止定位后，本地定位服务并不会被销毁
                    initData()
                    if (alertDialog?.isShowing == true){
                        alertDialog?.dismiss()
                    }
                } else {

                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    mLocationClient?.stopLocation()
//                    if (alertDialog?.isShowing == false){
//                        alertDialog?.show()
//                    }
                }
            }
        }
        mLocationClient?.startLocation()
    }

    private fun locationDialog() : AlertDialog {

        return AlertDialog.Builder(this)
            .setMessage("开启定位信息！")
            .setCancelable(false)
            .setPositiveButton("去开启") { dialogInterface, i ->
                val setting = Intent()
                setting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                setting.action = "android.settings.LOCATION_SOURCE_SETTINGS"
                startActivity(setting)
                dialogInterface.dismiss()
            }
            .setNegativeButton("手动定位") { dialogInterface, i ->

                dialogInterface.dismiss()
            }
            .create()
    }
    inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            mMainViewModel?.handlePosition(position)
        }
    }
}
