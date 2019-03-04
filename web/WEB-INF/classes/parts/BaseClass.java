package parts;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public abstract class BaseClass extends HttpServlet {

    public void init() throws ServletException {}

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {}
}
