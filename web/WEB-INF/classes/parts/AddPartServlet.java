package parts;

import base.BaseClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AddPart")
public class AddPartServlet extends BaseClass {

    private String url;
    private String user;
    private String pass;
    private String driver;

    @Override
    public void init() {
        url = "jdbc:mysql://localhost:3306/technia?useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "admin";
        pass = "nbusr123";
        driver = "com.mysql.cj.jdbc.Driver";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String type = request.getParameter("Type");
        String name = request.getParameter("Name");
        String length = request.getParameter("Length");
        String width = request.getParameter("Width");
        String weight = request.getParameter("Weight");
        String cost = request.getParameter("Cost");

        if
        (
            type != null && !type.equals("") &&
            name != null && !name.equals("") &&
            length != null && !length.equals("") &&
            width != null && !width.equals("") &&
            weight != null && !weight.equals("") &&
            cost != null && !cost.equals("")
        )
        {
            response.setContentType("text/html");
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = myCreateConnection(driver, url, user, pass);
                stmt = conn.prepareStatement("INSERT INTO part (type, name, length, width, weight, cost) values (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, type);
                stmt.setString(2, name);
                stmt.setString(3, length);
                stmt.setString(4, width);
                stmt.setString(5, weight);
                stmt.setString(6, cost);
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

        request.setAttribute("res_of_add", "Part added SUCCESSFULLY");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
