package com.liuxe.energyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.liuxe.energyweather.App
import com.liuxe.energyweather.base.BaseResponse
import com.liuxe.energyweather.base.BaseViewModel
import com.liuxe.energyweather.bean.*
import com.liuxe.energyweather.http.RetrofitClient
import com.liuxe.energyweather.utils.AssetJsonUtils
import com.loc.w
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Main2ViewModel : BaseViewModel() {
    var mMainResponse = MutableLiveData<BaseResponse<WeatherBean>>()

    var mWeatherCitiesList = MutableLiveData<List<WeatherCityBean>>()

    var mPositionList = MutableLiveData<List<WeatherCityBean>>()

    var mScrollY = MutableLiveData<Int>()

    fun getWeatherInfo(cityid: String, curPosition: Int) {
        request({
            RetrofitClient.service.getWeatherInfo(cityid)
        }, {
            mMainResponse.value = it
            handleMainResponse()
            handlePosition(curPosition)
        })
    }

    fun handlePosition(position: Int) {
        val response = mMainResponse.value as BaseResponse
        val positionList = ArrayList<WeatherCityBean>()
        if (response.value.size > 0) {
            viewModelScope.launch(Dispatchers.Main) {
                mPositionList.value = withContext(Dispatchers.IO) {
                    response.value?.forEach {
                        val weatherCity = WeatherCityBean()
                        weatherCity.city = it.city
                        weatherCity.cityid = it.cityid
                        weatherCity.realtime = it.realtime

                        positionList.add(weatherCity)
                    }
                    for (index in positionList.indices) {
                        positionList[index].isSelected = index == position
                    }

                    return@withContext positionList
                }
            }

        }
    }

    private fun handleMainResponse() {
        val response = mMainResponse.value as BaseResponse
        var cityList = ArrayList<WeatherCityBean>()
        if (response.value.size > 0) {
            viewModelScope.launch(Dispatchers.Main) {
                mWeatherCitiesList.value = withContext(Dispatchers.IO) {
                    response.value?.forEach {
                        val weatherCity = WeatherCityBean()
                        weatherCity.city = it.city
                        weatherCity.cityid = it.cityid
                        weatherCity.realtime = it.realtime
                        val weatherTypeList = ArrayList<WeatherTypeBean>()

                        val weatherRealtime = WeatherTypeBean(WeatherTypeBean.REALTIME)
                        weatherRealtime.realtime = it.realtime
                        weatherTypeList.add(weatherRealtime)

                        val weatherDetails = WeatherTypeBean(WeatherTypeBean.DETAILS)
                        weatherDetails.weatherDetailsInfo = it.weatherDetailsInfo
                        weatherTypeList.add(weatherDetails)

                        it.weathers.forEach {
                            val weatherWeek = WeatherTypeBean(WeatherTypeBean.WEEKS)
                            weatherWeek.weathers = it
                            weatherTypeList.add(weatherWeek)
                        }

                        val weatherSpace = WeatherTypeBean(WeatherTypeBean.SPACE)
                        weatherTypeList.add(weatherSpace)

                        it.indexes.forEach {
                            val weatherIndex = WeatherTypeBean(WeatherTypeBean.INDEX)
                            weatherIndex.indexes = it
                            weatherTypeList.add(weatherIndex)
                        }

                        weatherCity.weatherTypeList = weatherTypeList
                        cityList.add(weatherCity)
                    }

                    return@withContext cityList
                }
            }

        }
    }

    fun getCity(): List<CityBean> {
        var cities: CitiesBean? = null
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