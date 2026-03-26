package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, name, category, price FROM products"
            );
            ResultSet rs = ps.executeQuery();

            req.setAttribute("products", rs);
            req.getRequestDispatcher("booking.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}