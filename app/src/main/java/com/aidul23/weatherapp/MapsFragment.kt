package com.aidul23.weatherapp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.aidul23.weatherapp.Adapter.WeatherAdapter
import com.aidul23.weatherapp.Api.RetrofitHelper
import com.aidul23.weatherapp.Api.WeatherApi
import com.aidul23.weatherapp.Data.WeatherData
import com.aidul23.weatherapp.Repository.WeatherRepository
import com.aidul23.weatherapp.ViewModel.WeatherViewModel
import com.aidul23.weatherapp.ViewModel.WeatherViewModelFactory
import com.aidul23.weatherapp.databinding.FragmentMapsBinding
import com.aidul23.weatherapp.databinding.HomeFragmentBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<MapsFragmentArgs>()

    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(args.weatherData.coord.lat, args.weatherData.coord.lon)
        googleMap.addMarker(MarkerOptions().position(sydney).title(args.weatherData.name))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,10F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        _binding = FragmentMapsBinding.bind(view)

        _binding!!.cityName.text = args.weatherData.name
        _binding!!.weatherCondition.text = args.weatherData.weather[0].main
        _binding!!.humidity.text = "Humidity: "+args.weatherData.main.humidity.toString()
        _binding!!.windSpeed.text = "Wind Speed: "+args.weatherData.wind.speed
        _binding!!.maxTemp.text ="Max. Temp: "+celsius(args.weatherData.main.temp_max)
        _binding!!.minTemp.text = "Min. Temp: "+celsius(args.weatherData.main.temp_min)
        _binding!!.temp.text = celsius(args.weatherData.main.temp)

        mapFragment?.getMapAsync(callback)
    }

    fun celsius(temp: Double) : String{
        var c = temp - 273.15
        return String.format("%.2f °c",c)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}