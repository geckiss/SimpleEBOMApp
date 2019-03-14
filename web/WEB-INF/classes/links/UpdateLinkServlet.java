package links;

import base.BaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateLink")
public class UpdateLinkServlet extends BaseClass {
    private String url;
    private String user;
    private String pass;
    private String driver;

    @Override
    public void init() throws ServletException {
        url = "jdbc:mysql://localhost:3306/technia?useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "admin";
        pass = "nbusr123";
        driver = "com.mysql.cj.jdbc.Driver";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String query = "UPDATE link SET ";
        int linkId = -1;
        String s = request.getParameter("LinkId");
        if (s != null && !s.equals("")) {
            linkId = Integer.parseInt(request.getParameter("LinkId"));
        }

        int part1Id = -1;
        s = request.getParameter("Part1Id");
        if (s != null && !s.equals("")) {
            part1Id = Integer.parseInt(request.getParameter("Part1Id"));
            query += "id_part1 = " + part1Id + ",";
        }

        int part2Id = -1;
        s = request.getParameter("Part2Id");
        if (s != null && !s.equals("")) {
            part2Id = Integer.parseInt(request.getParameter("Part2Id"));
            query += "id_part2 = " + part2Id + ",";
        }

        boolean executeUpdate = true;
        // Ak sa id partov rovnaju, nic nerobim
        if (query.endsWith(" ") || part1Id == part2Id) {
            executeUpdate = false;
        } else {
            query = query.substring(0, query.length() - 1);
        }

        // Uzivatel zadal nejaku hodnotu, ktorou chce updatovat
        if (executeUpdate) {
            query += " WHERE id = " + linkId;

            response.setContentType("text/html");

            // Lenze musim skontrolovat hodnotu
            // Ak je link 14-15, a on zada ze chce 15 updatovat na 15, UPDATE sa nesmie vykonat
            // Part ID sa nemusia rovnat, lebo jedno je -1 a druhe napr. 15
            // Aj tak musim checknut, ze to, čo updatujem, nebude mat po update rovnake partIds
            Connection conn = null;
            PreparedStatement select = null;
            ResultSet qRes;
            String q = "SELECT * FROM link ";
            if (part1Id != -1 && part2Id != -1) {
                q += "WHERE id_part1 = " + part1Id + " and id_part2 = " + part2Id;
            } else {
                if (part1Id != -1) {
                    q += "WHERE id_part1 = " + part1Id;
                } else {
                    if (part2Id != -1) {
                        q += "WHERE id_part2 = " + part2Id;
                    }
                }
            }

            try {
                conn = myCreateConnection(driver, url, user, pass);
                select = conn.prepareStatement(q);
                // Ak mi select(resultset) nieco vrati, chce napr. 15 updatovat na 15 -> zle
                qRes = select.executeQuery();
                if (qRes.next()) {
                    executeUpdate = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (select != null) {
                        select.close();
                    }
                } catch (Exception e) {
                    // log this error
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    // log this error
                }
            }

            // Uz som checkol na chybne hodnoty, ak ziadne nie su, executeUpdate = true -> cool
            if (executeUpdate) {
                conn = myCreateConnection(driver, url, user, pass);
                Statement statement = myCreateStatement(conn);
                if (statement != null) {
                    try {
                        statement.execute(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (conn != null) {
                    try {
                        // if null ???
                        conn.close();
                    } catch (Throwable e) {
                        // logger.warn("Could not close JDBC Connection", e);
                    }
                }
            }
        }

        request.setAttribute("res_of_upd", "Link updated SUCCESSFULLY");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
