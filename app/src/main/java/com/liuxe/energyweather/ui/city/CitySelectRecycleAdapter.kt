package com.liuxe.energyweather.ui.city

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.CityBean
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.utils.SpUtils

class CitySelectRecycleAdapter(list: List<CityBean>) :
    BaseQuickAdapter<CityBean, BaseViewHolder>(
        R.layout.item_recycle_city_select,
        list
    ) {

    var cityid = SpUtils.getString(mContext,"cityid","101180101")

    override fun convert(helper: BaseViewHolder?, item: CityBean?) {
        helper?.setText(R.id.tv_city, item?.city)
        helper?.addOnClickListener(R.id.ll_city)
    }

}