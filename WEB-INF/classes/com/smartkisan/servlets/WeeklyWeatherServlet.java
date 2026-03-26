package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class WeeklyWeatherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<String> labels = new ArrayList<>();
        List<Double> temps = new ArrayList<>();
        List<Integer> rainDays = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT DATE(report_time) as d, AVG(temperature) as t, " +
                "SUM(CASE WHEN description LIKE '%rain%' THEN 1 ELSE 0 END) as r " +
                "FROM weather_reports WHERE state='Telangana' " +
                "GROUP BY DATE(report_time) ORDER BY d ASC LIMIT 7"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                labels.add(rs.getString("d"));
                temps.add(rs.getDouble("t"));
                rainDays.add(rs.getInt("r"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("labels", labels.toString());
        req.setAttribute("temps", temps.toString());
        req.setAttribute("rain", rainDays.toString());

        req.getRequestDispatcher("weekly-weather.jsp").forward(req, resp);
    }
}