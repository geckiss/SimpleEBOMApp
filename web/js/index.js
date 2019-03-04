var actualContentContainer = "overviewPage-content-container";

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
            url = "jsp/part/delete.jsp"
            desiredContentContainer = "deletePage";
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