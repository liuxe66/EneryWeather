package com.liuxe.energyweather.ui.weather


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.liuxe.energyweather.R
import com.liuxe.energyweather.base.BaseVMFragment
import com.liuxe.energyweather.bean.WeatherTypeBean
import com.liuxe.energyweather.utils.SpUtils
import com.loc.n
import com.loc.o
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * A simple [Fragment] subclass.
 */
class WeatherFragment(var position: Int) : BaseVMFragment() {
    var mMain2ViewModel: Main2ViewModel? = null
    var LayoutManager: GridLayoutManager? = null
    override fun getLayoutId() = R.layout.fragment_weather

    override fun init(savedInstanceState: Bundle?) {

        mMain2ViewModel = createViewModel()

        mMain2ViewModel?.mWeatherCitiesList?.observe(this, Observer {
            var mWeatherTypeAdapter = WeatherTypeAdapter(it[position].weatherTypeList)
            LayoutManager = GridLayoutManager(_mActivity, 4)
            recycler_weather.layoutManager = LayoutManager
            mWeatherTypeAdapter.bindToRecyclerView(recycler_weather)
        })

        swipe_refresh.setColorSchemeColors(requireActivity().resources.getColor(R.color.color_93a8ff))
        swipe_refresh.setOnRefreshListener {
            val locationCityid = SpUtils.getString(requireActivity(), SpUtils.CUR_CITY_ID, "101010100")
            val addCityId = SpUtils.getString(requireActivity(), SpUtils.ADD_CITY_ID, "")
            var cityids = locationCityid + addCityId
            Logger.e("cityids:$cityids")
            if (locationCityid == "" && addCityId != "") {
                cityids = addCityId.replaceFirst(",", "")
            }
            mMain2ViewModel?.getWeatherInfo(cityids, position)!!
        }
//        mMain2ViewModel?.mScrollY?.observe(requireActivity(), Observer {
//            Logger.e("=======scrollY=====" + it)
//            recycler_weather.scrollTo(0, it)
//        })
//
//        recycler_weather.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//                //大于0表示，正在向右滚动；小于等于0 表示停止或向左滚动
//                mMain2ViewModel?.mScrollY?.value = dy
//            }
//        })

    }

}
