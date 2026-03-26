package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CropProfitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Read form values
            String crop = req.getParameter("crop");
            double area = Double.parseDouble(req.getParameter("area"));
            double seedCost = Double.parseDouble(req.getParameter("seedCost"));
            double fertilizerCost = Double.parseDouble(req.getParameter("fertilizerCost"));
            double laborCost = Double.parseDouble(req.getParameter("laborCost"));
            double expectedYield = Double.parseDouble(req.getParameter("yield"));
            double marketPrice = Double.parseDouble(req.getParameter("marketPrice"));

            // Calculate
            double totalCost = seedCost + fertilizerCost + laborCost;
            double revenue = expectedYield * marketPrice;
            double profit = revenue - totalCost;

            // Get logged in user id
            HttpSession session = req.getSession(false);
            int userId = (int) session.getAttribute("uid");

            // Save to DB
            try (Connection con = DBUtil.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO crop_profit(user_id, crop, area, seed_cost, fertilizer_cost, labor_cost, expected_yield, market_price, total_cost, revenue, profit) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
                );

                ps.setInt(1, userId);
                ps.setString(2, crop);
                ps.setDouble(3, area);
                ps.setDouble(4, seedCost);
                ps.setDouble(5, fertilizerCost);
                ps.setDouble(6, laborCost);
                ps.setDouble(7, expectedYield);
                ps.setDouble(8, marketPrice);
                ps.setDouble(9, totalCost);
                ps.setDouble(10, revenue);
                ps.setDouble(11, profit);

                ps.executeUpdate();
            }

            // Send result to JSP
            req.setAttribute("crop", crop);
            req.setAttribute("area", area);
            req.setAttribute("totalCost", totalCost);
            req.setAttribute("revenue", revenue);
            req.setAttribute("profit", profit);

            req.getRequestDispatcher("profit-result.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Profit calculation failed", e);
        }
    }
}