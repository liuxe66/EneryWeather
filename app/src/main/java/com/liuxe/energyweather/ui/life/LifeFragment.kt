package com.liuxe.energyweather.ui.life


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.liuxe.energyweather.MainActivity

import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.base.BaseVMFragment
import com.liuxe.energyweather.bean.IndexesBean
import kotlinx.android.synthetic.main.fragment_life.*
import kotlinx.android.synthetic.main.fragment_life.tv_city
import kotlinx.android.synthetic.main.fragment_today.*

/**
 * A simple [Fragment] subclass.
 */
class LifeFragment : BaseVMFragment() , BaseQuickAdapter.OnItemChildClickListener {


    var mMainViewModel: MainViewModel? = null
    var mLifeRecycleAdapter:LifeRecycleAdapter? = null
    var mList:List<IndexesBean> = ArrayList()

    override fun getLayoutId() = R.layout.fragment_life

    override fun init(savedInstanceState: Bundle?) {
        var context = activity as MainActivity
        mMainViewModel = context.mMainViewModel
        mLifeRecycleAdapter = LifeRecycleAdapter(mList)
        

        var layoutManager = GridLayoutManager(activity,4)
        recycle_life.layoutManager = layoutManager
        recycle_life.adapter = mLifeRecycleAdapter

        mMainViewModel?.lifeInfoResponse?.observe(this, Observer {
            mList = it
            mLifeRecycleAdapter?.setNewData(mList)
        })

        mMainViewModel?.realtimeResponse?.observe(this, Observer {
            tv_temp.text = it.temp + "°"
            tv_weather.text = it.weather
        })

        mMainViewModel?.weatherResponse?.observe(this, Observer {
            tv_city.text = it.city
        })
        tv_content.text = mMainViewModel?.lifeInfoResponse?.value?.get(1)?.content
        ll_life_content.visibility = View.VISIBLE

        mLifeRecycleAdapter?.setOnItemChildClickListener(this)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        mLifeRecycleAdapter?.handleClick(position)
        tv_content.text = mMainViewModel?.lifeInfoResponse?.value?.get(position)?.content
    }

}
