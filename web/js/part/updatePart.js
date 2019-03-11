// tableId = update table
// clickedButtonId = row index(base is 0)

var expandedRows = [];

function expandPartRow(clickedButtonId) {

    var rowIndex = parseInt(clickedButtonId);
    var table = document.getElementById("part-update-table");
    /*
    // Lebo mozem mat expandnutych viacej riadkov, to chcem zachovat
    var oldExpandedRows = expandedRows.slice();

    for (var i = 0; i < table.getElementsByTagName("tr").length; i++) {
        expandedRows.push(0);
    }
    // Stare pole s hodnotami skopirujem do noveho, ktore moze byt zvacsene alebo zmensene
    expandedRows = oldExpandedRows.slice(0, expandedRows.length);

    if (expandedRows[rowIndex] === 0) {
        expandedRows[rowIndex] = 1;
    */

        var colWidth = table.getElementsByTagName("td").width;

        // Header is at 0, need to skip header and row with clicked button
        var row = table.insertRow(rowIndex + 2);
        // TODO co sa stane, ak kliknem na posledny riadok?
        var cols = 8;

        var cell;
        var input;
        var btnDiv;
        var deexpandButton;

        // Forma, ktora ma 6 inputov
        // Tuto formu musim dat do cellu ktora colspan = 7
        var updateForm = document.createElement("form");
        updateForm.setAttribute("id", "part-update-form");
        updateForm.method = "post";
        updateForm.action = "UpdatePart";


        // Do tej formy dam tabulku so stlpcami
        var formTable = document.createElement("table");
        var formTableRow = document.createElement("tr");
        var formTableColumn;

        for (var i = 0; i < cols; i++) {

            input = document.createElement("input");
            formTableColumn = document.createElement("td");

            if (i === 0) {
                // Len ID-cko
                var itemId = document.createElement("input");
                itemId.type = "number";
                itemId.name = "ItemId";
                itemId.value = table.rows[rowIndex + 1].cells[0].innerHTML;
                console.log(itemId);
                //itemId.disabled = true;
                formTableColumn.appendChild(itemId);
                formTableRow.appendChild(formTableColumn);
            } else {

                if (i === 1 || i === 2) {

                    // Type a Name je String
                    input.type = "text";
                    if (i === 1) {
                        input.name = "Type";
                    } else if (i === 2) {
                        input.name = "Name";
                    }
                    formTableColumn.appendChild(input);
                    formTableRow.appendChild(formTableColumn);

                } else {
                    if (i === cols - 1) {
                        // do druheho stlpca dam formu s inputny a nastavim colspan = 7
                        // Teraz tu insertnutu cell povazuje za 2. stlpec
                        cell = row.insertCell(0);
                        // Teraz uz nie
                        cell.colSpan = 8;

                        // Kedze som v poslednom stlpci, posledny stlpec budu 2 buttony, ktore tiez patria do form
                        btnDiv = document.createElement("div");
                        deexpandButton = document.createElement("button");
                        deexpandButton.type = "button";
                        deexpandButton.value = "De-expand";
                        deexpandButton.onclick = function() {
                            var table = document.getElementById("part-update-table");
                            //expandedRows[rowIndex] = 0;
                            table.deleteRow(rowIndex + 2);
                        };

                        input.type = "submit";
                        input.name = "update-button";
                        input.value = "Update";
                        /*
                        // Ako sem dostat vsetky hodnoty z inputov
                        input.onclick = function() {
                            var http = new XMLHttpRequest();
                            var url = 'UpdatePart';
                            var params = 'ID=' + (clickedButtonId + 1);
                            params += "&Type"
                            http.open('POST', url, true);

                            //Send the proper header information along with the request
                            http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

                            http.onreadystatechange = function() {//Call a function when the state changes.
                                if(http.readyState == 4 && http.status == 200) {
                                    alert(http.responseText);
                                }
                            };
                            http.send(params);
                        };
                        */

                        btnDiv.appendChild(deexpandButton);
                        btnDiv.appendChild(input);

                        formTableColumn.appendChild(btnDiv);

                        // ˇ Nechytat ˇ
                        formTableRow.appendChild(formTableColumn);
                        formTable.appendChild(formTableRow);
                        updateForm.appendChild(formTable);
                        cell.appendChild(updateForm);
                    } else {

                        switch (i) {
                            case 3:
                                input.name = "Length";
                                break;

                            case 4:
                                input.name = "Width";
                                break;

                            case 5:
                                input.name = "Weight";
                                input.step = "0.01";
                                break;

                            case 6:
                                input.name = "Cost";
                                input.step = "0.01";
                                break;
                        }
                        input.type = "number";

                        formTableColumn.appendChild(input);
                        formTableRow.appendChild(formTableColumn);
                    }
                }

            }
        }


    //}
}
