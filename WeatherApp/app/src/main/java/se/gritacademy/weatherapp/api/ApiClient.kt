package se.gritacademy.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.weatherbit.io/"

    // Builds a Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // Uses Gson to convert JSON
        .build()

    // Creates an implementation of the API service interface
    val apiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}
