<%@ page import="com.example.firstj2eeproject.model.Task" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Task List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
</head>
<body>
<h2>Task List</h2>

<!--Form to CREATE-->
<h3>Add a New Task</h3>
<form action="/firstJ2EEProject_war_exploded/taskList" method="post">
    <!-- Adding action parameter to specify this is for adding -->
    <input type="hidden" name="action" value="add">
    <input type="text" name="newTaskName" placeholder="Enter Task Name" required>
    <br>
    <textarea name="newTaskDescription" placeholder="Enter Task Description" required></textarea>
    <br>
    <button type="submit">Add Task</button>
</form>

<br><br>

<!-- Task list table -->
<table border="1">
    <thead>
    <tr>
        <th>Task Name</th>
        <th>Task Description</th>
        <th>Actions</th> <!-- Added column for buttons -->
    </tr>
    </thead>
    <tbody>
    <%
        // Get the taskList from the request
        List<Task> taskList = (List<Task>) request.getAttribute("taskList");

        // Iterate over the taskList using a for loop
        if (taskList != null) {
            for (Task task : taskList) {
    %>
    <tr>
        <td><%= task.getTaskName() %></td>
        <td><%= task.getTaskDescription() %></td>
        <td>
            <!-- Edit Task UPDATE-->
            <form action="/firstJ2EEProject_war_exploded/taskListUpdate" method="get">
                <!-- Send the task name to be edited -->
                <input type="hidden" name="taskName" value="<%= task.getTaskName() %>">
                <input type="hidden" name="taskDescription" value="<%= task.getTaskDescription() %>">
                <input type="hidden" name="action" value="edit">
                <button type="submit">Edit</button>
            </form>
            <!--Delete form for DELETE-->
            <form action="/firstJ2EEProject_war_exploded/taskList" method="post">
                <!-- Send the task name and the delete action -->
                <input type="hidden" name="taskName" value="<%= task.getTaskName() %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">No tasks found.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
