# Weather App
A simple and clean Android weather application built entirely in Kotlin. This app fetches and displays real-time weather data from the Weatherbit.io REST API, using a modern approach to Android development.
##  Key Features
- Real-time Weather Data: Search for the current weather in any city.
- Clean UI: Displays the city name, temperature, and a short weather description.
- Dark Mode: Includes a functional toggle in the settings to switch between light and dark themes.
- Solid Navigation: Uses a multi-screen layout with a logical and predictable back button flow.
- Responsive Design: The user interface works well in both portrait and landscape orientations.
## Tech Stack & Architecture
The application is built using a structure that prevents crashes and data loss during events like screen rotation.
- Language: 100% Kotlin.
- Architecture: It uses a Single-Activity, Multi-Fragment pattern. A ViewModel manages and saves the UI's data, so it doesn't get lost when the screen rotates.
- Navigation: Jetpack Navigation Component handles all screen transitions through a central nav_graph.xml file.
- Networking: Retrofit is used to make simple and reliable HTTP requests to the REST API.
- Data Parsing: Gson works with Retrofit to automatically convert the API's JSON text into usable Kotlin data objects.
- Asynchronous Operations: Kotlin Coroutines are used to run network calls on a background thread, which keeps the app's UI running smoothly without freezing.
- UI State: LiveData holds the UI data within the ViewModel. The app's screens "observe" this data and update themselves automatically whenever new information arrives.
## API Integration Details
The app's main feature is its connection to the Weatherbit.io REST API. It specifically calls the /v2.0/current endpoint to get current weather information.
When a user starts a search, the WeatherViewModel uses a coroutine to make the network request. 

When a successful response comes back, the Gson library parses the JSON data into a WeatherResponse object. This object is then placed into the ViewModel's LiveData. The WeatherResultFragment is watching this LiveData, and as soon as the new data arrives, it updates the text on the screen to show the weather. This process is designed to handle common issues like network errors and screen rotation smoothly.


