package links;

import base.BaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddLink")
public class AddLinkServlet extends BaseClass {
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

        String part1Id = request.getParameter("Part1Id");
        String part2Id = request.getParameter("Part2Id");

        if (part1Id != null && !part1Id.equals("") && part2Id != null && !part2Id.equals("")) {
            if (!part1Id.equals(part2Id)) {   // Nemozem linkovat objekt sam so sebou
                boolean doInsert = true;
                Connection conn = null;
                response.setContentType("text/html");

                // Zistim, ci uz rovnake Part-y nie su zlinkovane
                PreparedStatement select = null;
                ResultSet duplicita = null;
                try {
                    conn = myCreateConnection(driver, url, user, pass);
                    select = conn.prepareStatement("SELECT * FROM link WHERE id_part1 = ? and id_part2 = ?");
                    select.setString(1, part1Id);
                    select.setString(2, part2Id);
                    duplicita = select.executeQuery();
                    if (duplicita.next()) {
                        doInsert = false;
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

                // Ak sa nic nevratilo zo selectu, mozem pridavat
                if (doInsert) {
                    PreparedStatement stmt = null;
                    try {
                        conn = myCreateConnection(driver, url, user, pass);
                        stmt = conn.prepareStatement("INSERT INTO link (id_part1, id_part2) values (?, ?)");
                        stmt.setString(1, part1Id);
                        stmt.setString(2, part2Id);
                        stmt.execute();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
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
                }
            }
        }

        request.setAttribute("res_of_del", "Link added SUCCESSFULLY");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
