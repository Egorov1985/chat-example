<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chat WebSocket</title>
    <script src="../js/sockjs-0.3.4.js"></script>
    <script src="../js/stomp.js"></script>
    <script src="/static/js/app.js">
    </script>
</head>

<div>
    <form action="/logout" method="post">
        <button type="submit"> Выйти с аккаунта</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<br>
<body onload="disconnect();">

<div>
    <div>
        <input type="text" id="from" placeholder="Choose a nickname"/>
    </div>
    <br/>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
    </div>
    <br/>
    <div id="conversationDiv">
        <input type="text" id="text" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();" >Send</button>
        <script src="../js/clear-input-form.js"></script>
        <p id="response"></p>
    </div>
</div>
</body>
</html>