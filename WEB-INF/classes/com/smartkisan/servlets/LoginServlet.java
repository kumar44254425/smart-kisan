package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
              "SELECT id, name FROM users WHERE mobile=? AND password=? AND mobile_verified=TRUE");
            ps.setString(1, mobile);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("uid", rs.getInt("id"));
                session.setAttribute("name", rs.getString("name"));
                resp.sendRedirect("dashboard.jsp");
            } else {
         req.setAttribute("error", "Wrong password. Try OTP login or reset password.");
          req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
