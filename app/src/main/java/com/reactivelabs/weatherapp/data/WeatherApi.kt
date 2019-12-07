package com.reactivelabs.weatherapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val API_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "50000d34420cf8e84a8358f83d93de8f"
    }

    @GET("weather?APPID=$API_KEY&units=metric")

    fun getCurrentWeatherForCity(@Query("q") cityAndCountryCode: String): Call<Weather>
}