FirstJ2EEProject - TODO Web APP
This is a simple Java-based web application built using Jakarta EE. The project demonstrates user
login functionality, task management, and session management with the use of servlets, JSP, and a simple DAO pattern.

Technologies Used
Jakarta EE
Servlet API
JSP (JavaServer Pages)
DAO Class
JDBC

Configuration
Task Servlet (TaskServlet.java)
The TaskServlet handles displaying the list of tasks, adding new tasks, and deleting tasks. It forwards requests to
taskList.jsp for viewing tasks.
Task Update Servlet (TaskUpdateServlet.java)
The TaskUpdateServlet handles displaying the update form and updating tasks. It retrieves task data, shows it in the
update form, and updates the task in the database when the form is submitted.
Task DAO (TaskDAO.java)
The TaskDAO class contains CRUD methods to interact with the database, including:
getAllTasks(): Retrieves all tasks from the database.
addTask(String name, String description): Adds a new task to the database.
deleteTask(String name): Deletes a task from the database based on its name.
updateTask(String updatedName, String updatedDescription, String oldName): Updates the task's name and description in
the database.

Database Connection
The project uses JDBC to interact with the database. Make sure the necessary JDBC driver is included in the project and
that the TaskDAO class is correctly configured to connect to your database.