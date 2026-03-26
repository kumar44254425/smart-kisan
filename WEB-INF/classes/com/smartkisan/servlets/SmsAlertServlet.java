package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SmsAlertServlet extends HttpServlet {

    // 🔑 Paste your Fast2SMS API Key here
    private static final String FAST2SMS_API_KEY = "cyobSXJF0KrRnENg8U2B3GaW7wIhkAvPfODLTuHiQzdeCZ546jc9iXnt3pBrGP6d0olmAV7IZ8vzshQb";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mobile = req.getParameter("mobile");
        String message = req.getParameter("message");

        try {
            String urlStr = "https://www.fast2sms.com/dev/bulkV2?authorization="
                    + FAST2SMS_API_KEY
                    + "&route=q&message=" + URLEncoder.encode(message, "UTF-8")
                    + "&numbers=" + mobile;

            HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int code = con.getResponseCode();

            // Save log in DB
            try (Connection db = DBUtil.getConnection()) {
                PreparedStatement ps = db.prepareStatement(
                    "INSERT INTO sms_logs(mobile, message) VALUES (?, ?)"
                );
                ps.setString(1, mobile);
                ps.setString(2, message);
                ps.executeUpdate();
            }

            resp.sendRedirect("dashboard.jsp?msg=sms_sent");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("SMS sending failed", e);
        }
    }
}