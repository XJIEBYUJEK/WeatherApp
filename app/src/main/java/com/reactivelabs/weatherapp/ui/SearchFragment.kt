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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val repository =  WeatherRepository()


        super.onViewCreated(view, savedInstanceState)


        startSearch.setOnClickListener {

            launch {
                if (cityName.text.isNotBlank() && countryCode.text.isNotBlank()){
                    val cityName = cityName.text.toString()
                    val countryCode = countryCode.text.toString()
                    val weather = repository.getCurrentWeatherForCity(cityName, countryCode).await()
                    Log.i("gfd", weather.toString() ?: "None")
                    val fragment = WeatherFragment()
                    val bundle = Bundle()
                    bundle.putString("city", weather?.name )
                    bundle.putString("title", title )
                }


            }



            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, WeatherFragment())
                ?.addToBackStack(null)
                ?.commit()




        }
    }
}