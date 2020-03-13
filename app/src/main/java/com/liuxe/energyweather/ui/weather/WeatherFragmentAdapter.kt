package com.liuxe.energyweather.ui.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.liuxe.energyweather.bean.WeatherCityBean

class WeatherFragmentAdapter(act: FragmentActivity,var mList:List<WeatherFragment>) : FragmentStateAdapter(act) {

    override fun getItemCount() = mList.size

    override fun createFragment(position: Int) = mList.get(position)

}