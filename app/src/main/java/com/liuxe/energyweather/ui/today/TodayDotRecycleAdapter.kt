package com.liuxe.energyweather.ui.today

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.utils.SpUtils

class TodayDotRecycleAdapter(list: List<WeatherBean>) :
    BaseQuickAdapter<WeatherBean, BaseViewHolder>(
        R.layout.item_recycler_dot_today,
        list
    ) {
    override fun convert(helper: BaseViewHolder?, item: WeatherBean?) {
        if (item?.isSelected == true)
            helper?.setImageResource(R.id.iv_item,R.drawable.shape_dot_blue)
        else
            helper?.setImageResource(R.id.iv_item,R.drawable.shape_dot_blue_alphe)
    }

}