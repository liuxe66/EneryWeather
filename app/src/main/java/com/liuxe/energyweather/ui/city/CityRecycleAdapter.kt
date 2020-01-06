package com.liuxe.energyweather.ui.city

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.CityBean
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.utils.SpUtils

class CityRecycleAdapter(list: List<WeatherBean>) :
    BaseQuickAdapter<WeatherBean, BaseViewHolder>(
        R.layout.item_recycle_city,
        list
    ) {

    var cityid = SpUtils.getString(mContext,"cityid","101180101")

    override fun convert(helper: BaseViewHolder?, item: WeatherBean?) {
        if (helper?.layoutPosition == 0){
            helper?.setImageResource(R.id.iv_city,R.drawable.location)
        } else{
            helper?.setImageResource(R.id.iv_city,R.drawable.delete)
        }
        helper?.setText(R.id.tv_city, item?.city)
        helper?.addOnClickListener(R.id.ll_city)
        helper?.addOnClickListener(R.id.iv_city)
        helper?.setText(R.id.tv_weather,item?.realtime?.weather+"    "+item?.realtime?.temp+"°")
        var  imgView = helper?.getView(R.id.iv_weather) as ImageView
        handleImgView(imgView,item?.realtime?.weather)
    }
    private fun handleImgView(imgView: ImageView, weather: String?) {
        var imgSrc:Int? = 0
        when(weather){
            "晴" -> imgSrc = R.drawable.qing
            "多云" -> imgSrc = R.drawable.duoyun_dark
            "阵雨" -> imgSrc = R.drawable.zhenyu_dark
            "阴" -> imgSrc = R.drawable.yin_dark
            "小雨","中雨","大雨","暴雨" -> imgSrc = R.drawable.yu_dark
            "小雪","中雪","大雪","暴雪" -> imgSrc = R.drawable.xue_dark
            "雾","霾" -> imgSrc = R.drawable.wu_dark
            else -> imgSrc = R.drawable.yin_dark
        }
        imgView.setImageResource(imgSrc)
    }


}