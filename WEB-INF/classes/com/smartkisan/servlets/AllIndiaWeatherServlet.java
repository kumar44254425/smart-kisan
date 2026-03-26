package com.smartkisan.servlets;
import com.smartkisan.util.DBUtil;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

public class AllIndiaWeatherServlet extends HttpServlet {

    private static final String API_KEY = "000e5277196488aaf4f8cef84c202c45";

    private static final String[][] STATES = {
        {"Andhra Pradesh", "Amaravati"},
        {"Telangana", "Hyderabad"},
        {"Tamil Nadu", "Chennai"},
        {"Karnataka", "Bengaluru"},
        {"Kerala", "Thiruvananthapuram"},
        {"Maharashtra", "Mumbai"},
        {"Gujarat", "Ahmedabad"},
        {"Rajasthan", "Jaipur"},
        {"Madhya Pradesh", "Bhopal"},
        {"Uttar Pradesh", "Lucknow"},
        {"Bihar", "Patna"},
        {"West Bengal", "Kolkata"},
        {"Odisha", "Bhubaneswar"},
        {"Punjab", "Chandigarh"},
        {"Haryana", "Chandigarh"},
        {"Himachal Pradesh", "Shimla"},
        {"Uttarakhand", "Dehradun"},
        {"Jharkhand", "Ranchi"},
        {"Chhattisgarh", "Raipur"},
        {"Assam", "Guwahati"},
        {"Arunachal Pradesh", "Itanagar"},
        {"Manipur", "Imphal"},
        {"Meghalaya", "Shillong"},
        {"Mizoram", "Aizawl"},
        {"Nagaland", "Kohima"},
        {"Tripura", "Agartala"},
        {"Sikkim", "Gangtok"},
        {"Goa", "Panaji"},
        {"Delhi", "New Delhi"}
    };

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Map<String, String>> list = new ArrayList<>();

        try (Connection dbCon = DBUtil.getConnection()) {

            dbCon.prepareStatement("DELETE FROM weather_reports").executeUpdate();

            PreparedStatement ps = dbCon.prepareStatement(
              "INSERT INTO weather_reports(state, temperature, description, alert, alertType,cropSuggestion) VALUES (?,?,?,?,?,?)"
            );

            for (String[] s : STATES) {
                String state = s[0];
                String city = s[1];

                String urlStr = "https://api.openweathermap.org/data/2.5/weather?q="
                        + URLEncoder.encode(city, "UTF-8")
                        + "&appid=" + API_KEY + "&units=metric";

                HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);

                JSONObject json = new JSONObject(sb.toString());
                double temp = json.getJSONObject("main").getDouble("temp");
                String desc = json.getJSONArray("weather").getJSONObject(0).getString("description");

                String alert;
                String alertType;
                String cropSuggestion;

if (desc.toLowerCase().contains("rain")) {
    alert = "🌧️ Rain Alert in " + state + " – Protect crops & avoid spraying.";
    alertType = "rain";
    cropSuggestion = "🌾 Best Crops: Paddy, Sugarcane";
} else if (temp >= 38) {
    alert = "🔥 Heatwave Alert in " + state + " – Irrigate crops & avoid daytime work.";
    alertType = "heatwave";
 cropSuggestion = "🌾 Best Crops: Millets, Cotton";
} else if (temp <= 5) {
    alert = "❄️ Cold wave - protect crops in " + state;
    alertType = "cold";
   cropSuggestion = "🌾 Best Crops: Wheat, Barley";
}else {
    alert = "☀️ Normal weather in " + state;
    alertType = "normal";
    cropSuggestion = "🌾 Best Crops: Maize, Vegetables";
}
   
                ps.setString(1, state);
                ps.setDouble(2, temp);
                ps.setString(3, desc);
                ps.setString(4, alert);
                ps.setString(5, alertType);
                ps.setString(6, cropSuggestion);
                ps.executeUpdate();

                Map<String, String> row = new HashMap<>();
                row.put("state", state);
                row.put("city", city);
                row.put("temp", String.valueOf(temp));
                row.put("desc", desc);
                row.put("alert", alert);
                row.put("alertType", alertType);
                row.put("crop", cropSuggestion);
                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Weather fetch failed", e);
        }

        req.setAttribute("weatherList", list);
        req.getRequestDispatcher("weather-india-result.jsp").forward(req, resp);
    }
}