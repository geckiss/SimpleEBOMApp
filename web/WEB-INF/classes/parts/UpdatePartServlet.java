package parts;

import base.BaseClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

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

    /// Update
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String query = "UPDATE part SET ";
        int itemId = -1;
        if (!request.getParameter("ItemId").equals("")) {
            itemId = Integer.parseInt(request.getParameter("ItemId"));
        }

        String newType;
        if (!request.getParameter("Type").equals("")) {
            newType = request.getParameter("Type");
            query += "type = \"" + newType + "\",";
        }

        String newName;
        if (!request.getParameter("Name").equals("")) {
            newName = request.getParameter("Name");
            query += "name = \"" + newName + "\",";
        }

        int newLength;
        if (!request.getParameter("Length").equals("")) {
            newLength = Integer.parseInt(request.getParameter("Length"));
            query += "length = " + newLength + ",";
        }


        int newWidth;
        if (!request.getParameter("Width").equals("")) {
            newWidth = Integer.parseInt(request.getParameter("Width"));
            query += "width = " + newWidth + ",";
        }

        double newWeight;
        if (!request.getParameter("Weight").equals("")) {
            newWeight = Double.parseDouble(request.getParameter("Weight"));
            query += "weight = " + newWeight + ",";
        }

        double newCost;
        if (!request.getParameter("Cost").equals("")) {
            newCost = Double.parseDouble(request.getParameter("Cost"));
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
