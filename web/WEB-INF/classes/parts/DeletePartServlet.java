package parts;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/DeletePart")
public class DeletePartServlet extends BaseClass {
    private String url;
    private String user;
    private String pass;
    private String driver;
}
