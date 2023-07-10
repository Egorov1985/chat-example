<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h4>Авторизация</h4>
<form action="/login" method="post">
    <p><label for="email"> Эл. почта </label>
        <input type="email" name="username"><br><br>
    </p>
    <p><label for="password"> Пароль </label>
        <input type="password" name="password"><br><br>
    </p>
    <p>
        <input type="checkbox" name="remember-me"/> Запомнить меня
    </p>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit"> Войти </button>
</form>
<br>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>