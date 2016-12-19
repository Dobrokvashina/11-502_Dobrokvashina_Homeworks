<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 09.11.2016
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Личный кабинет</title>
</head>
<body>

<div class="myPageDiv">

    <h1>Рады видеть Вас, ${user.surname} ${user.name}</h1>

    <a class="UpGoTo" href="/index.jsp">Домой</a><br>
    <a class="UpGoTo" href="/myPage?exit=true">Выйти из аккаунта </a><br>

    <c:if test="${admin == null}">

        <p id="whatToDo">Здесь вы можете добавить или изменить баллы полученные за экзамен, а также добавить свои
            достижения. Или посмотреть, какие <a href="/myPage?specs=true">специальности вам доступны</a>, с нынешними данными</p>


        <hr>

        <form action="/myPage" method="post">

            <label class="labelLK">Баллы по предметам:</label><br><br>
            <c:forEach items="${subs}" var="sub">

                <label class="labelLK" for="sub+${sub.id}">${sub.name}</label> <br>
                <input name="${sub.name}" type="number" id="sub+${sub.id}" value="${sub.point}">
                <br>
            </c:forEach><br>


            <label class="labelLK">Достижения:</label><br><br>
            <c:forEach items="${achs}" var="ach">
                <label class="labelLK" for="ach+${ach.id}">${ach.type}</label> <br>
                <input id="ach+${ach.id}" name="${ach.type}" type="checkbox"
                <c:if test="${ach.chosen}"> checked</c:if>>
                <br>
            </c:forEach>

            <input type="submit" class="SubmitBotton">
        </form>
    </c:if>

</div>
</body>
</html>
