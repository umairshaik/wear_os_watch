package com.example.weatherwatchapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.weatherwatchapp.R
import com.example.weatherwatchapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.viewmodel = mainActivityViewModel

        viewBinding.bFirstRegion.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            mainActivityViewModel.fetchWeather(CityCode.BANGALORE.cityCode)
        }

        viewBinding.bSecondRegion.setOnClickListener {
            progressBar?.visibility = View.VISIBLE
            mainActivityViewModel.fetchWeather(CityCode.HYDERABAD.cityCode)
        }

        mainActivityViewModel.weatherValue.observe(this, Observer {
            progressBar?.visibility = View.GONE

            viewBinding.tvWeatherResult.text = getString(
                R.string.weather_result,
                it.main.temp.toString(),
                it.weather?.firstOrNull()?.main.toString()
            )
        })
    }
}