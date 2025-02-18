package com.example.firstj2eeproject;

import com.example.firstj2eeproject.dao.TaskDAO;
import com.example.firstj2eeproject.model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/taskList")
public class TaskServlet extends HttpServlet {

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();  // Initialize DAO instance
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all tasks
        List<Task> taskList = null;
        try {
            taskList = taskDAO.getAllTasks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Set tasks to be used in JSP
        request.setAttribute("taskList", taskList);
        request.getRequestDispatcher("/WEB-INF/taskList.jsp").forward(request, response);
        System.out.println("TaskList size: " + taskList.size());
        System.out.println(taskList);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //add or delete
        String action = request.getParameter("action");
        //delete
        if ("delete".equals(action)) {
            String taskName = request.getParameter("taskName");
            System.out.println("taskName: " + taskName);
            if (taskName != null && !taskName.isEmpty()) {
                try {
                    taskDAO.deleteTask(taskName);
                } catch (NumberFormatException ignored) {
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            //add
        } else if ("add".equals(action)) {
            //Get the new task name and description
            String newTaskName = request.getParameter("newTaskName");
            String newTaskDescription = request.getParameter("newTaskDescription");
            try {
                taskDAO.addTask(newTaskName, newTaskDescription);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        List<Task> taskList = null;
        try {
            taskList = taskDAO.getAllTasks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("taskList", taskList);
        request.getRequestDispatcher("/WEB-INF/taskList.jsp").forward(request, response);
    }
}
