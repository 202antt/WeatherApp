package weatherapp;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class WeatherService {
    private static final String API_KEY = "20b2c0faa67f78372653aa7a1603c16a";

    public static WeatherResult getWeatherData(String city, String unit) {
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + URLEncoder.encode(city, "UTF-8") + "&units=" + unit + "&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder json = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(json.toString());

            double temp = obj.getJSONObject("main").getDouble("temp");
            String description = obj.getJSONArray("weather")
                    .getJSONObject(0).getString("description");
            String icon = obj.getJSONArray("weather")
                    .getJSONObject(0).getString("icon");

            saveSearchToFile(city, temp, description);
            return new WeatherResult(temp, description, icon);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveSearchToFile(String city, double temp, String description) {
        try (FileWriter writer = new FileWriter("weather_history.txt", true)) {
            writer.write(city + ": " + temp + "° | " + description + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
