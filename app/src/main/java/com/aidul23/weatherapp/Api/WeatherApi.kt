package com.aidul23.weatherapp.Api

import com.aidul23.weatherapp.Data.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
interface WeatherApi {
    @GET("find")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ) : Response<WeatherInfo>
}