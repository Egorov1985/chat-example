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
<h4>Все пользователи </h4>
<#list users as user>
    <div>
        <p>  ${user.name}
            <a href="/user-info/${user.id}"> Подробнее о пользователи...</a>
        </p>
    </div>
<#else >
    <p> Пользователей нет! </p>
</#list>


</body>
</html>