package se.gritacademy.weatherapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import se.gritacademy.weatherapp.models.WeatherResponse

interface WeatherApiService {
    @GET("v2.0/current") // The endpoint for the current weather API
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("key") apiKey: String,
        @Query("lang") lang: String = "en" // Optional: get descriptions in English
    ): Response<WeatherResponse>
}
