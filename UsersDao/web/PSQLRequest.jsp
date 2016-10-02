<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 25.09.2016
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.itis.inform.JdbcImpl" %>
<%@ page import="ru.itis.inform.User" %>
<%@ page import="org.postgresql.*" %>
<%@ page import="ru.itis.inform.UsersDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DataBase</title>
    <style>
        table,th,td
        {
            border:1px solid black;
        }
    </style>
</head>
<body>

    <table>
        <tbody>

        <tr> <th>ID</th> <th>Name</th> <th>City</th> <th>Age</th> </tr>
        <%
            UsersDao users = new JdbcImpl("jdbc:postgresql://localhost:5432/UsersCars", "postgres", "female");
            pageContext.setAttribute("users", users.findAll());
        %>
        <c:forEach items="${users}" var="user" >
            <tr>
                <td><c:out value="${user.id}"></c:out> </td>
                <td><c:out value="${user.name}"></c:out> </td>
                <td><c:out value="${user.city}"></c:out> </td>
                <td><c:out value="${user.age}"></c:out> </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

</body>
</html>
