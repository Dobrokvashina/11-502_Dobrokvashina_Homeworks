<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 06.11.2016
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Регистрация</title>
</head>
<body>

<div class="RegDiv">

<h1>Регистрация</h1>

<p id="whatToDo">Для регистрации заполните форму</p>

<hr>

<form action="/registration" method="post">

    <label for="FirstName">Имя</label> <br>
    <input name="name" type="text" id="FirstName" placeholder="Имя">
    <br>
    <label for="LastName">Фамилия</label> <br>
    <input name="surname" type="text" id="LastName" placeholder="Фамилия">
    <br>
    <label for="Country">Страна</label> <br>
    <input name="country" type="text" id="Country" placeholder="Страна">
    <br>
    <label for="City">Город</label> <br>
    <input name="city" type="text" id="City" placeholder="Город">
    <br>
    <label for="Login">Логин</label> <br>
    <input name="login" type="text" id="Login" placeholder="Логин">
    <br>
    <label for="Password1">Пароль</label> <br>
    <input name="password1" type="password1" id="Password1" placeholder="Пароль">
    <br>
    <label for="Password2">Повторите пароль</label> <br>
    <input name="password2" type="password2" id="Password2" placeholder="Подтверждение">
    <br>

</form>

<a class="GoTo" href="/login">Уже есть аккаунт?</a><br>
<a class="GoTo" href="/index.jsp">Вернуться на главную</a><br>

</div>
</body>
</html>
