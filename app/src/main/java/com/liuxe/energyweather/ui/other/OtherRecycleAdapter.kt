package com.liuxe.energyweather.ui.other

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean

class OtherRecycleAdapter(list: List<WeatherBean.WeathersBean>) :
    BaseQuickAdapter<WeatherBean.WeathersBean, BaseViewHolder>(
        R.layout.item_recycle_other,
        list
    ) {
    override fun convert(helper: BaseViewHolder?, item: WeatherBean.WeathersBean?) {
        var dateList = item?.date?.split("-")
        var dateStr = dateList?.get(1)+"/"+dateList?.get(2)
        var weekStr = item?.week?.replace("星期","周")

        helper?.setText(R.id.tv_date,dateStr)
        helper?.setText(R.id.tv_week,weekStr)
        helper?.setText(R.id.tv_temp_low,item?.temp_night_c+"°")
        helper?.setText(R.id.tv_temp_high,item?.temp_day_c+"°")

        helper?.setText(R.id.tv_weather,item?.weather)

        var  imgView = helper?.getView(R.id.iv_weather) as ImageView
        handleImgView(imgView,item?.weather)
    }

    private fun handleImgView(imgView: ImageView, weather: String?) {
        var imgSrc:Int? = 0
        when(weather){
            "晴" -> imgSrc = R.drawable.qing
            "多云" -> imgSrc = R.drawable.duoyun
            "阵雨" -> imgSrc = R.drawable.zhenyu
            "阴" -> imgSrc = R.drawable.yin
            "小雨","中雨","大雨","暴雨" -> imgSrc = R.drawable.yu
            "小雪","中雪","大雪","暴雪" -> imgSrc = R.drawable.xue
            "雾","霾" -> imgSrc = R.drawable.wu
            else -> imgSrc = R.drawable.yin
        }
        imgView.setImageResource(imgSrc)
    }
}