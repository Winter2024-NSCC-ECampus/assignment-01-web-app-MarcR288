<%--
  Created by IntelliJ IDEA.
  User: Marc_
  Date: 2025-02-14
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Task</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h2>Update Task</h2>
<form action="/firstJ2EEProject_war_exploded/taskListUpdate" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="taskName" value="<%= request.getParameter("taskName") %>">
    <input type="text" name="updatedTaskName" value="<%= request.getParameter("taskName") %>" required>
    <br>
    <textarea name="updatedTaskDescription" required><%= request.getParameter("taskDescription") %></textarea>
    <br>
    <button type="submit">Update Task</button>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

