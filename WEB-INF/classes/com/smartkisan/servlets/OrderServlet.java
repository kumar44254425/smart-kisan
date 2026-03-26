package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("uid") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String pidStr = req.getParameter("productId");
        String qtyStr = req.getParameter("qty");

        if (pidStr == null || qtyStr == null || pidStr.isEmpty() || qtyStr.isEmpty()) {
            resp.getWriter().println("❌ Product ID or Quantity missing!");
            return;
        }

        int productId = Integer.parseInt(pidStr);
        int qty = Integer.parseInt(qtyStr);
        int userId = (int) session.getAttribute("uid");

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders(user_id, product_id, quantity) VALUES (?,?,?)"
            );

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, qty);
            ps.executeUpdate();

            resp.sendRedirect("orders");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Order failed", e);
        }
    }
}