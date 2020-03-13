package com.liuxe.energyweather.ui.weather

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherCityBean

class CityDotAdapter(list: List<WeatherCityBean>) :
    BaseQuickAdapter<WeatherCityBean, BaseViewHolder>(
        R.layout.item_recycler_dot_today,
        list
    ) {
    override fun convert(helper: BaseViewHolder?, item: WeatherCityBean?) {
        if (item?.isSelected == true)
            helper?.setImageResource(R.id.iv_item,R.drawable.shape_dot_blue)
        else
            helper?.setImageResource(R.id.iv_item,R.drawable.shape_dot_blue_alphe)
    }

}