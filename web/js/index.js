var actualContentContainer = "overviewPage-content-container";
var actualOptionsContainer = "part-options-container";
var actualButton = "overview-button";

function chooseOption(eventSourceId) {
    var xhr = new XMLHttpRequest();
    var url;
    var desiredContentContainer = "";
    switch (eventSourceId) {
        case "overview-button":
            url = "jsp/part/overview.jsp";
            desiredContentContainer = "overviewPage";
            break;

        case "add-button":
            url ="jsp/part/add.jsp";
            desiredContentContainer = "addPage";
            break;

        case "update-button":
            url = "jsp/part/update.jsp";
            desiredContentContainer = "updatePage";
            break;

        case "delete-button":
            url = "jsp/part/delete.jsp";
            desiredContentContainer = "deletePage";
            break;

        case "l-overview-button":
            url = "jsp/link/overview.jsp";
            desiredContentContainer = "overviewPage";
            break;

        case "l-tree-button":
            url = "jsp/link/link_tree.jsp";
            desiredContentContainer = "linkTree";
            break;

        case "l-add-button":
            url = "jsp/link/add.jsp";
            desiredContentContainer = "addPage";
            break;

        case "l-update-button":
            url = "jsp/link/update.jsp";
            desiredContentContainer = "updatePage";
            break;

        case "l-delete-button":
            url = "jsp/link/delete.jsp"
            desiredContentContainer = "deletePage";
            break;
    }

    if (actualButton != eventSourceId) {
        changeButtonAppearance(actualButton, eventSourceId);
        actualButton = eventSourceId.slice();
    }

    desiredContentContainer += "-content-container";
    if (actualContentContainer !== desiredContentContainer) {
        xhr.open('GET', url);

        xhr.onreadystatechange = function () {
            var DONE = 4;
            var OK = 200;
            if (xhr.readyState === DONE) {
                if (xhr.status === OK) {
                    document.getElementById("content-container").innerHTML = this.responseText;
                    actualContentContainer = desiredContentContainer;
                } else {
                    console.log('Error: ' + xhr.status);
                }
            }
        };
        xhr.send(null);
    }
}

function changeButtonAppearance(oldButtonId, newButtonId) {
    var oldBtn = document.getElementById(oldButtonId);
    oldBtn.style.backgroundColor = "white";
    oldBtn.style.color = "black";

    var newBtn = document.getElementById(newButtonId);
    newBtn.style.backgroundColor = "#ff3300";
    newBtn.style.color = "white";
}

function chooseCategory(eventSourceId) {
    var xhr1 = new XMLHttpRequest();
    // Pre horne 4 buttony(Overview, Add, Update, Delete)
    var urlCategory;
    // Pre part/link overview
    var urlContent;
    var desiredOptionsContainer;
    // Aby sa mi po zmene kategorie(part <-> link) rovno nacitalo overview
    var desiredContentContainer = "overviewPage-content-container";
    var clickedBtn;

    switch (eventSourceId) {
        case "part-category-button":
            urlCategory = "jsp/part/p_option_container.jsp";
            urlContent = "jsp/part/overview.jsp";
            desiredOptionsContainer = "part-options-container";
            actualButton = "overview-button"; // Aby fungovali zmeny farieb
            break;

        case "link-category-button":
            urlCategory = "jsp/link/l_option_container.jsp";
            urlContent = "jsp/link/overview.jsp";
            desiredOptionsContainer = "link-options-container";
            actualButton = "l-overview-button"; // Aby fungovali zmeny farieb
            break;
    }

    if (actualOptionsContainer !== desiredOptionsContainer) {
        // Po zmene kategorie zmenim aj content kontajner, inak by napr. z delete do delete nefungovalo
        if (actualOptionsContainer === "part-options-container") {
            document.getElementById("part-connector").style.borderColor = "#1565c0";
            document.getElementById("link-connector").style.borderColor = "red";
            document.getElementById("part-connector").style.width = "305px";
            document.getElementById("link-connector").style.width = "305px";
            clickedBtn = document.getElementById("link-category-button");
            clickedBtn.style.backgroundColor = "#ff3300";
            clickedBtn.style.color = "white";
            clickedBtn.style.textShadow = "2px 2px 2px 2px #ff9933";
            clickedBtn = document.getElementById("part-category-button");
            clickedBtn.style.backgroundColor = "white";
            clickedBtn.style.color = "black";
            clickedBtn.style.textShadow = "none";

        } else {
            document.getElementById("part-connector").style.borderColor = "red";
            document.getElementById("link-connector").style.borderColor = "#1565c0";
            document.getElementById("part-connector").style.width = "360px";
            document.getElementById("link-connector").style.width = "360px";
            clickedBtn = document.getElementById("part-category-button");
            clickedBtn.style.backgroundColor = "#ff3300";
            clickedBtn.style.color = "white";
            clickedBtn.style.textShadow = "2px 2px 2px 2px #ff9933";
            clickedBtn = document.getElementById("link-category-button");
            clickedBtn.style.backgroundColor = "white";
            clickedBtn.style.color = "black";
            clickedBtn.style.textShadow = "none";
        }

        actualOptionsContainer = desiredOptionsContainer.slice();

        xhr1.open('GET', urlCategory);
        xhr1.onreadystatechange = function() {
            var DONE = 4;
            var OK = 200;
            if (xhr1.readyState === DONE) {
                if (xhr1.status === OK) {
                    document.getElementById("options-container").innerHTML = this.responseText;
                } else {
                    console.log('Error: ' + xhr1.status);
                }
            }
        };
        xhr1.send(null);

        var xhr2 = new XMLHttpRequest();
        // Change content to overview
        xhr2.open('GET', urlContent);
        xhr2.onreadystatechange = function () {
            var DONE = 4;
            var OK = 200;
            if (xhr2.readyState === DONE) {
                if (xhr2.status === OK) {
                    document.getElementById("content-container").innerHTML = this.responseText;
                    actualContentContainer = desiredContentContainer;
                } else {
                    console.log('Error: ' + xhr2.status);
                }
            }
        };
        xhr2.send(null);
    }
}