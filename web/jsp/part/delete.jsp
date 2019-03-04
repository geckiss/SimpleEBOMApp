<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 27.2.2019
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple EBOM App - Delete Part</title>
    <link rel="stylesheet" type="text/css" href="../../css/part/deletePart.css">
</head>
<body>
<div id="deletePage-content-container">
    <!--
    <form action="" method="post">
      <input type="submit" name="addButton" value="Add Part" />
    </form>
    -->

    <div id="part-delete-separator"></div>
    <div id="delete-table-container">

        <table>
            <tr>
                <!-- Headers -->
                <%
                    response.setContentType("text/html");
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

                    if (res != null) {
                        try {
                            boolean preskocId = true;
                            while (res.next()) {
                                if (preskocId) {
                                    // No ID in table, first header will be PART
                %>
                <th>PART</th>
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
                %>
            </tr>

            <!-- Data -->
            <%
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

                if (res != null) {
                    int colCount;
                    try {
                        int itemId = 1;
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
                    }
                %>
                <td>
                    <form action="DeletePart" method="post">
                        <input type="number" value="<%= itemId++ %>" name="ID" disabled="true"/>
                        <input type="submit" value="DELETE" name="DELETE" />
                    </form>
                </td>
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
