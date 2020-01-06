package com.liuxe.energyweather.ui.life

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.utils.DisplayUtils

class LifeRecycleAdapter(list: List<WeatherBean.IndexesBean>) :
    BaseQuickAdapter<WeatherBean.IndexesBean, BaseViewHolder>(
        R.layout.item_recycle_life,
        list
    ) {

    var curPosition = 1

    override fun convert(helper: BaseViewHolder?, item: WeatherBean.IndexesBean?) {
        helper?.setText(R.id.tv_life_state, item?.level)
        var name = when (item?.name) {
            "紫外线强度指数" -> "紫外线指数"
            else -> item?.name
        }
        helper?.setText(R.id.tv_life_name, name)
        helper?.setImageResource(R.id.iv_life, handlePic(item?.name))

        when (helper?.adapterPosition) {
            curPosition -> {
                helper?.getView<CardView>(R.id.card_life)
                    ?.setCardBackgroundColor(mContext.resources.getColor(R.color.color_66cbcdfc))
                helper?.getView<TextView>(R.id.tv_dot)?.visibility = View.VISIBLE
            }
            else -> {
                helper?.getView<CardView>(R.id.card_life)
                    ?.setCardBackgroundColor(mContext.resources.getColor(R.color.white))
                helper?.getView<TextView>(R.id.tv_dot)?.visibility = View.GONE
            }
        }

        helper?.addOnClickListener(R.id.card_life)
    }

    public fun handleClick(position: Int) {
        curPosition = position
        notifyDataSetChanged()
    }

    private fun handlePic(name: String?): Int {
        return when (name) {
            "洗车指数" -> R.drawable.xczs
            "穿衣指数" -> R.drawable.cyzs
            "紫外线强度指数" -> R.drawable.zwxzs
            "感冒指数" -> R.drawable.gmzs
            "化妆指数" -> R.drawable.hzzs
            "运动指数" -> R.drawable.ydzs
            else -> R.drawable.zwxzs
        }
    }
}