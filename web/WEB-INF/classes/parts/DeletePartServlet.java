package parts;

import base.BaseClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeletePart")
public class DeletePartServlet extends BaseClass {
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

        String itemId = request.getParameter("ItemId");

        if (itemId != null && !itemId.equals("")) {
            response.setContentType("text/html");
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = myCreateConnection(driver, url, user, pass);
                stmt = conn.prepareStatement("DELETE FROM part WHERE id = ?");
                stmt.setString(1, itemId);
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

        request.setAttribute("res_of_del", "Part deleted SUCCESSFULLY");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
