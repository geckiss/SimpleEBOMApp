<%@ page import="java.sql.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 5.3.2019
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple EBOM App - Update Link</title>
    <script src="../../js/link/updateLink.js" > </script>
</head>
    <body>
        <div id="updatePage-content-container">
            <div id="link-update-separator"></div>
            <div id="update-table-container">
                <table id="link-update-table">
                    <tr>
                        <%
                        // Hlavičky
                        int maxAllowedLinkId = -1;
                        response.setContentType("text/html");
                        Connection conn = null;
                        ResultSet res = null;

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
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
                                "link",
                                null
                            );
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (res != null) {
                            try {
                                boolean preskocId = true;
                                while (res.next()) {
                                    if (preskocId) {
                                        // No ID in table, first header will be LINK
                                        %>
                                        <th>LINK</th>
                                        <%
                                        preskocId = false;
                                        continue;
                                    }

                                    String name = res.getString("COLUMN_NAME").toUpperCase();
                                    out.println("<th>" + name + "</th>");
                                }
                                out.println("<th>ACTION</th>");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        %>
                    </tr>

                    <!-- Data -->
                    <%
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
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
                        res = query.executeQuery("SELECT * FROM link");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (res != null) {
                        int colCount;
                        // -----------------------
                        // NECHYTAT!!! MUSI TU BYT 0, INAK SCRIPT NEBUDE FUNGOVAT
                        // BLACK MAGIC WOODOO
                        int btnId = 0;
                        // -----------------------
                        try {
                            while (res.next()) {
                                colCount = res.getMetaData().getColumnCount();
                                %>
                                <tr>
                                <%
                                // Indexovanie začína od 1 ...

                                for (int i = 1; i <= colCount; i++) {
                                    out.println("<td>" + res.getObject(i) + "</td>");
                                }

                                out.println("<td><button " +
                                        "class=\"update-buttons\"" +
                                        "type=\"button\" " +
                                        "id=\"" + btnId++ +
                                        "\"" + "onclick=\"expandLinkRow(this.id)\"" +
                                        ">Expand" +
                                        "</button></td>");
                                %>
                                </tr>
                                <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
