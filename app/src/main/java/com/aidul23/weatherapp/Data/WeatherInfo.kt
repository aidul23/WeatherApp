package com.aidul23.weatherapp.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(
    val cod: String,
    val count: Int,
    val list: List<WeatherData>,
    val message: String
): Parcelable
