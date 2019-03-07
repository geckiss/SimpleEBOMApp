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
    <title>Simple EBOM App - Add Part</title>
    <link rel="stylesheet" type="text/css" href="../../css/part/addPart.css">
    <script src="../../js/index_script.js"></script>
</head>
<body>
<div id="addPage-content-container">

    <div id="part-add-form-container">
            <form method="post" action="AddPart">
                <span class="result">${res_of_add}</span>
                <table id="add-table">
                    <tr>
                        <td>Type</td>
                        <td>
                            <input type="text" name="Type">
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type="text" name="Name">
                        </td>
                    </tr>
                    <tr>
                        <td>Length</td>
                        <td>
                            <input type="number" name="Length" min="0" max="4,294,967,295">
                        </td>
                    </tr>
                    <tr>
                        <td>Width</td>
                        <td>
                            <input type="number" name="Width" min="0" max="4,294,967,295">
                        </td>
                    </tr>
                    <tr>
                        <td>Weight</td>
                        <td>
                            <input type="number" name="Weight" min="0" max="4,294,967,295">
                        </td>
                    </tr>
                    <tr>
                        <td>Cost</td>
                        <td>
                            <input type="number" name="Cost" min="-2,147,483,648" max="2,147,483,647">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input class="add-buttons" type="submit" value="Add new part" />
                        </td>
                    </tr>
                </table>
            </form>
    </div>
</div>
</body>
</html>
