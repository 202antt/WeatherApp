This Weather Information App is a Java desktop application built with JavaFX that allows users to search for real-time weather information for different cities. The app connects to a weather API, retrieves live data, and displays important details like temperature, weather conditions, and icons that represent the current forecast.
This project was built to practice working with APIs, building graphical user interfaces, and handling real-world programming issues like API errors and data parsing.

Features
* Search weather by city name
* Retrieve real-time weather data from an API
* Display temperature and weather conditions
* Toggle between Celsius and Fahrenheit
* Weather icons that update based on conditions
* Basic error handling for invalid searches
* Simple and responsive JavaFX interface

Technologies Used
* Java
* JavaFX
* REST API integration
* JSON data parsing
* Eclipse IDE
* Git & GitHub

Project Structure


WeatherApp
│
├── WeatherApp.java       # Main JavaFX application
├── WeatherService.java   # Handles API requests
├── WeatherResult.java    # Stores weather data
├── icons/                # Weather condition images
└── README.md


Challenges and Problems Encountered
While building this application, a few common development challenges came up.
API Request Issues
At first, the application had trouble retrieving weather data because the API request URL was not formatted correctly. Fixing this required adjusting the request parameters and confirming that the API key was being passed correctly.
JSON Parsing
Once the API started returning data, the next challenge was parsing the JSON response. Some values like temperature and weather descriptions were nested inside the response, so the program had to be adjusted to properly extract those fields and store them in the WeatherResult class.
JavaFX UI Updates
Another issue occurred when updating the UI after a new search. The interface didn’t always refresh properly when new data was returned. This was resolved by restructuring how UI components were updated after the API response was received.
Temperature Conversion
Adding the Celsius/Fahrenheit toggle required extra logic so the app could convert temperatures locally without making additional API calls.
Error Handling
If an invalid city was entered or the API failed, the program would sometimes crash. Basic error handling was added so the app can display a message instead of terminating.
GitHub Authentication
While uploading the project to GitHub, authentication initially failed because GitHub no longer supports password authentication for Git pushes. The issue was resolved by using a Personal Access Token instead.

How to Run the Application
1. Clone the repository


git clone https://github.com/202antt/WeatherApp.git

1. Open the project in Eclipse
2. Make sure JavaFX is configured in your environment
3. Run the main application file


WeatherApp.java

1. Enter a city name to retrieve weather information.

Future Improvements
Some improvements that could be added later include:
* 7-day weather forecast
* Better UI styling
* Automatic location detection
* Saving previous searches
* Dynamic background images based on weather conditions
