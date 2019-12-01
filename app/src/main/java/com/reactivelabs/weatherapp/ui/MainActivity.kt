package com.reactivelabs.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reactivelabs.weatherapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, SearchFragment())
            .commit()
    }
}
