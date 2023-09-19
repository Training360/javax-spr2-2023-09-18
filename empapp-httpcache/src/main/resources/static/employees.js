window.onload = function () {
    const fetchButton = document.getElementById("fetch-button");
    fetchButton.onclick = function() {
            const url = 'api/employees';
            fetch(url)
                    .then(function(response) {
                        return response.json();
                        })
                    .then(function(jsonData) {
                        let names = "";
                        jsonData.forEach(function(item) {
                            names += item["name"] + ", ";
                        });
                        const namesSpan = document.getElementById("names-span");
                        namesSpan.innerHTML = names;
                    });

    }

}