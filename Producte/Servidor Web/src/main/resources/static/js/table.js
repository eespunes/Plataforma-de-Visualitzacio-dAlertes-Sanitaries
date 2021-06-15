function asc(index) {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("table");
    switching = true;

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;

            x = rows[i].getElementsByTagName("TD")[index];
            y = rows[i + 1].getElementsByTagName("TD")[index];

            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}

function desc(index) {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("table");
    switching = true;

    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;

            x = rows[i].getElementsByTagName("TD")[index];
            y = rows[i + 1].getElementsByTagName("TD")[index];

            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}

function search(index) {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("s" + index);
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[index];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function appear(index) {
    let search, searchIcon, asc, desc;
    search = document.getElementById("s" + index);
    searchIcon = document.getElementById("b" + index);
    asc = document.getElementById("a" + index);
    desc = document.getElementById("d" + index);

    search.style.display = "block";
    searchIcon.style.display = "none"
    asc.style.display = "none"
    desc.style.display = "none"

    search.focus();
}

function disappear(index) {
    let search, searchIcon, asc, desc;
    search = document.getElementById("s" + index);
    searchIcon = document.getElementById("b" + index);
    asc = document.getElementById("a" + index);
    desc = document.getElementById("d" + index);

    search.style.display = "none";
    searchIcon.style.display = ""
    asc.style.display = ""
    desc.style.display = ""
}

function rowClicked(value) {
    location.href = value;
}
