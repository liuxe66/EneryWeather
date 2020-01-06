package com.liuxe.energyweather

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.base.BaseVMActivity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentTransaction
import androidx.transition.TransitionInflater
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.liuxe.energyweather.ui.city.CityFragment
import com.liuxe.energyweather.ui.life.LifeFragment
import com.liuxe.energyweather.ui.other.OtherFragment
import com.liuxe.energyweather.ui.today.TodayFragment
import com.liuxe.energyweather.utils.SpUtils
import com.liuxe.energyweather.utils.StatusBarUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_life.*
import kotlinx.android.synthetic.main.fragment_other.*
import kotlinx.android.synthetic.main.fragment_today.*


class MainActivity : BaseVMActivity(), View.OnClickListener {


    private val NOW = 0
    private val OTHER = 1
    private val NUM = 2
    private val PIC = 3

    private var preIndex = 0
    private var nowIndex = 0

    private var set: TransitionSet? = null

    var mMainViewModel: MainViewModel? = null
    private var todayFragment: TodayFragment? = null
    private var otherFragment: OtherFragment? = null
    private var lifeFragment: LifeFragment? = null
    private var cityFragment: CityFragment? = null

    //声明AMapLocationClient类对象
    var mLocationClient: AMapLocationClient? = null
    //声明AMapLocationClientOption对象
    var mLocationOption: AMapLocationClientOption? = null

    var curPosition: Int = 0

    var alertDialog:AlertDialog? = null

    override fun getLayout() = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) {
        mMainViewModel = createViewModel()
        initView()
    }

    override fun onResume() {
        super.onResume()
        initLocation()
    }

    private fun initView() {
        alertDialog = locationDialog()
        initData()

        set = AutoTransition()
        set?.ordering = TransitionSet.ORDERING_TOGETHER
        set?.duration = 120L
        set?.interpolator = FastOutSlowInInterpolator()

        val statusLayoutParams: LinearLayout.LayoutParams =
            tv_status?.layoutParams as LinearLayout.LayoutParams
        statusLayoutParams.height = StatusBarUtils.getStatusBarHeight(this)

        todayFragment = TodayFragment()

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        ft.replace(R.id.fl_main, todayFragment!!)
        ft.commit()

        fl_now.setOnClickListener(this)
        fl_num.setOnClickListener(this)
        fl_other.setOnClickListener(this)
        fl_pic.setOnClickListener(this)
    }

    fun initData() {
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
                    Logger.e("定位信息 success：" + amapLocation.province + "," + amapLocation.city + "," + amapLocation.district + "," + amapLocation.street)
                    val city = amapLocation.getCity()
                    val cityId = mMainViewModel?.getCityId(city)
                    SpUtils.putString(App.CONTEXT, SpUtils.CUR_CITY_ID, cityId)
                    mLocationClient?.stopLocation()//停止定位后，本地定位服务并不会被销毁
                    initData()
                    if (alertDialog?.isShowing == true){
                        alertDialog?.dismiss()
                    }
                } else {
                    Logger.e(
                        "定位信息 Error, ErrCode:"
                                + amapLocation.errorCode + ", errInfo:"
                                + amapLocation.errorInfo
                    )
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    mLocationClient?.stopLocation()
                    if (alertDialog?.isShowing == false){
                        alertDialog?.show()
                    }
                }
            }
        }
        mLocationClient?.startLocation()
    }

    private fun locationDialog() : AlertDialog{

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
                 gotoCity(true)
                 dialogInterface.dismiss()
             }
             .create()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fl_now -> {
                gotoHome()
            }
            R.id.fl_other -> {
                nowIndex = 1
                if (preIndex != OTHER) {
                    reLayoutParams()

                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    if (otherFragment == null) {
                        otherFragment = OtherFragment()
                    }
                    otherFragment?.sharedElementEnterTransition = TransitionInflater.from(this)
                        .inflateTransition(android.R.transition.move)
                    switchShareElement(ft)
                    ft.replace(R.id.fl_main, otherFragment!!)
                    ft.commit()

                    preIndex = 1
                }
            }

            R.id.fl_num -> {
                nowIndex = 2
                if (preIndex != NUM) {
                    reLayoutParams()

                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    if (lifeFragment == null) {
                        lifeFragment = LifeFragment()
                    }
                    lifeFragment?.sharedElementEnterTransition = TransitionInflater.from(this)
                        .inflateTransition(android.R.transition.move)
                    switchShareElement(ft)
                    ft.replace(R.id.fl_main, lifeFragment!!)
                    ft.commit()

                    preIndex = 2
                }
            }

            R.id.fl_pic -> {
                gotoCity(false)
            }

        }
    }

    private fun gotoCity(boolean: Boolean) {
        nowIndex = 3
        if (preIndex != PIC) {
            reLayoutParams()

            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            if (cityFragment == null) {
                cityFragment = CityFragment()
            }
            cityFragment?.switchCityList(boolean)
            cityFragment?.sharedElementEnterTransition = TransitionInflater.from(this)
                .inflateTransition(android.R.transition.move)
            switchShareElement(ft)
            ft.replace(R.id.fl_main, cityFragment!!)
            ft.commit()


            preIndex = 3
        }
    }

    fun gotoHome() {
        nowIndex = 0
        if (preIndex != NOW) {
            reLayoutParams()

            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            if (todayFragment == null) {
                todayFragment = TodayFragment()
            }
            todayFragment?.sharedElementEnterTransition = TransitionInflater.from(this)
                .inflateTransition(android.R.transition.move)
            switchShareElement(ft)
            ft.replace(R.id.fl_main, todayFragment!!)
            ft.commit()

            preIndex = 0
        }
    }

    private fun switchShareElement(ft: FragmentTransaction): FragmentTransaction {
        return when (preIndex) {
            0 -> {
                ft.addSharedElement(
                    fl_today_share,
                    ViewCompat.getTransitionName(fl_today_share)!!
                )
            }
            1 -> {
                ft.addSharedElement(
                    fl_other_share,
                    ViewCompat.getTransitionName(fl_other_share)!!
                )
            }
            2 -> {
                ft.addSharedElement(
                    fl_life_share,
                    ViewCompat.getTransitionName(fl_life_share)!!
                )
            }
            3 -> {
                ft.addSharedElement(
                    fl_other_share,
                    ViewCompat.getTransitionName(fl_other_share)!!
                )
            }
            else -> ft

        }

    }

    private fun reLayoutParams() {

        val preframeLayout: FrameLayout = getFl(preIndex)
        val preLayoutParams: LinearLayout.LayoutParams =
            preframeLayout.layoutParams as LinearLayout.LayoutParams
        preLayoutParams.weight = 1.0f
        preframeLayout.layoutParams = preLayoutParams
        getTv(preIndex).visibility = View.GONE
        preframeLayout.getChildAt(0).setBackgroundResource(0)

        val curframeLayout: FrameLayout = getFl(nowIndex)
        val curLayoutParams: LinearLayout.LayoutParams =
            curframeLayout.layoutParams as LinearLayout.LayoutParams
        curLayoutParams.weight = 2.0f
        curframeLayout.layoutParams = curLayoutParams
        getTv(nowIndex).visibility = View.VISIBLE
        curframeLayout.getChildAt(0).setBackgroundResource(R.drawable.shape_weather_bottom_gray_bg)

        TransitionManager.beginDelayedTransition(ll_bottom_bar, set)
    }

    private fun getFl(index: Int): FrameLayout {
        return when (index) {
            NOW -> fl_now
            OTHER -> fl_other
            NUM -> fl_num
            PIC -> fl_pic
            else -> fl_now
        }
    }


    private fun getTv(index: Int): TextView {
        return when (index) {
            NOW -> tv_now
            OTHER -> tv_other
            NUM -> tv_num
            PIC -> tv_pic
            else -> tv_now
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient?.onDestroy()
    }
}
