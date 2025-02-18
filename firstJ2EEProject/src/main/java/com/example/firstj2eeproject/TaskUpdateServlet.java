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

@WebServlet(urlPatterns = "/taskListUpdate")
public class TaskUpdateServlet extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();  // Initialize DAO instance
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> taskList = null;
        try {
            taskList = taskDAO.getAllTasks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("taskList", taskList);
        String taskName = request.getParameter("taskName");
        String taskDescription = request.getParameter("taskDescription");
        request.getRequestDispatcher("/WEB-INF/taskListUpdate.jsp").forward(request, response);
        System.out.println("This is the Update Page");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldTaskName = request.getParameter("taskName");
        String updatedTaskName = request.getParameter("updatedTaskName");
        String updatedTaskDescription = request.getParameter("updatedTaskDescription");
        // Call the DAO method to update the task in the database
        System.out.println("trying to post oldTaskName: " + oldTaskName);
        System.out.println("trying to post updatedTaskName: " + updatedTaskName);
        System.out.println("trying to post updatedTaskDescription: " + updatedTaskDescription);
        try {
            taskDAO.updateTask(updatedTaskName, updatedTaskDescription, oldTaskName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect back to the task list after the update
        response.sendRedirect("/firstJ2EEProject_war_exploded/taskList");

    }

}
