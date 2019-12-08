package com.reactivelabs.weatherapp.ui

import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
import com.reactivelabs.weatherapp.ui.SearchFragment.Companion.CITY
import com.reactivelabs.weatherapp.ui.SearchFragment.Companion.DESCRIPTION
import com.reactivelabs.weatherapp.ui.SearchFragment.Companion.HUMIDITY
import com.reactivelabs.weatherapp.ui.SearchFragment.Companion.OVERALL
import com.reactivelabs.weatherapp.ui.SearchFragment.Companion.TEMP
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        returnFromFragmentWeather.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    SearchFragment()
                )
                ?.commit()
        }

        cityTitle.text = arguments?.getString(CITY)
        title.text = arguments?.getString(OVERALL)
        description.text = arguments?.getString(DESCRIPTION)
        temperature.text = "${arguments?.getString(TEMP)} °C"
        humidity.text = "${arguments?.getString(HUMIDITY)} %"
    }




}