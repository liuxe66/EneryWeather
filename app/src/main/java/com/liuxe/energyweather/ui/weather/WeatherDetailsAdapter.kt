package com.liuxe.energyweather.ui.weather

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.bean.WeatherDetailsInfoBean

class WeatherDetailsAdapter (list: List<WeatherDetailsInfoBean.Weather3HoursDetailsInfosBean>) :
    BaseQuickAdapter<WeatherDetailsInfoBean.Weather3HoursDetailsInfosBean, BaseViewHolder>(
        R.layout.item_recycle_details,
        list
    ) {
    override fun convert(
        helper: BaseViewHolder?,
        item: WeatherDetailsInfoBean.Weather3HoursDetailsInfosBean?
    ) {
        val time = item?.startTime?.split(" ")?.get(1)
        val hour = time?.split(":")?.get(0)
        helper?.run {
            setText(R.id.tv_time, "$hour:00")
            setText(R.id.tv_temp, item?.highestTemperature + "°")
            setText(R.id.tv_weather, item?.weather)
        }


        val imgView = helper?.getView(R.id.iv_pic) as ImageView
        handleImgView(imgView, item?.weather)

    }

    private fun handleImgView(imgView: ImageView, weather: String?) {
        val imgSrc: Int = when (weather) {
            "晴" -> R.drawable.qing
            "多云" -> R.drawable.duoyun_dark
            "阵雨" -> R.drawable.zhenyu_dark
            "阴" -> R.drawable.yin_dark
            "小雨", "中雨", "大雨", "暴雨" -> R.drawable.yu_dark
            "小雪", "中雪", "大雪", "暴雪" -> R.drawable.xue_dark
            "雾", "霾" -> R.drawable.wu_dark
            else -> R.drawable.yin_dark
        }
        imgView.setImageResource(imgSrc)
    }
}