package links;

import base.BaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteLink")
public class DeleteLinkServlet extends BaseClass {
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

        String linkId = request.getParameter("LinkId");

        if (linkId != null && !linkId.equals("")) {
            response.setContentType("text/html");
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = myCreateConnection(driver, url, user, pass);
                stmt = conn.prepareStatement("DELETE FROM link WHERE id = ?");
                stmt.setString(1, linkId);
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

            if (conn != null) {
                try {
                    // if null ???
                    conn.close();
                } catch (Throwable e) {
                    // logger.warn("Could not close JDBC Connection", e);
                }
            }
        }
        request.setAttribute("res_of_del", "Link deleted SUCCESSFULLY");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
