window.onload = function() {
    const url = 'http://localhost:8080/api/employees';
    fetch(url)
            .then(function(response) {
                return response.json();
                })
            .then(function(jsonData) {
                const employeesTable = document.querySelector("#employees-table");
                jsonData.forEach(function(item) {
                    employeesTable.innerHTML +=
                        `<tr><td>${item.id}</td><td>${item.name}</td></tr>`;
                });
            });
}