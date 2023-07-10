<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> users </title>
</head>
<body>
<br>
<hr>
<h4> Информация о пользователе ${user.name} </h4>
<div>
    <p> Аккаунт создан ${user.createDate} </p>
</div>

<div>
    <form action="/chatRoom/create" method="post">
        <input type="submit" value="Написать пользователю">
    </form>

</div>
</body>
</html>