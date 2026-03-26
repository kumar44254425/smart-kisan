package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
              "UPDATE users SET password=? WHERE mobile=?");
            ps.setString(1, password);
            ps.setString(2, mobile);
            ps.executeUpdate();

            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
