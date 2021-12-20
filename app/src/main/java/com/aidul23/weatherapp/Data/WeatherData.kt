package com.aidul23.weatherapp.Data

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
@Parcelize
data class WeatherData (
    val clouds: @RawValue Clouds,
    val coord: @RawValue Coord,
    val dt: @RawValue Int,
    val id: @RawValue Int,
    val main: @RawValue Main,
    val name: @RawValue String,
    val rain: @RawValue Any,
    val snow: @RawValue Any,
    val sys: @RawValue Sys,
    val weather: List<Weather>,
    val wind: @RawValue Wind
): Parcelable