package com.aidul23.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidul23.weatherapp.Repository.WeatherRepository

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }

}