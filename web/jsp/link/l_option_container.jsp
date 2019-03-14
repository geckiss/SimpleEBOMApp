<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 5.3.2019
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
    <body>
        <div id="link-options-container">
            <span class="link-option-span">
              <button class="menu-buttons" id="l-overview-button" type="button" onclick="chooseOption(this.id)">
                  Links Overview
              </button>
            </span>
            <span class="link-option-span">
              <button class="menu-buttons" id="l-tree-button" type="button" onclick="chooseOption(this.id)">
                  Link Tree
              </button>
            </span>
            <span class="link-option-span">
              <button class="menu-buttons" id="l-add-button" type="button" onclick="chooseOption(this.id)">
                  Add Link
              </button>
            </span>
            <span class="link-option-span">
              <button class="menu-buttons" id="l-update-button" type="button" onclick="chooseOption(this.id)">
                  Update Link
              </button>
            </span>
            <span class="link-option-span">
              <button class="menu-buttons" id="l-delete-button" type="button" onclick="chooseOption(this.id)">
                  Delete Link
              </button>
            </span>
        </div>
    </body>
</html>

