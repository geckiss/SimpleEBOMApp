function expandPartRow(clickedButtonId) {

    var rowIndex = parseInt(clickedButtonId);
    var table = document.getElementById("part-update-table");

    // Header is at 0, need to skip header and row with clicked button
    var row = table.insertRow(rowIndex + 2);
    var cols = 8;

    var cell;
    var input;
    var btnSpan;
    var deexpandButton;

    // Kedze prvy input s idckom nie je disabled(lebo by nefungoval servlet)
    // Musim zistit, ake je max ID v DB
    // Ked uz nemozem osetrovat priamo hodnoty, ale len min a max
    var maxId = table.rows[table.getElementsByTagName("tr").length - 1].cells[0].innerHTML;

    // Forma, ktora ma 6 inputov
    // Tuto formu musim dat do cellu ktora ma nejaky colspan
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
            input.type = "number";
            input.name = "ItemId";
            input.value = table.rows[rowIndex + 1].cells[0].innerHTML;
            input.min = "1";
            input.max = maxId.toString();
            //itemId.disabled = true;
            formTableColumn.appendChild(input);
            formTableRow.appendChild(formTableColumn);
        } else {
            if (i === 1 || i === 2) {
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
                    // Do druheho stlpca dam formu s inputmi a nastavim colspan
                    cell = row.insertCell(0);
                    cell.colSpan = 8;

                    // Kedze som v poslednom stlpci, posledny stlpec budu 2 buttony, ktore tiez patria do form
                    btnSpan = document.createElement("span");
                    deexpandButton = document.createElement("button");
                    deexpandButton.type = "button";
                    deexpandButton.innerHTML = "De-expand";
                    deexpandButton.className = "update-buttons";
                    deexpandButton.onclick = function() {
                        var table = document.getElementById("part-update-table");
                        table.deleteRow(rowIndex + 2);
                    };

                    input.type = "submit";
                    input.name = "update-button";
                    input.className = "update-buttons";
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
                            input.step = "0.01";
                            break;

                        case 6:
                            input.name = "Cost";
                            input.step = "0.01";
                            break;
                    }
                    input.type = "number";
                    input.min = "0";

                    formTableColumn.appendChild(input);
                    formTableRow.appendChild(formTableColumn);
                }
            }
        }
    }
}
