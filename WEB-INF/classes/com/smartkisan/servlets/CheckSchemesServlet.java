package com.smartkisan.servlets;

import com.smartkisan.util.DBUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class CheckSchemesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String passbook = req.getParameter("passbook");
        String name = req.getParameter("name");
        String state = req.getParameter("state");
        String district = req.getParameter("district");
        String landStr = req.getParameter("land");
        String type = req.getParameter("type");
        boolean aadhaar = "yes".equals(req.getParameter("aadhaar"));
        boolean bank = "yes".equals(req.getParameter("bank"));

        double land = 0;
        try {
            land = Double.parseDouble(landStr);
        } catch (Exception e) {
            land = 0;
        }

        List<Map<String,String>> schemes = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            // Save / Update farmer
            PreparedStatement save = con.prepareStatement(
                "INSERT INTO farmers(passbook_no, name, state, district, land_acres, farmer_type, has_aadhaar, has_bank) " +
                "VALUES(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
                "name=?, state=?, district=?, land_acres=?, farmer_type=?, has_aadhaar=?, has_bank=?"
            );

            save.setString(1, passbook);
            save.setString(2, name);
            save.setString(3, state);
            save.setString(4, district);
            save.setDouble(5, land);
            save.setString(6, type);
            save.setBoolean(7, aadhaar);
            save.setBoolean(8, bank);

            save.setString(9, name);
            save.setString(10, state);
            save.setString(11, district);
            save.setDouble(12, land);
            save.setString(13, type);
            save.setBoolean(14, aadhaar);
            save.setBoolean(15, bank);

            save.executeUpdate();

            schemes.add(check(con, passbook, "PM-KISAN",
                    aadhaar && bank && land > 0,
                    "₹6,000 per year"));

            schemes.add(check(con, passbook, "Rythu Bandhu",
                    state.equalsIgnoreCase("Telangana"),
                    "₹10,000 per acre (Telangana only)"));

            schemes.add(check(con, passbook, "PMFBY (Crop Insurance)",
                    land > 0,
                    "Crop insurance coverage"));

            schemes.add(check(con, passbook, "Soil Health Card",
                    true,
                    "Free soil testing"));

            schemes.add(check(con, passbook, "KCC Loan",
                    aadhaar && bank,
                    "Loan up to ₹3,00,000"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Scheme check failed", e);
        }

        req.setAttribute("schemes", schemes);
        req.getRequestDispatcher("schemes-result.jsp").forward(req, resp);
    }

    private Map<String,String> check(Connection con, String passbook,
                                    String scheme, boolean ok, String benefit) throws Exception {

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO scheme_results(passbook_no, scheme_name, eligible, benefit) VALUES (?,?,?,?)"
        );

        ps.setString(1, passbook);
        ps.setString(2, scheme);
        ps.setString(3, ok ? "Yes" : "No");
        ps.setString(4, benefit);
        ps.executeUpdate();

        Map<String,String> m = new HashMap<>();
        m.put("scheme", scheme);
        m.put("status", ok ? "Eligible ✅" : "Not Eligible ❌");
        m.put("benefit", benefit);
        return m;
    }
}