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
    public void init() throws ServletException {
        url = "jdbc:mysql://localhost:3306/technia?useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "admin";
        pass = "nbusr123";
        driver = "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        /*

        Locale locale = new Locale("sk", "SK");
        ResourceBundle rb =
                ResourceBundle.getBundle("parts.Bundle", locale);
        */
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

        //String title = rb.getString("helloworld.title");

        out.println("<title>Nejaky titulok</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.println("<form action=\"\" method=\"post\">" +
                "<input type=\"submit\" name=\"addButton\" value=\"Add Part\" />" +
                "</form>");
        out.println("<button>Update Part</button>");
        out.println("<button>Remove Part</button>");
        out.println("<button>Create Link</button>");

        out.println("<h1>Simple EBOM App</h1>");

        out.println("<table border=1 font-weight=bold text-align=center border-spacing=0>");
        out.println("<tr>");
        ResultSet headers = this.getHeaders();
        if (headers != null) {
            try {
                boolean preskocId = true;
                while (headers.next()) {
                    if (preskocId) {
                        // V tabuľke chcem mať Object, nie ID
                        out.println("<th padding=2 background-color:cyan>" + "PART" + "</th>");
                        preskocId = false;
                        continue;
                    }

                    String name = headers.getString("COLUMN_NAME").toUpperCase();
                    out.println("<th>" + name + "</th>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        out.println("</tr>");


        ResultSet parts = this.getData();
        if (parts != null) {
            int colCount;
            try {
                while (parts.next()) {
                    colCount = parts.getMetaData().getColumnCount();
                    out.println("<tr>");

                    // Indexovanie začína od 1 ...

                    for (int i = 1; i <= colCount; i++) {
                        out.println("<td >" + parts.getObject(i) + "</td>");
                    }

                    out.println("</tr>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</head>");
        out.println("</html>");
    }

    /// Pridá object Part do DB
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String type = request.getParameter("Type");
        String name = request.getParameter("Name");
        int length = Integer.parseInt(request.getParameter("Length"));
        int width = Integer.parseInt(request.getParameter("Width"));
        double weight = Double.parseDouble(request.getParameter("Weight"));
        double cost = Double.parseDouble(request.getParameter("Cost"));

        response.setContentType("text/html");

        Connection conn = null;

        if (!type.equals("") && !name.equals("")) {
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
                statement.execute(
                        "INSERT INTO part (type, name, length, width, weight, cost)" +
                                "values (\"" +
                                type + "\",\"" + name + "\",\"" +
                                length + "\",\"" + width + "\",\"" +
                                weight + "\",\"" + cost + "\")"
                );

                request.setAttribute("res_of_add", "Part added SUCCESSFULLY");
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

    /// Získa hlavičky stĺpcov
    private ResultSet getHeaders() {
        Connection conn;
        ResultSet res = null;

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
            res = conn.getMetaData().getColumns(
                    null,
                    null,
                    "part",
                    null
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /// Získa entity z DB
    private ResultSet getData() {
        Connection conn;
        ResultSet res = null;
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
            Statement query = conn.createStatement();
            res = query.executeQuery("SELECT * FROM part");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


}
