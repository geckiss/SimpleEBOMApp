// tableId = update table
// clickedButtonId = row index(base is 0)

var expandedRows = [];
// TODO nejde, dorob
function expandRow(clickedButtonId) {

    /*
    if (expandedRows[clickedButtonId] === 0) {
        expandedRows[clickedButtonId] = 1;
    */
        var table = document.getElementById("part-update-table");
        var expandedTable;

        // Header is at 0, need to skip header and row with clicked button
        var row = table.insertRow(clickedButtonId + 2);
        // TODO co sa stane, ak kliknem na posledny riadok?
        var cols = 9;


        var cell;
        var input;
        var btnSpan;
        var deexpandButton;
        // Forma, ktora ma 6 inputov
        // Tuto formu musim dat do cellu ktora colspan = 7
        var updateForm = document.createElement("form");
        updateForm.action = "UpdatePart";
        updateForm.method = "post";
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
                itemId.name = "ID";
                itemId.value = clickedButtonId + 1;
                itemId.disabled = true;
                formTableColumn.appendChild(itemId);
                formTableRow.appendChild(formTableColumn);
            } else {

                if (i === 1 || i === 2 || i === 7) {

                    // Type a Name je String
                    input.type = "text";
                    if (i === 1) {
                        input.name = "Type";
                    } else if (i === 2) {
                        input.name = "Name";
                    } else {
                        input.type = "Comment"
                    }
                    formTableColumn.appendChild(input);
                    formTableRow.appendChild(formTableColumn);

                } else {
                    if (i === cols - 1) {
                        // do druheho stlpca dam formu s inputny a nastavim colspan = 7
                        // Teraz tu insertnutu cell povazuje za 2. stlpec
                        cell = row.insertCell(0);
                        // Teraz uz nie
                        cell.colSpan = 9;

                        // Kedze som v poslednom stlpci, posledny stlpec budu 2 buttony, ktore tiez patria do form
                        btnSpan = document.createElement("span");
                        deexpandButton = document.createElement("button");
                        deexpandButton.type = "button";
                        deexpandButton.value = "De-expand";
                        deexpandButton.addEventListener('click', function (clickedButtonId) {
                            var table = document.getElementById("part-update-table");
                            var row = table.insertRow(clickedButtonId + 2);
                            table.deleteRow(row);
                            expandedRows[clickedButtonId] = 0;
                        });

                        input.type = "submit";
                        input.name = "update-button";
                        input.value = "Update";

                        btnSpan.appendChild(deexpandButton);
                        btnSpan.appendChild(input);

                        formTableColumn.appendChild(btnSpan);

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
                                break;

                            case 6:
                                input.name = "Cost";
                                break;
                        }
                        input.type = "number";

                        formTableColumn.appendChild(input);
                        formTableRow.appendChild(formTableColumn);
                    }
                }

            }
        }


    // }
}
