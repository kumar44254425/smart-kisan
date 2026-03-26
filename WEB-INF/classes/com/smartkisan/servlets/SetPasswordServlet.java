package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
              "INSERT INTO users(name, mobile, password, mobile_verified) VALUES (?,?,?,TRUE)");
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, password);
            ps.executeUpdate();

            PreparedStatement del = con.prepareStatement("DELETE FROM otps WHERE mobile=?");
            del.setString(1, mobile);
            del.executeUpdate();

            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            resp.getWriter().println("Mobile already registered!");
        }
    }
}
