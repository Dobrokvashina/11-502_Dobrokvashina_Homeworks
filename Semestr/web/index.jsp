<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 04.11.2016
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Справочник абитуриента</title>
</head>

<div class="IndexDiv">

    <h1>Справочник абитуриента</h1>


    <c:if test="${current_user != null}">
        <a class="UpGoTo" href="/myPage">Личный кабинет</a><br>
        <a class="UpGoTo" href="/myPage?exit=true">Выйти из аккаунта </a><br>
    </c:if>
    <c:if test="${current_user == null}">
        <a class="UpGoTo" href="/login">Войти</a><br>
        <a class="UpGoTo" href="/registration">Зарегистрироваться</a><br>
    </c:if>

    <hr>

    <a class="link" href="/universities">Список университетов</a><br><br>
    <a class="link" href="/specialities">Список специальностей</a><br>


</div>
</body>
</html>

