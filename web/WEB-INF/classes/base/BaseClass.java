package base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public abstract class BaseClass extends HttpServlet {

    public void init() throws ServletException {}

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {}


    public Connection myCreateConnection(String driver, String url, String user, String pass) {
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

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public Statement myCreateStatement(Connection conn) {
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
