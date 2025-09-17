package se.gritacademy.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import se.gritacademy.weatherapp.R

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextCity = view.findViewById<EditText>(R.id.editText_city)
        val buttonShowWeather = view.findViewById<Button>(R.id.button_show_weather)
        val backButton = view.findViewById<Button>(R.id.button_back)

        buttonShowWeather.setOnClickListener {
            val cityName = editTextCity.text.toString()
            if (cityName.isNotBlank()) {
                val action = SearchFragmentDirections.actionSearchFragmentToWeatherResultFragment(cityName)
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Please enter a city", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

