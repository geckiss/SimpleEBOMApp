// tableId = update table
// clickedButtonId = row index(base is 0)

function expandLinkRow(clickedButtonId) {
    /*
    if (expandedRows[clickedButtonId] === 0) {
        expandedRows[clickedButtonId] = 1;
    */

    var rowIndex = parseInt(clickedButtonId);
    var table = document.getElementById("link-update-table");

    // Header is at 0, need to skip header and row with clicked button
    var row = table.insertRow(rowIndex + 2);
    var cols = 4;

    var cell;
    var input;
    var btnDiv;
    var deexpandButton;

    // Kedze prvy input s idckom nie je disabled(lebo by nefungoval servlet)
    // Musim zistit, ake je max ID v DB
    // Ked uz nemozem osetrovat priamo hodnoty, ale len min a max
    var maxId = table.rows[table.getElementsByTagName("tr").length - 1].cells[0].innerHTML;

    // Forma, ktora ma 6 inputov
    // Tuto formu musim dat do cellu ktora colspan = 7
    var updateForm = document.createElement("form");
    updateForm.action = "UpdateLink";
    updateForm.method = "post";

    // Do tej formy dam tabulku so stlpcami
    var formTable = document.createElement("table");
    var formTableRow = document.createElement("tr");
    var formTableColumn;

    for (var i = 0; i < cols; i++) {

        input = document.createElement("input");
        formTableColumn = document.createElement("td");

        // Vsetko su cisla
        input.type = "number";
        if (i === 0) {
            // Len ID-cko linku
            input.name = "LinkId";
            input.value = table.rows[rowIndex + 1].cells[0].innerHTML;
            input.min = "1";
            input.max = maxId.toString();
            formTableColumn.appendChild(input);
            formTableRow.appendChild(formTableColumn);
        } else {

            if (i === 1) {
                input.name = "Part1Id";
            } else if (i === 2) {
                input.name = "Part2Id";
            }
            input.min = "1";    // max?...
            formTableColumn.appendChild(input);
            formTableRow.appendChild(formTableColumn);

            if (i === cols - 1) {
                // do druheho stlpca dam formu s inputny a nastavim colspan = 7
                // Teraz tu insertnutu cell povazuje za 2. stlpec
                cell = row.insertCell(0);
                // Teraz uz nie
                cell.colSpan = 4;

                // Kedze som v poslednom stlpci, posledny stlpec budu 2 buttony, ktore tiez patria do form
                btnDiv = document.createElement("div");
                deexpandButton = document.createElement("button");
                deexpandButton.type = "button";
                deexpandButton.innerHTML = "De-expand";
                deexpandButton.className = "update-buttons";
                deexpandButton.onclick = function () {
                    var table = document.getElementById("link-update-table");
                    table.deleteRow(rowIndex + 2);
                };

                input.type = "submit";
                input.name = "update-button";
                input.value = "Update";
                input.className = "update-buttons";

                btnDiv.appendChild(deexpandButton);
                btnDiv.appendChild(input);

                formTableColumn.appendChild(btnDiv);

                // ˇ Nechytat ˇ
                formTableRow.appendChild(formTableColumn);
                formTable.appendChild(formTableRow);
                updateForm.appendChild(formTable);
                cell.appendChild(updateForm);

            }
        }
    }
}
