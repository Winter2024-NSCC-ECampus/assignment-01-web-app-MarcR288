<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Marc_
  Date: 2025-02-10
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ6vkk23f4a8a1hu4O5N7z1/dXz8wC53uZb2pOUOwUfsLzP5Z+b5eUy0kZ3y" crossorigin="anonymous">
</head>
<!--This is a scriplet to run java-->
<%
    Date date = new Date();
%>
<body>
<h1>Current date is <%=date%></h1>
Login to TODO App<br>
<%--Hello ${name} <br>--%>
<%--Your password is: ${password}--%>

<form action="/firstJ2EEProject_war_exploded/login" method="post">
    Name: <input type="text" name="name"/> Password:<input type="text" name="password"/> <input type="submit" value="Submit">
</form>
${message}
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb6O0d0Pgyk6S6w6d9sDk5f6hw9q8lWjjfXyWhX+0QK5EXkL7" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0Jv5T3v7jqyWQllk1p4OBbPfmN1kXc9tV2h34As8fS4ls9D6" crossorigin="anonymous"></script>
</body>
</html>
