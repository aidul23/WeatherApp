package com.aidul23.weatherapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//092b9216affc0f10dd37ad0ff025f121

object RetrofitHelper {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}