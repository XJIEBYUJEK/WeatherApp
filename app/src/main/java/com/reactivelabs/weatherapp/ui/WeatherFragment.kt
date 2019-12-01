package com.reactivelabs.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reactivelabs.weatherapp.R
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

        cityTitle.text = "Moscow"
        title.text = "Clear"
        description.text = "clear sky"
        temperature.text = "42°"
        humidity.text = "42%"
    }
}