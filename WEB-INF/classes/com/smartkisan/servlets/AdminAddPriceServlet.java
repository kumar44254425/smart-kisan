package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminAddPriceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String crop = req.getParameter("crop");
        String market = req.getParameter("market");
        String priceStr = req.getParameter("price");

        try {
            double price = Double.parseDouble(priceStr);

            try (Connection con = DBUtil.getConnection()) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO market_prices (crop, market, price) VALUES (?, ?, ?)"
                );

                ps.setString(1, crop);
                ps.setString(2, market);
                ps.setDouble(3, price);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    resp.sendRedirect(req.getContextPath() + "/prices");
                } else {
                    resp.getWriter().println("Failed to add price");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Failed to add price");
        }
    }
}
