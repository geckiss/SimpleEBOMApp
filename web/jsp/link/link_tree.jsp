<%@ page import="java.sql.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 12.3.2019
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple EBOM App - Link Tree</title>
    <link rel="stylesheet" type="text/css" href="../../css/part/linkTree.css">
</head>
    <body>
        <div id="linkTree-content-container">
            <div id="linkTree-separator"></div>
            <div id="linkTree-table-container">

                <table id="linkTree-table">
                    <!-- Zistím, ake mám najväčšie ID partu v tabulke, aby som vedel šírku tabulky -->
                    <%
                        Connection conn = null;
                        ResultSet res = null;
                        int maxId = -1;
                        String sMaxId = "";
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
                            res = query.executeQuery("SELECT * FROM link ORDER BY id_part1 DESC, id_part2 DESC LIMIT 1");
                            if (res != null) {
                                // Vytiahnem hodnotu do stringu a porovnávam, či je väčšia než aktuálne maximum
                                while (res.next()) {
                                    for (int i = 2; i <= res.getMetaData().getColumnCount(); i++) {
                                        sMaxId = res.getString(i);
                                        if (maxId < Integer.parseInt(sMaxId)) {
                                            maxId = Integer.parseInt(sMaxId);
                                        }
                                    }
                                }
                            }
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        /* Data */
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
                            res = query.executeQuery("SELECT * FROM link ORDER BY id_part1 ASC, id_part2 ASC");

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        if (res != null) {
                            int colCount = res.getMetaData().getColumnCount();
                            try {
                                int linkId = 0;

                                // Najskôr hlavičky, prvý stĺpec bude prázdny
                                %>
                                <tr id="linkTree-table-headers-row">
                                    <td>LINK IDs/PART IDs</td>
                                <%
                                for (int i = 1; i <= maxId; i++) {
                                    %>
                                    <td><%=i%></td>
                                    <%
                                }
                                %>
                                    <td>x</td>
                                </tr>
                                <%
                                int partId = 0;
                                int part1id = 0;
                                int part2id = 0;
                                while (res.next()) {

                                    %>
                                    <tr>
                                    <%

                                    for (int i = 1; i < colCount; i++) {   // colCount je vzdy 3..
                                        if (i == 1) {
                                            linkId = res.getInt(i); // Dostanem id-cko(z prveho stlpca)
                                            %>
                                            <td id="<%=linkId%>">
                                                <%= res.getObject(i)%>
                                            </td>
                                            <%
                                        } else {
                                            int span = 1;
                                            part1id = Integer.parseInt(res.getString(i));
                                            part2id = Integer.parseInt(res.getString(i + 1));
                                            // Pomocny td na odsadenie part1id zlava
                                            if (part1id != 1) {
                                                %>
                                                <td colspan="<%=part1id - 1%>"></td>
                                                <%
                                            }
                                            %>
                                            <td><%=part1id%></td>
                                            <%

                                            if (part2id < part1id) {
                                                // Odsadim part1, a part2 odsadim s pomocnym td, ktory ma colspan=6-part1id
                                                span = maxId - part1id;

                                            } else {
                                                span = part2id - part1id - 1;
                                            }

                                            if (span != 0) {
                                                %>
                                                <td colspan="<%=span%>"></td>
                                                <%
                                            }
                                            %>
                                            <td><%=part2id%></td>
                                            <%
                                        }
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
