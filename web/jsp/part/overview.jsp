<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 27.2.2019
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parts Overview</title>
</head>
<body>
<!--
    response.setContentType("text/html");
-->

<div id="overviewPage-content-container">


        <div id="table-container">
            <table>
                <tr>
                    <%
                        response.setContentType("text/html");
                        Connection conn;
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
                                    "part",
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
                                        // V tabuľke chcem mať Object, nie ID
                    %>
                    <th>PART</th>
                    <%
                                        preskocId = false;
                                        continue;
                                    }

                                    String name = res.getString("COLUMN_NAME").toUpperCase();
                                    out.println("<th>" + name + "</th>");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    %>
                </tr>
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
                        res = query.executeQuery("SELECT * FROM part");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (res != null) {
                        int colCount;
                        try {
                            while (res.next()) {
                                colCount = res.getMetaData().getColumnCount();
                %>
                <tr>
                    <%
                        // Indexovanie začína od 1 ...

                        for (int i = 1; i <= colCount; i++) {
                    %>
                    <td ><%= res.getObject(i)%></td>
                    <%
                        }
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
