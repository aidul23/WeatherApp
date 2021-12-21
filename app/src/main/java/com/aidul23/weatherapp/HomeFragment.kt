package com.aidul23.weatherapp

import android.app.PendingIntent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aidul23.weatherapp.Adapter.WeatherAdapter
import com.aidul23.weatherapp.Api.RetrofitHelper
import com.aidul23.weatherapp.Api.WeatherApi
import com.aidul23.weatherapp.Data.WeatherData
import com.aidul23.weatherapp.Repository.WeatherRepository
import com.aidul23.weatherapp.ViewModel.WeatherViewModel
import com.aidul23.weatherapp.ViewModel.WeatherViewModelFactory
import com.aidul23.weatherapp.databinding.HomeFragmentBinding
import java.util.*

class HomeFragment : Fragment(R.layout.home_fragment), WeatherAdapter.OnItemClickListener {
    lateinit var weatherViewModel: WeatherViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)

        val currentTime = Calendar.getInstance()[Calendar.HOUR_OF_DAY]


        val weatherService = RetrofitHelper.getInstance().create(WeatherApi::class.java)
        val repository = WeatherRepository(weatherService)


        weatherViewModel =
            ViewModelProvider(
                this,
                WeatherViewModelFactory(repository)
            ).get(WeatherViewModel::class.java)

        weatherViewModel.weather.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d(TAG, "onViewCreated: " + it.list)
                val adapter = WeatherAdapter(it.list, this)
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.adapter = adapter

            }
        })
    }

    override fun onItemClick(position: Int, weatherList: List<WeatherData>) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMapsFragment(weatherData = weatherList[position])
        findNavController().navigate(action)
    }

//    override fun onItemClick(weatherList: List<WeatherData>) {

//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


