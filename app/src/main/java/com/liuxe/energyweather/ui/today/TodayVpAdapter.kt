package com.liuxe.energyweather.ui.today

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean


class TodayVpAdapter(list: List<WeatherBean>) :
    BaseQuickAdapter<WeatherBean, BaseViewHolder>(
        R.layout.item_viewpager_today,
        list
    ) {

    override fun convert(helper: BaseViewHolder?, item: WeatherBean?) {
        helper?.run {
            setText(R.id.tv_weather, item?.realtime?.weather)
            setText(R.id.tv_temp, item?.realtime?.temp + "°")
        }

        val lottisJson = handleLottie(item?.realtime?.weather)
        val lottieWeather = helper?.getView<LottieAnimationView>(R.id.lottie_weather)
        lottieWeather?.run {
            setAnimation(lottisJson)
            playAnimation()
        }
    }


    private fun handleLottie(weather: String?): String {
        return when (weather) {
            "晴" -> "qing.json"
            "多云" -> "duoyun.json"
            "阵雨" -> "zhenyu.json"
            "阴" -> "yin.json"
            "小雨", "中雨", "大雨", "暴雨" -> "yu.json"
            "小雪", "中雪", "大雪", "暴雪" -> "xue.json"
            "雾", "霾" -> "wu.json"
            else -> "wu.json"
        }

    }

}