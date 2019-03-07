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

    /// Update
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String query = "DELETE FROM part ";
        int itemId = -1;
        if (!request.getParameter("ItemToDel").equals("")) {
            itemId = Integer.parseInt(request.getParameter("ItemToDel"));
        }

        boolean executeDelete = true;
        if (itemId != -1) {
            query += "WHERE id = " + itemId;
        } else {
            executeDelete = false;
        }

        if (executeDelete) {
            response.setContentType("text/html");
            Connection conn = null;

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                conn = DriverManager.getConnection(
                    url,
                    user,
                    pass
                );

                Statement statement = conn.createStatement();
                statement.execute(query);

                request.setAttribute("res_of_del", "Part deleted SUCCESSFULLY");
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    // if null ???
                    conn.close();
                } catch (Throwable e) {
                    // logger.warn("Could not close JDBC Connection", e);
                }
            }
        }
    }
}
