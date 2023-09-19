window.onload = function() {

    const socket = new SockJS("/websocket-endpoint");
    const stomp = new Stomp.over(socket);

    const sendButton = document.querySelector("#send-button");
    sendButton.onclick = function() {
        const messageInput = document.querySelector("#message-input");
        const text = messageInput.value;

        stomp.send("/app/messages", {}, JSON.stringify({"requestText": text}));
    }

    stomp.connect({}, function() {
        stomp.subscribe("/topic/employees", function(frame) {
            console.log("Message has come: " + frame);
            const messageBody = JSON.parse(frame.body);
            const text = messageBody.responseText;
            const responseDiv = document.querySelector("#response-div");
            //responseDiv.innerHTML = "<p>" + text + "</p>";
            responseDiv.innerHTML += `<p>${text}</p>`;
        })
    })

}