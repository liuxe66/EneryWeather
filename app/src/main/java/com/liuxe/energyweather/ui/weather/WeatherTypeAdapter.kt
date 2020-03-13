package com.liuxe.energyweather.ui.weather

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liuxe.energyweather.R

import com.liuxe.energyweather.bean.WeatherTypeBean


class WeatherTypeAdapter(data: List<WeatherTypeBean>) :
    BaseMultiItemQuickAdapter<WeatherTypeBean, BaseViewHolder>(data) {

    init {
        addItemType(WeatherTypeBean.REALTIME, R.layout.item_type_realtime)
        addItemType(WeatherTypeBean.DETAILS, R.layout.item_type_details)
        addItemType(WeatherTypeBean.WEEKS, R.layout.item_type_weeks)
        addItemType(WeatherTypeBean.SPACE,R.layout.item_type_space)
        addItemType(WeatherTypeBean.INDEX, R.layout.item_type_index)
    }

    init {
        setSpanSizeLookup(SpanSizeLookup { _, position ->
            return@SpanSizeLookup when (getDefItemViewType(position)) {
                //每行的item个数= spanCount/?
                //这个直接占满全格子
                WeatherTypeBean.REALTIME, WeatherTypeBean.DETAILS, WeatherTypeBean.WEEKS -> 4
                //每个item占位2个
                WeatherTypeBean.INDEX -> 1
                //每个item占位3个
                else -> 4
            }
        })
    }

    override fun convert(helper: BaseViewHolder?, item: WeatherTypeBean?) {
        helper?.apply {
            item?.also {
                when (it.itemType) {
                    WeatherTypeBean.REALTIME -> {

                        setText(R.id.tv_weather, it.realtime?.weather)
                        setText(R.id.tv_temp, it.realtime?.temp + "°")

                        val lottisJson = handleLottie(it.realtime?.weather)
                        val lottieWeather = getView<LottieAnimationView>(R.id.lottie_weather)
                        lottieWeather?.run {
                            setAnimation(lottisJson)
                            playAnimation()
                        }
                    }
                    WeatherTypeBean.DETAILS -> {
                        val recyclerView = getView<RecyclerView>(R.id.recycler_details)
                        if (recyclerView.adapter == null || recyclerView.adapter !is WeatherDetailsAdapter) {
                            val adapter = WeatherDetailsAdapter(it.weatherDetailsInfo.weather3HoursDetailsInfos!!)
                            recyclerView.isNestedScrollingEnabled = false
                            adapter.onItemChildClickListener = onItemChildClickListener
                            recyclerView.layoutManager =
                                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                            adapter.bindToRecyclerView(recyclerView)
                        } else {
                            val adapter = recyclerView.adapter as WeatherDetailsAdapter
                            adapter.setNewData(it.weatherDetailsInfo.weather3HoursDetailsInfos!!)
                        }
                    }
                    WeatherTypeBean.WEEKS -> {
                        val dateList = it.weathers.date.split("-")
                        val dateStr = dateList[1]+"/"+dateList[2]
                        val weekStr = it.weathers.week.replace("星期","周")

                        helper?.setText(R.id.tv_date,dateStr)
                        helper?.setText(R.id.tv_week,weekStr)
                        helper?.setText(R.id.tv_temp_low,it.weathers.temp_night_c+"°")
                        helper?.setText(R.id.tv_temp_high,it.weathers.temp_day_c+"°")

                        helper?.setText(R.id.tv_weather,it.weathers.weather)

                        var  imgView = getView(R.id.iv_weather) as ImageView
                        handleImgView(imgView,it.weathers.weather)
                    }
                    WeatherTypeBean.INDEX -> {
                        setText(R.id.tv_life_state, it.indexes.level)
                        var name = when (it.indexes.name) {
                            "紫外线强度指数" -> "紫外线指数"
                            else -> it.indexes.name
                        }
                        setText(R.id.tv_life_name, name)
                        setImageResource(R.id.iv_life, handlePic(it.indexes.name))
                    }

                }
            }
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