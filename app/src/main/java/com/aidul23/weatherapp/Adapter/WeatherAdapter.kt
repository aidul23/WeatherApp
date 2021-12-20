package com.aidul23.weatherapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.aidul23.weatherapp.Data.WeatherData
import com.aidul23.weatherapp.HomeFragmentDirections
import com.aidul23.weatherapp.databinding.ItemWeatherBinding
import java.util.ArrayList

class WeatherAdapter(private val weatherList: List<WeatherData>, private val listener: WeatherAdapter.OnItemClickListener) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater =
            ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: WeatherData) {
            binding.apply {
                cityName.text = data.name
                weatherCondition.text = data.weather[0].main
                temperature.text = celsius(data.main.temp)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
                listener.onItemClick(weatherList)
        }

        fun celsius(temp: Double) : String{
            var c = temp - 273.15
            return String.format("%.2f °c",c)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(weatherList: List<WeatherData> )
    }
}

