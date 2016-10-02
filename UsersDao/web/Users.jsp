
<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 21.09.2016
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.itis.inform.User" %>
<%@ page import="ru.itis.inform.FileBasedUsersDaoImpl" %>
<%@ page import="java.io.File" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<%
    String name = "C:\\Users\\Саоша\\IdeaProjects\\UsersDao\\input\\users.txt";
    File file1 = new File(name);
    FileBasedUsersDaoImpl fileBasedUsersDao = new FileBasedUsersDaoImpl(file1);
    pageContext.setAttribute("users", fileBasedUsersDao.findAll());
%>
<table>
    <tbody>

    <tr> <th>ID</th> <th>Name</th> <th>City</th> <th>Age</th> </tr>
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

