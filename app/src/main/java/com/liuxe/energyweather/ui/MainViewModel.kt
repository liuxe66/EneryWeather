package com.liuxe.energyweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.liuxe.energyweather.App
import com.liuxe.energyweather.base.BaseResponse
import com.liuxe.energyweather.bean.CitiesBean
import com.liuxe.energyweather.bean.CityBean
import com.liuxe.energyweather.bean.WeatherBean
import com.liuxe.energyweather.utils.AssetJsonUtils
import com.liuxe.energyweather.base.BaseViewModel
import com.liuxe.energyweather.http.RetrofitClient
import com.liuxe.energyweather.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {
    var mMainResponse = MutableLiveData<BaseResponse<WeatherBean>>()
    var weatherListResponse = MutableLiveData<List<WeatherBean>>()
    var weatherResponse = MutableLiveData<WeatherBean>()
    var realtimeResponse = MutableLiveData<WeatherBean.RealtimeBean>()
    var weatherInfoResponse = MutableLiveData<WeatherBean.WeatherDetailsInfoBean>()
    var lifeInfoResponse = MutableLiveData<List<WeatherBean.IndexesBean>>()
    var weathersResponse = MutableLiveData<List<WeatherBean.WeathersBean>>()

    fun getWeatherInfo(cityid: String, curPosition: Int) {
        request({
            RetrofitClient.service.getWeatherInfo(cityid)
        }, {
            mMainResponse.value = it
            handleMainResponse(curPosition)
        })
    }

    fun handleMainResponse(position: Int) {
        val response = mMainResponse.value as BaseResponse
        if (response.value.size > 0) {
            for (index in response.value.indices) {
                response.value[index].isSelected = index == position
            }
            weatherListResponse.value = response.value
            weatherResponse.value = response.value[position]
            realtimeResponse.value = response.value[position].realtime
            weatherInfoResponse.value = response.value[position].weatherDetailsInfo
            lifeInfoResponse.value = response.value[position].indexes
            weathersResponse.value = response.value[position].weathers
        }

    }

    fun getCity(): List<CityBean> {
        var cities:CitiesBean?= null
       viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var cityJson = AssetJsonUtils.getJson(App.CONTEXT, "city.json")
                var citiesTemp: CitiesBean = Gson().fromJson(cityJson, CitiesBean::class.java)
                cities = citiesTemp
            }
        }
        return cities!!.cities
    }

    fun getCityId(cityStr: String): String {
        val cityStrUse = if (cityStr.contains("市")) cityStr.replace("市", "") else cityStr
        for (city in getCity()) {
            if (city.city == cityStrUse) {
                return city.cityid
            }
        }
        return ""
    }
}