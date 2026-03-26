package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class OrdersListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("uid") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) s.getAttribute("uid");

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT p.name AS product_name, p.category, o.quantity, p.price, o.order_date " +
                "FROM orders o " +
                "JOIN products p ON o.product_id = p.id " +
                "WHERE o.user_id = ? " +
                "ORDER BY o.order_date DESC"
            );

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            req.setAttribute("orders", rs);
            req.getRequestDispatcher("my-orders.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Unable to load orders", e);
        }
    }
}