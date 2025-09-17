package se.gritacademy.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import se.gritacademy.weatherapp.api.ApiClient
import se.gritacademy.weatherapp.models.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            try {
                val apiKey = "5d2b2b5243da47da9995ea2c1026b006"
                val response = ApiClient.apiService.getCurrentWeather(city, apiKey)
                if (response.isSuccessful && response.body() != null) {
                    _weatherData.postValue(response.body())
                } else {
                    _error.postValue("Error: Could not fetch data. Status code: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.postValue("Network Error: ${e.message}")
            }
        }
    }
}
