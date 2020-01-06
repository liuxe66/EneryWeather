package com.liuxe.energyweather.ui.city


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.liuxe.energyweather.App
import com.liuxe.energyweather.MainActivity
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.CityBean
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.utils.SpUtils
import com.liuxe.energyweather.base.BaseVMFragment
import com.liuxe.energyweather.bean.WeatherBean
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_city.*


/**
 * A simple [Fragment] subclass.
 */
class CityFragment : BaseVMFragment(), BaseQuickAdapter.OnItemChildClickListener {

    var mWeatherList: List<WeatherBean> = ArrayList()
    var mCityList: List<CityBean> = ArrayList()
    var mMainViewModel: MainViewModel? = null
    var mCityRecycleAdapter: CityRecycleAdapter? = null
    var mCitySelectRecycleAdapter: CitySelectRecycleAdapter? = null
    var cityid: String = ""
    var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null
    override fun getLayoutId() = R.layout.fragment_city

    override fun init(savedInstanceState: Bundle?) {
        var context = activity as MainActivity
        mMainViewModel = context.mMainViewModel

        mCityList = mMainViewModel?.getCity()!!
//        cityid = SpUtils.getString(activity,"cityid","101180101")
        mCityRecycleAdapter = CityRecycleAdapter(mWeatherList)
        mCitySelectRecycleAdapter = CitySelectRecycleAdapter(mCityList)

        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recycle_weather.layoutManager = layoutManager
        recycle_weather.adapter = mCityRecycleAdapter
        mCityRecycleAdapter?.onItemChildClickListener = this@CityFragment

        var selectLayoutManager = LinearLayoutManager(activity)
        selectLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycle_city.layoutManager = selectLayoutManager
        recycle_city.adapter = mCitySelectRecycleAdapter
        mCitySelectRecycleAdapter?.onItemChildClickListener = this@CityFragment

        mMainViewModel?.mMainResponse?.observe(this, Observer {
            mWeatherList = it.value
            mCityRecycleAdapter?.setNewData(mWeatherList)
        })
        bottomSheetBehavior = BottomSheetBehavior.from(fl_other_share)
    }

    fun switchCityList(boolean: Boolean) = if (boolean)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    else
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED


    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (adapter) {
            is CityRecycleAdapter -> {
                when (view?.id) {
                    R.id.ll_city -> handleToHome(position)
                    R.id.iv_city -> {
                        if (position != 0) {
                            val cityIdCur = mWeatherList.get(position).cityid
                            Logger.e("cityIdCur" + cityIdCur)
                            val cityIdsOld = SpUtils.getString(App.CONTEXT, SpUtils.ADD_CITY_ID, "")
                            Logger.e("cityIdsOld" + cityIdsOld)
                            val cityIdsNew = cityIdsOld.replace("," + cityIdCur, "")
                            Logger.e("cityIdsNew" + cityIdsNew)
                            SpUtils.putString(App.CONTEXT, SpUtils.ADD_CITY_ID, cityIdsNew)
                            val context = activity as MainActivity
                            context.curPosition = 0
                            handleData()
                            adapter.notifyItemRemoved(position)
                        } else {
                            handleToHome(position)
                        }
                    }
                }

            }

            is CitySelectRecycleAdapter -> {
                val cityIdCur = mCityList.get(position).cityid


                if (mWeatherList.size == 5) {
                    Toast.makeText(activity, "最多添加五个城市", Toast.LENGTH_SHORT).show()
                    return
                }

                for (index in mWeatherList.indices) {
                    if (mWeatherList[index].cityid == cityIdCur) {
//                        handleToHome(index)
                        Toast.makeText(activity, "该城市已添加", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
                val cityIdsAdd = SpUtils.getString(App.CONTEXT, SpUtils.ADD_CITY_ID, "")
                val cityIdsNew = cityIdsAdd + "," + cityIdCur
                SpUtils.putString(App.CONTEXT, SpUtils.ADD_CITY_ID, cityIdsNew)

                handleToHomeWithData(cityIdsNew.split(",").size - 1)
            }
        }


    }

    private fun handleToHome(position: Int) {
        val context = activity as MainActivity
        context.curPosition = position
        mMainViewModel?.handleMainResponse(position)
        context.gotoHome()
    }

    private fun handleToHomeWithData(position: Int) {
        val context = activity as MainActivity
        context.curPosition = position
        context.gotoHome()
        context.initData()
    }

    private fun handleData() {
        val context = activity as MainActivity
        context.initData()
    }
}
