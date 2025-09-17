package se.gritacademy.weatherapp.models

data class WeatherResponse(
    val data: List<CurrentWeather>,
    val count: Int
)

data class CurrentWeather(
    val app_temp: Double,
    val city_name: String,
    val country_code: String,
    val temp: Double,
    val weather: WeatherInfo
)

data class WeatherInfo(
    val code: Int,
    val description: String,
    val icon: String
)
