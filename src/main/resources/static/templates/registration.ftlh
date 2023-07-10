<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
</head>
<body>
<H4>Регистрация нового пользователя</H4>
<form action="/registration" method="post">

    Имя пользователя :
    <@spring.formInput "user.name"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Эл.почта :
    <@spring.formInput "user.email"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    Пароль :
    <@spring.formInput "user.password"/>
    <@spring.showErrors "<br>"/>
    <br><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

</body>
</html>