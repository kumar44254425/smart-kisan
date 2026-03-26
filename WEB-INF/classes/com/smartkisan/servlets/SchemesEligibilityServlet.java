package com.smartkisan.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class SchemesEligibilityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String state = req.getParameter("state");
        double land = Double.parseDouble(req.getParameter("land"));
        String aadhaar = req.getParameter("aadhaar");
        String bank = req.getParameter("bank");

        List<Map<String,String>> schemes = new ArrayList<>();

        // PM-KISAN
        schemes.add(check("PM-KISAN", 
            aadhaar.equals("yes") && bank.equals("yes"),
            "Aadhaar & bank required"));

        // Rythu Bandhu (Telangana)
        schemes.add(check("Rythu Bandhu",
            state.equalsIgnoreCase("Telangana"),
            "Only for Telangana farmers"));

        // PMFBY
        schemes.add(check("PMFBY (Crop Insurance)", land > 0,
            "Farmer must own land"));

        // Soil Health Card
        schemes.add(check("Soil Health Card", true,
            "Available for all farmers"));

        // KCC Loan
        schemes.add(check("KCC Loan",
            bank.equals("yes"),
            "Bank account required"));

        req.setAttribute("schemes", schemes);
        req.getRequestDispatcher("schemes-result.jsp").forward(req, resp);
    }

    private Map<String,String> check(String name, boolean ok, String failReason) {
        Map<String,String> m = new HashMap<>();
        m.put("name", name);
        if (ok) {
            m.put("status", "Eligible");
            m.put("reason", "Meets criteria");
        } else {
            m.put("status", "Not Eligible");
            m.put("reason", failReason);
        }
        return m;
    }
}