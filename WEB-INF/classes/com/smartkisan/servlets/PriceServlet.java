package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PriceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Map<String, Object>> prices = new ArrayList<>();
        double bestPrice = 0;

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT crop, market, price, price_date FROM market_prices ORDER BY price_date DESC"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double p = rs.getDouble("price");
                bestPrice = Math.max(bestPrice, p);

                Map<String, Object> row = new HashMap<>();
                row.put("crop", rs.getString("crop"));
                row.put("market", rs.getString("market"));
                row.put("price", p);
                row.put("date", rs.getTimestamp("price_date"));

                prices.add(row);
            }

            req.setAttribute("prices", prices);
            req.setAttribute("bestPrice", bestPrice);
            req.getRequestDispatcher("prices.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error fetching prices", e);
        }
    }
}
