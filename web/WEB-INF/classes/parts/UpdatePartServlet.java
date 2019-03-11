package parts;

import base.BaseClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;

@WebServlet("/UpdatePart")
public class UpdatePartServlet extends BaseClass {
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {    }

    /// Update
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String query = "UPDATE part SET ";

        int itemId = -1;
        String s = request.getParameter("ItemId");
        if (s != null && !s.equals("")) {
            itemId = Integer.parseInt(request.getParameter("ItemId"));
        }

        String newType = request.getParameter("Type");
        if (newType != null && !newType.equals("")) {
            query += "type = \"" + newType + "\",";
        }

        String newName = request.getParameter("Name");
        if (newName != null && !newName.equals("")) {
            query += "name = \"" + newName + "\",";
        }

        int newLength;
        s = request.getParameter("Length");
        if (s != null && !s.equals("")) {
            newLength = Integer.parseInt(s);
            query += "length = " + newLength + ",";
        }

        int newWidth;
        s = request.getParameter("Width");
        if (s != null && !s.equals("")) {
            newWidth = Integer.parseInt(s);
            query += "width = " + newWidth + ",";
        }

        double newWeight;
        s = request.getParameter("Weight");
        if (s != null && !s.equals("")) {
            newWeight = Double.parseDouble(s);
            query += "weight = " + newWeight + ",";
        }

        double newCost;
        s = request.getParameter("Cost");
        if (s != null && !s.equals("")) {
            newCost = Double.parseDouble(s);
            query += "cost = " + newCost + ",";
        }

        boolean executeUpdate = true;
        if (query.endsWith(",")) {
            query = query.substring(0, query.length() - 1);
        } else {
            if (query.endsWith(" ")) {
                executeUpdate = false;
            }
        }

        if (executeUpdate) {
            query += " WHERE id = " + itemId;


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

                request.setAttribute("res_of_upd", "Part updated SUCCESSFULLY");
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
