<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 09.11.2016
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>О специальности</title>
</head>
<body>
<div class="TablesDiv">

    <c:if test="${current_user != null}">
        <a class="UpGoTo" href="/myPage">Личный кабинет</a><br>
        <a class="UpGoTo" href="/myPage?exit=true">Выйти из аккаунта </a><br>
    </c:if>
    <c:if test="${current_user == null}">
        <a class="UpGoTo" href="/login">Войти</a><br>
        <a class="UpGoTo" href="/registration">Зарегистрироваться</a><br>
    </c:if>

    <c:if test="${change == null}">

        <h1>Специальность: ${spec.name}</h1><br>
        <hr>
        <p class="labelLK">Предметы:
            <c:forEach items="${spec.subjects}" var="sub">
                ${sub.name}
            </c:forEach>
            <br>
            <c:if test="${spec.about !=null}">
                О специальности: ${spec.about}<br>
            </c:if>
            Университеты, имеющие эту специальность:</p><br>

        <table>
            <tbody>
            <tr>
                <th>Университет</th>
                <th>Страна</th>
                <th>Город</th>
            </tr>

            <c:forEach items="${univs}" var="univ">
                <tr>
                    <td><a href="/universities?id=${univ.id}">${univ.name}</a></td>
                    <td><c:out value="${univ.country}"></c:out></td>
                    <td><c:out value="${univ.city}"></c:out></td>
                </tr>

            </c:forEach>

            </tbody>
        </table>

        <a class="GoTo" href="/specialities"> Вернуться к списку специальностей</a><br>
        <a class="GoTo" href="/index.jsp">Домой</a><br>


    </c:if>

    <c:if test="${(admin != null)&& (change != null)}">

        <form action="/specialities?id=${spec.id}" method="post" >

            <label class="labelLK" for="specName">Специальность: </label>
            <input name="specName" type="text" id="specName" value="${spec.name}">
            <br>

            <label class="labelLK" for="specAbout">О специальности: </label>
            <input name="specAbout" type="text" id="specAbout" value="${spec.about}">
            <br>

            <label class="labelLK">Предметы:</label><br><br>
            <c:forEach items="${subjects}" var="ach">
                <label  class="labelLK" for="sub+${ach.id}">${ach.name}</label> <br>
                <input id="sub+${ach.id}" name="${ach.name}" type="checkbox"
                <c:if test="${spec.hasSubject(ach.id)}"> checked</c:if>>
                <br>
            </c:forEach>

            <p class="labelLK">Университеты, имеющие эту специальность:<br>
               (изменить данные о принадлежности к университетам можно на странице университета)</p><br>

            <table>
                <tbody>
                <tr>
                    <th>Университет</th>
                    <th>Страна</th>
                    <th>Город</th>
                </tr>

                <c:forEach items="${univs}" var="univ">
                    <tr>
                        <td><a href="/universities?id=${univ.id}">${univ.name}</a></td>
                        <td><c:out value="${univ.country}"></c:out></td>
                        <td><c:out value="${univ.city}"></c:out></td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

            <input type="submit" class="SubmitBotton">
        </form>


        <a class="GoTo" href="/specialities"> Вернуться к списку без сохранения</a><br>
        <a class="GoTo" href="/index.jsp">Домой без сохранения</a><br>

    </c:if>

</div>
</body>
</html>
