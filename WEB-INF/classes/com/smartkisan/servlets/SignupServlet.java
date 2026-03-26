package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");

        try (Connection con = DBUtil.getConnection()) {
            String sql = "INSERT INTO users(name, mobile, password) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, password);
            ps.executeUpdate();

            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            // likely duplicate mobile
            resp.getWriter().println("Mobile number already registered!");
        }
    }
}
