package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

public class LivePriceFetcherServlet extends HttpServlet {

    private static final String API_KEY = "579b464db66ec23bdd0000017cc67f0c52c548326ecb9f91c4cd5063";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection con = DBUtil.getConnection()) {

            // Clear old data
            PreparedStatement del = con.prepareStatement("TRUNCATE market_prices");
            del.executeUpdate();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO market_prices(crop, market, price) VALUES (?,?,?)"
            );

            // Fetch multiple pages (important for Telangana / AP)
            for (int page = 0; page < 5; page++) {

                String urlStr =
                    "https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070"
                    + "?api-key=" + API_KEY
                    + "&format=json&limit=1000&offset=" + (page * 1000);

                URL url = new URL(urlStr);
                HttpURLConnection conHttp = (HttpURLConnection) url.openConnection();
                conHttp.setRequestMethod("GET");
                conHttp.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader br = new BufferedReader(
                    new InputStreamReader(conHttp.getInputStream())
                );

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                JSONObject json = new JSONObject(sb.toString());
                JSONArray records = json.getJSONArray("records");

                for (int i = 0; i < records.length(); i++) {
                    JSONObject o = records.getJSONObject(i);

                    String state = o.optString("state", "").trim();

                    // Only Telangana & Andhra Pradesh
                    if (!(state.equalsIgnoreCase("Telangana") ||
                          state.equalsIgnoreCase("Andhra Pradesh"))) {
                        continue;
                    }

                    ps.setString(1, o.optString("commodity"));
                    ps.setString(2, o.optString("market"));
                    ps.setDouble(3, o.optDouble("modal_price", 0));
                    ps.executeUpdate();
                }
            }

            resp.sendRedirect(req.getContextPath() + "/prices");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Live price fetch failed", e);
        }
    }
}
