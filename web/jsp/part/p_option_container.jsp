<%--
  Created by IntelliJ IDEA.
  User: Mato
  Date: 5.3.2019
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
    <body>
        <div id="part-options-container">
            <span class="part-option-span">
              <button class="menu-buttons" id="overview-button" type="button" onclick="chooseOption(this.id)">
                  Parts Overview
              </button>
            </span>
            <span class="part-option-span">
              <button class="menu-buttons" id="add-button" type="button" onclick="chooseOption(this.id)">
                  Add Part
              </button>
            </span>
            <span class="part-option-span">
              <button class="menu-buttons" id="update-button" type="button" onclick="chooseOption(this.id)">
                  Update Part
              </button>
            </span>
            <span class="part-option-span">
              <button class="menu-buttons" id="delete-button" type="button" onclick="chooseOption(this.id)">
                  Delete Part
              </button>
            </span>
        </div>
    </body>
</html>
