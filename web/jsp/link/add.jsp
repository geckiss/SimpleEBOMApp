<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 27.2.2019
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple EBOM App - Add Link</title>
    <script src="../../js/index_script.js"></script>
</head>
    <body>
        <div id="addPage-content-container">
            <div id="link-add-separator">
                <span id="add-successful"></span>
                <span id="add-unsuccessful"></span>
            </div>
            <div id="link-add-form-container">
                <form method="post" action="AddLink">
                    <span class="result">${res_of_add}</span>
                    <table id="add-table">
                        <tr>
                            <td>(Part)ID1</td>
                            <td>
                                <input type="number" name="Part1Id" min="1" max="4,294,967,295">
                            </td>
                        </tr>
                        <tr>
                            <td>(Part)ID2</td>
                            <td>
                                <input type="number" name="Part2Id" min="1" max="4,294,967,295">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input class="add-buttons" type="submit" value="Add new link" />
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
