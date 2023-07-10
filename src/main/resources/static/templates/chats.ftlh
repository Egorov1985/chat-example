<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> messages </title>
</head>
<body>
<div>
    <form action="/logout" method="post">
        <button type="submit"> Выйти с аккаунта</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<br>
<hr>
<h4>Мои чаты</h4>
<#list chatRoomList as chat>
    <div>
        <p>  ${chat.creatDateChat}
            <a href="/chatRoom/${chat.id}"> зайти в чат </a>
        </p>
    </div>
<#else >
    <p> Чатов нет </p>
</#list>

<div>
    <form action="/createChatRoom" method="post">
        <input type="text" name="chatRoomName" placeholder="Введите назавание чата" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button> Создать чат</button>
    </form>
</div>

</body>
</html>