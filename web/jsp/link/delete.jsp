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
    <title>Simple EBOM App - Delete Link</title>
    <link rel="stylesheet" type="text/css" href="../../css/part/deletePart.css">
</head>
    <body>
        <div id="deletePage-content-container">
            <div id="link-delete-separator"></div>
            <div id="delete-table-container">

                <table id="link-delete-table">
                    <tr>
                        <!-- Headers -->
                        <%
                            int maxAllowedLinkId = -1;
                            response.setContentType("text/html");
                            Connection conn = null;
                            ResultSet res = null;

                            // Zistím, aké je naväčšie ID link v DB
                            // Podľa toho povolím max možnú hodnotu v inputoch
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
                                res = query.executeQuery("SELECT * FROM link ORDER BY id DESC LIMIT 1;");
                                if (res.next()) {
                                    maxAllowedLinkId = Integer.parseInt(res.getString(1));
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            // Získam hlavičky
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
                                // Hlavičky
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
                                    out.println("<th>DELETE</th>");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (conn != null) {
                                conn.close();
                            }

                        %>
                    </tr>


                    <%
                        // Data
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
                            try {
                                int linkId = 0;
                                while (res.next()) {
                                    colCount = res.getMetaData().getColumnCount();
                                    %>
                                    <tr>
                                    <%
                                    // Indexovanie začína od 1 ...

                                    for (int i = 1; i <= colCount; i++) {
                                        %>
                                        <td >
                                            <%= res.getObject(i)%>
                                        </td>
                                        <%
                                        if (i == 1) {
                                            linkId = res.getInt(1); // Dostanem id-cko(z prveho stlpca)
                                        }
                                    }
                                    %>
                                        <td>
                                            <form method="post" action="DeleteLink">
                                                <input type="number" name="LinkId" value="<%= linkId %>" min="1" max="<%=maxAllowedLinkId%>" />
                                                <input class="delete-buttons" type="submit" value="DELETE" name="DELETE" />
                                            </form>
                                        </td>
                                    </tr>

                                <%
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
