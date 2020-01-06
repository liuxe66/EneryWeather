package com.liuxe.energyweather.http

import com.liuxe.energyweather.base.BaseResponse
import com.liuxe.energyweather.bean.WeatherBean
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "http://aider.meizu.com/app/weather/"
    }

    @GET("listWeather")
    suspend fun getWeatherInfo(@Query("cityIds")cityid: String):BaseResponse<WeatherBean>
}