package com.aidul23.weatherapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aidul23.weatherapp.Api.WeatherApi
import com.aidul23.weatherapp.Data.WeatherInfo

class WeatherRepository(private val weatherApi: WeatherApi) {

    private val weatherLiveData = MutableLiveData<WeatherInfo>()

    val weather: LiveData<WeatherInfo>
        get() = weatherLiveData

    suspend fun getWeather() {
        val results = weatherApi.getWeather("23.68", "90.35", "092b9216affc0f10dd37ad0ff025f121")
        if (results.body() != null) {
            weatherLiveData.postValue(results.body())
        }
    }
}