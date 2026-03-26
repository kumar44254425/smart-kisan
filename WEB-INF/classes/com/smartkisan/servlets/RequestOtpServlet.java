package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestOtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mobile = req.getParameter("mobile");
        String name = req.getParameter("name"); // present during signup
        String otp = String.valueOf(1000 + new Random().nextInt(9000));

        try (Connection con = DBUtil.getConnection()) {

            // Check if user exists
            PreparedStatement check = con.prepareStatement(
                "SELECT id, name FROM users WHERE mobile=?");
            check.setString(1, mobile);
            ResultSet rs = check.executeQuery();

            boolean exists = rs.next();

            // Save OTP (for both signup + login)
            PreparedStatement ps = con.prepareStatement(
                "REPLACE INTO otps(mobile, otp) VALUES (?,?)");
            ps.setString(1, mobile);
            ps.setString(2, otp);
            ps.executeUpdate();

            // Pass data to OTP page
            req.setAttribute("mobile", mobile);
            req.setAttribute("demoOtp", otp);

            if (exists) {
                // Login flow
                req.setAttribute("flow", "login");
            } else {
                // Signup flow (new user)
                req.setAttribute("flow", "signup");
                req.setAttribute("name", name);
            }

            req.getRequestDispatcher("verify-otp.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
