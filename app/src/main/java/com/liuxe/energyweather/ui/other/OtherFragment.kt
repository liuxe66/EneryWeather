package com.liuxe.energyweather.ui.other


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.liuxe.energyweather.MainActivity
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.ui.MainViewModel
import com.liuxe.energyweather.base.BaseVMFragment
import com.liuxe.energyweather.bean.WeathersBean
import kotlinx.android.synthetic.main.fragment_other.*
import kotlinx.android.synthetic.main.fragment_other.tv_city
import kotlinx.android.synthetic.main.fragment_today.*


/**
 * A simple [Fragment] subclass.
 */
class OtherFragment : BaseVMFragment() {

    var mMainViewModel: MainViewModel? = null
    var mOtherRecycleAdapter:OtherRecycleAdapter? = null
    var mList:List<WeathersBean> = ArrayList()

    override fun getLayoutId() = R.layout.fragment_other_copy

    override fun init(savedInstanceState: Bundle?) {
        var context = activity as MainActivity
        mMainViewModel = context.mMainViewModel
        mOtherRecycleAdapter = OtherRecycleAdapter(mList)

        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycle_other.layoutManager = layoutManager
        recycle_other.adapter = mOtherRecycleAdapter
//        var controller = LayoutAnimationController(AnimationUtils.loadAnimation(context,R.anim.recycle_other_anim))
//
//        recycle_other.layoutAnimation = controller


        mMainViewModel?.weathersResponse?.observe(this, Observer {
            mList = it
            mOtherRecycleAdapter?.setNewData(mList)
        })

//        mMainViewModel?.realtimeResponse?.observe(this, Observer {
//            tv_temp.text = it.temp + "°"
//            tv_weather.text = it.weather
//        })

        mMainViewModel?.weatherResponse?.observe(this, Observer {
            tv_city.text = it.city
        })
    }
}
