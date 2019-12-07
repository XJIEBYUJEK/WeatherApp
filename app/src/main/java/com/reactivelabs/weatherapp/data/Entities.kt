package com.reactivelabs.weatherapp.data

import java.io.FileDescriptor

data class Weather(
    val name: String,
    val weather: List<WeatherData>,
    val main: WeatherMain

)
data class WeatherData(
    val main: String,
    val descriptor: String,
    val icon: String
)
data class WeatherMain(
    val temp: Double, //Kelvin
    val pressure: Int,
    val humidity: Int
)


/*"weather":
{
    "id": 800,
    "main": "Clear",
    "description": "clear sky",
    "icon": "01n"
}
,
"base": "stations",
"main": {
    "temp": 289.92,
    "pressure": 1009,
    "humidity": 92,
    "temp_min": 288.71,
    "temp_max": 290.93
}*/