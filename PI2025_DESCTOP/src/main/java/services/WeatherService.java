package services;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherService {
    private static final String API_KEY = "2ffd064fbe720a8406a88497acc58268"; // Replace with your API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast?units=metric&lat=%f&lon=%f&appid=" + API_KEY;

    public static String getWeeklyForecast(double latitude, double longitude) {
        String url = String.format(BASE_URL, latitude, longitude);
        String jsonResponse = HttpUtils.fetchData(url);

        if (jsonResponse == null) {
            return "Error fetching weather data.";
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray list = jsonObject.getJSONArray("list");

        StringBuilder forecast = new StringBuilder();
        for (int i = 0; i < list.length(); i += 8) { // Every 8 entries ≈ 1 day
            JSONObject dayData = list.getJSONObject(i);
            String date = dayData.getString("dt_txt").split(" ")[0]; // Extract date

            JSONObject main = dayData.getJSONObject("main");
            double temp = main.getDouble("temp");
            double tempMin = main.getDouble("temp_min");
            double tempMax = main.getDouble("temp_max");

            String weatherMain = dayData.getJSONArray("weather").getJSONObject(0).getString("main");
            String weatherIcon = getWeatherIcon(weatherMain);

            forecast.append(String.format("%s %s: 🌡 %.1f°C (Min: %.1f°C, Max: %.1f°C)\n",
                    weatherIcon, date, temp, tempMin, tempMax));
        }
        return forecast.toString();
    }
    public static String getWeatherIcon(String weather) {
        switch (weather.toLowerCase()) {
            case "clear":
                return "☀️";
            case "clouds":
                return "⛅";
            case "rain":
                return "🌧️";
            case "thunderstorm":
                return "⛈️";
            case "snow":
                return "❄️";
            case "mist":
                return "🌫️";
            default:
                return "🌍"; // Default Earth icon
        }
    }

}
