package com.aidul23.weatherapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidul23.weatherapp.Data.WeatherInfo
import com.aidul23.weatherapp.Repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getWeather()
        }
    }

    val weather : LiveData<WeatherInfo>
        get() = weatherRepository.weather
}