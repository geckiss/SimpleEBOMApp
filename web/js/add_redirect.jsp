<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>


<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 27.2.2019
  Time: 13:55
  To change this template use File | Settings | File Templates.


--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
    <!--
    TODO tento súbor mal byť .js, ale neviem, ako importovať do jsp .java triedy
    TODO Keď to zistíš, zmeň cieľ po(kliknutí na submit) na metódu v .java triede -->
    <%
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
            statement.executeUpdate("INSERT INTO part VALUES ()");
        } catch (SQLException e) {
            e.printStackTrace();
        }%>
</body>
</html>
