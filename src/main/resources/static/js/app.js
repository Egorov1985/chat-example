var StompClient = null;

function setConnected(connected){
    $("connect").prop("disabled", connected);
    $("disconnect").prop("disabled", !connected);

    if (connected){
        $("conversation").show();
    } else {
        if (connected){
            $("conversation").hide();
        }
    }
}

function connect(){
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame){
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user' + current.id + "/queue/messages",
            function (message){
            showMessage(JSON.parse(message))
            });
    });

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendMessage(message){
        stompClient.send("/app/chat", {}, JSON.stringify(message));
    }

}