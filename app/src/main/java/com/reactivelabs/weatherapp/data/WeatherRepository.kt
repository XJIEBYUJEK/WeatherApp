package com.reactivelabs.weatherapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class WeatherRepository(
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope {
    private val api = Retrofit.Builder()
        .baseUrl(WeatherApi.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getCurrentWeatherForCity(city: String, countryCode: String) = async {
        api.getCurrentWeatherForCity("$city,$countryCode").execute().body()
    }
}