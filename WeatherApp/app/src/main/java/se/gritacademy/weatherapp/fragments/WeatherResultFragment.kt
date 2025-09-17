package se.gritacademy.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import se.gritacademy.weatherapp.R
import se.gritacademy.weatherapp.viewmodel.WeatherViewModel

class WeatherResultFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()
    private val args: WeatherResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCityName = view.findViewById<TextView>(R.id.tv_city_name)
        val tvTemperature = view.findViewById<TextView>(R.id.tv_temperature)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val backButton = view.findViewById<Button>(R.id.button_back)

        val cityName = args.cityName

        viewModel.weatherData.observe(viewLifecycleOwner) { weatherResponse ->
            progressBar.visibility = View.GONE
            weatherResponse?.let {
                if (it.data.isNotEmpty()) {
                    val weather = it.data[0]
                    tvCityName.text = weather.city_name
                    tvTemperature.text = "Temperature: ${weather.temp}°C"
                    tvDescription.text = "Description: ${weather.weather.description}"
                } else {
                    Toast.makeText(context, "Could not find weather data for $cityName", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            progressBar.visibility = View.GONE
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        progressBar.visibility = View.VISIBLE
        viewModel.fetchWeatherData(cityName)
    }
}

