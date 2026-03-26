package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VerifyOtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mobile = req.getParameter("mobile");
        String otp = req.getParameter("otp");
        String flow = req.getParameter("flow"); // "login" or "signup"
        String name = req.getParameter("name"); // only for signup

        try (Connection con = DBUtil.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT otp FROM otps WHERE mobile=? AND otp=?");
            ps.setString(1, mobile);
            ps.setString(2, otp);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                req.setAttribute("error", "Invalid OTP!");
                req.getRequestDispatcher("verify-otp.jsp").forward(req, resp);
                return;
            }

            if ("login".equals(flow)) {
                // Login user
                PreparedStatement u = con.prepareStatement(
                    "SELECT id, name FROM users WHERE mobile=?");
                u.setString(1, mobile);
                ResultSet ur = u.executeQuery();

                if (ur.next()) {
                    HttpSession session = req.getSession();
                    session.setAttribute("uid", ur.getInt("id"));
                    session.setAttribute("name", ur.getString("name"));
                    resp.sendRedirect("dashboard.jsp");
                } else {
                    resp.getWriter().println("User not found!");
                }

            } else {
                // Signup flow -> go to set password
                req.setAttribute("mobile", mobile);
                req.setAttribute("name", name);
                req.getRequestDispatcher("set-password.jsp").forward(req, resp);
            }

            // delete OTP after use
            PreparedStatement del = con.prepareStatement("DELETE FROM otps WHERE mobile=?");
            del.setString(1, mobile);
            del.executeUpdate();

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
