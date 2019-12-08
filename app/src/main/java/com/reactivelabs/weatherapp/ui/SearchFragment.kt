package com.reactivelabs.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
import com.reactivelabs.weatherapp.data.Weather
import com.reactivelabs.weatherapp.data.WeatherData
import com.reactivelabs.weatherapp.data.WeatherRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchFragment(override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    companion object{
        const val CITY = "city"
        const val OVERALL = "title"
        const val DESCRIPTION = "description"
        const val HUMIDITY = "humidity"
        const val TEMP = "temp"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val repository =  WeatherRepository()


        super.onViewCreated(view, savedInstanceState)


        startSearch.setOnClickListener {

            launch {
                if (cityName.text.isNotBlank()){
                    val cityName = cityName.text.toString()
                    val countryCode = countryCodeSp.selectedItem.toString()
                    val weatherRep = repository.getCurrentWeatherForCity(cityName, countryCode).await()
                   // Log.i("gfd", weather.toString() ?: "None")
                    weatherRep?.apply{
                        val fragment = WeatherFragment()
                        val bundle = Bundle()
                        bundle.putString(CITY, name )
                        bundle.putString(OVERALL, weather.first().main)
                        bundle.putString(DESCRIPTION, weather.first().descriptor)
                        bundle.putString(TEMP, main.temp.toString())
                        bundle.putString(HUMIDITY, main.humidity.toString())
                        fragment.arguments = bundle
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container,fragment)      //переход на новый экран
                            ?.commit()
                    }
                }
            }
        }
    }
}