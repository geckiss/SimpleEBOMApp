<%@ page import="com.mysql.cj.exceptions.MysqlErrorNumbers" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 26.2.2019
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>

<html>
  <head>
    <title>Simple EBOM App</title>
    <link href="${pageContext.request.contextPath}/css/index_style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js//index_script.js" > </script>
  </head>
  <body>
  <%
  %>

    <div id="menu-container">
      <span id="part-link-switcher">
        <div id="part-span">
          <button type="button">Parts Management</button>
        </div>
        <div id="link-span">
          <button type="button">Links Management</button>
        </div>
      </span>

      <div id="parts-mngmnt-container">
        <span class="part-category-span">
          <button id="overview-button" type="button" onclick="chooseOption(this.id)">
              Overview
          </button>
        </span>
        <span class="part-category-span">
          <button id="add-button" type="button" onclick="chooseOption(this.id)">
              Add
          </button>
        </span>
        <span class="part-category-span">
          <button id="update-button" type="button" onclick="chooseOption(this.id)">
              Update
          </button>
        </span>
        <span class="part-category-span">
          <button id="delete-button" type="button" onclick="chooseOption(this.id)">
              Delete
          </button>
        </span>
      </div>

      <div id="title-div">Simple EBOM App</div>
    </div>

  <div id="content-container">
    <jsp:include page="jsp/part/overview.jsp"></jsp:include>
  </div>

  </body>
</html>
