const eventSource = new EventSource("api/event");
eventSource.addEventListener("message",
    function(event) {
        const text = JSON.parse(event.data).text;
        console.log(text);

        const messagesDiv = document.querySelector("#messages-div");
        messagesDiv.innerHTML += `<p>${text}</p>`;
    });