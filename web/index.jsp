
<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 26.2.2019
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Simple EBOM App</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js" > </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/part/updatePart.js" > </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/link/updateLink.js" > </script>
  </head>
  <body>

    <div id="menu-container">
      <div id="category-container">
        <span id="part-link-switcher">
          <div id="part-span">
            <button class="menu-buttons" id="part-category-button" type="button" onclick="chooseCategory(this.id)" >Parts Management</button>
          </div>
          <div id="link-span">
            <button class="menu-buttons" id="link-category-button" type="button" onclick="chooseCategory(this.id)" >Links Management</button>
          </div>
        </span>
      </div>

      <div id="connector-container">
        <div id="part-connector"></div>
        <div id="link-connector"></div>
      </div>

      <div id="options-container">
        <jsp:include page="jsp/part/p_option_container.jsp"></jsp:include>
      </div>

      <div id="title-div">Simple EBOM App</div>
    </div>

  <div id="content-container">
    <jsp:include page="jsp/part/overview.jsp"></jsp:include>
  </div>

  </body>
</html>
