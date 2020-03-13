package com.liuxe.energyweather.ui.today

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.liuxe.energyweather.MainActivity
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.base.BaseVMFragment
import com.liuxe.energyweather.bean.WeatherDetailsInfoBean
import com.liuxe.energyweather.utils.SpUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.item_viewpager_today.*

class TodayFragment : BaseVMFragment() {

    var mMainViewModel: MainViewModel? = null

    var mTodayRecycleAdapter: TodayRecycleAdapter? = null
    var mList: List<WeatherDetailsInfoBean.Weather3HoursDetailsInfosBean> = ArrayList()

    var mDotAdapter: TodayDotRecycleAdapter? = null
    var mWeatherList: List<WeatherBean> = ArrayList()

    var mVpAdapter: TodayVpAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_today
    }

    override fun init(savedInstanceState: Bundle?) {
        val context = activity as MainActivity
        mMainViewModel = context.mMainViewModel

        //每三小时天气
        mTodayRecycleAdapter = TodayRecycleAdapter(mList)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycle_today.layoutManager = layoutManager
        recycle_today.adapter = mTodayRecycleAdapter
        //城市指示器
        mDotAdapter = TodayDotRecycleAdapter(mWeatherList)
        val dotLayoutManager = LinearLayoutManager(activity)
        dotLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_vp_dot.layoutManager = dotLayoutManager
        recycler_vp_dot.adapter = mDotAdapter
        //天气viewpager

        mVpAdapter = TodayVpAdapter(mWeatherList)
        viewpager_today.adapter = mVpAdapter

        mMainViewModel?.weatherListResponse?.observe(this, Observer {
            refresh_layout.isRefreshing = false
            mDotAdapter?.setNewData(it)
            mVpAdapter?.setNewData(it)
            viewpager_today.currentItem = context.curPosition
        })

        mMainViewModel?.weatherInfoResponse?.observe(this, Observer {
            mTodayRecycleAdapter?.setNewData(it.weather3HoursDetailsInfos)
        })
        mMainViewModel?.weatherResponse?.observe(this, Observer {
            tv_city.text = it.city
        })

        viewpager_today.registerOnPageChangeCallback(PageChangeCallBack())
        refresh_layout.setColorSchemeColors(context.resources.getColor(R.color.color_93a8ff))
        refresh_layout.setOnRefreshListener {
            context.initData()
        }
    }

    inner class PageChangeCallBack :ViewPager2.OnPageChangeCallback(){

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val context = activity as MainActivity
            context.curPosition = position
            mMainViewModel?.handleMainResponse(position)
            recycle_today.scrollToPosition(0)
        }
    }

}