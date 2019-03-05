var actualContentContainer = "overviewPage-content-container";
var actualOptionsContainer = "part-options-container";

function gebi(elementId) {
    return document.getElementById(elementId);
}

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

    desiredContentContainer += "-content-container";
    if (actualContentContainer !== desiredContentContainer) {
        xhr.open('GET', url);

        xhr.onreadystatechange = function () {
            var DONE = 4; // readyState 4 means the request is done.
            var OK = 200; // status 200 is a successful return.
            if (xhr.readyState === DONE) {
                if (xhr.status === OK) {
                    document.getElementById("content-container").innerHTML =
                        this.responseText;  // Response from URL
                    actualContentContainer = desiredContentContainer;
                } else {
                    console.log('Error: ' + xhr.status); // An error occurred during the request.
                }
            }
        };
        xhr.send(null);
    }

}

function chooseCategory(eventSourceId) {
    var xhr = new XMLHttpRequest();
    // Pre horne 4 buttony
    var urlCategory;
    // Pre category(P/L) overview
    var urlContent;
    var desiredOptionsContainer;
    // Aby sa mi po zmene kategorie(part <-> link) rovno nacitalo overview
    var desiredContentContainer = "overviewPage-content-container";

    switch (eventSourceId) {
        case "part-category-button":
            urlCategory = "jsp/part/p_option_container.jsp";
            urlContent = "jsp/part/overview.jsp";
            desiredOptionsContainer = "part-options-container";
            break;

        case "link-category-button":
            urlCategory = "jsp/link/l_option_container.jsp";
            urlContent = "jsp/link/overview.jsp";
            desiredOptionsContainer = "link-options-container";
            break;
    }

    if (actualOptionsContainer !== desiredOptionsContainer) {
        actualOptionsContainer = desiredOptionsContainer;
        // Po zmene kategorie zmenim aj content kontajner, inak by napr. z delete do delete nefungovalo
        // TODO NEFUNGUJE
        document.getElementById("part-connector").style.borderColor = "red";
        document.getElementById("link-connector").style.borderColor = "#1565c0";

        xhr.open('GET', urlCategory);

        xhr.onreadystatechange = function () {
            var DONE = 4; // readyState 4 means the request is done.
            var OK = 200; // status 200 is a successful return.

            if (xhr.readyState === DONE) {
                if (xhr.status === OK) {
                    // Container pre containery kategorii
                    document.getElementById("options-container").innerHTML =
                        this.responseText;


                } else {
                    console.log('Error: ' + xhr.status); // An error occurred during the request.
                }
            }
        };
        xhr.send(null);

        if (actualContentContainer != desiredContentContainer) {
            xhr.open('GET', urlContent);

            xhr.onreadystatechange = function () {
                var DONE = 4; // readyState 4 means the request is done.
                var OK = 200; // status 200 is a successful return.
                if (xhr.readyState === DONE) {
                    if (xhr.status === OK) {
                        document.getElementById("content-container").innerHTML =
                            this.responseText;
                        actualContentContainer = desiredContentContainer;
                    } else {
                        console.log('Error: ' + xhr.status); // An error occurred during the request.
                    }
                }
            };
            xhr.send(null);
        }
    }


}