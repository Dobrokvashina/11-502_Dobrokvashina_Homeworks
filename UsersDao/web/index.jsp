<%--
  Created by IntelliJ IDEA.
  User: Саоша
  Date: 28.10.2016
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<html>
<head>
  <title>Code</title>
</head>
<body>


<%

  List list = new LinkedList();
  try {
    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UsersCars", "postgres", "female");
    Statement st = con.createStatement();

    ResultSet res = st.executeQuery(
            "SELECT * FROM Users"
    );

   list = new LinkedList<String>();
    while (res.next()) {

      list.add(res.getInt("user_id") + ", " + res.getString("user_name") + ", " + res.getString("user_city")+ ", " + res.getInt("user_age"));
    }




  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (SQLException e) {
    e.printStackTrace();
  }


  request.setAttribute("list", list);
%>



<c:forEach items="${list}" var="user" >
  <p><c:out value="${user}"></c:out></p>
</c:forEach>



</body>
</html>