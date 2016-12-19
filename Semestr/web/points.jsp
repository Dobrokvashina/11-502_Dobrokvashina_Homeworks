<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 19.12.2016
  Time: 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Личный кабинет(Баллы)</title>
</head>
<body>

<div class="myPageDiv">

    <h1>Рады видеть Вас, ${user.surname} ${user.name}</h1>

    <a class="UpGoTo" href="/myPage">Вернуться </a><br>
    <a class="UpGoTo" href="/index.jsp">Домой</a><br>
    <a class="UpGoTo" href="/myPage?exit=true">Выйти из аккаунта </a><br>

    <table>
        <tbody>
        <tr>
            <th>Специальность : баллы</th>
        </tr>

        <c:forEach items="${points}" var="point">
            <tr>
                <td><c:out value="${point}"></c:out></td>
            </tr>

        </c:forEach>

        </tbody>
    </table>

</div>
</body>
</html>

