
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class PartManager extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Do required initialization

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

    /// Získa hlavičky stĺpcov
    private ResultSet getHeaders() {
        Connection conn;
        ResultSet res = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/technia?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "admin",
                    "nbusr123"
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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/technia?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "admin",
                    "nbusr123"
            );
            Statement query = conn.createStatement();
            res = query.executeQuery("SELECT * FROM part");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /// Pridá object parts.Part do DB
    public boolean add(Part pPart) {
        boolean addSuccessful = false;

        if (pPart != null) {
            Connection conn = null;

            try {
                conn = DriverManager.getConnection(
                        "localhost:3306",
                        "admin",
                        "nbusr123"
                );

                Statement statement = conn.createStatement();
                ResultSet res = statement.executeQuery(
                        "INSERT INTO part (type, name, length, width, weight, cost)" +
                                "values (" +
                                pPart.GetType() + "," + pPart.GetName() + "," +
                                pPart.GetLength() + "," + pPart.GetWidth() + "," +
                                pPart.GetWeight() + "," + pPart.GetCost() +
                                ")"
                );

                // TODO rowInserted mozno nie je spravna metoda
                if (res.rowInserted()) {
                    addSuccessful = true;
                }

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

        return addSuccessful;
    }
}
