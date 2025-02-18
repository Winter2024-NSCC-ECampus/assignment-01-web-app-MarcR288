package com.example.firstj2eeproject;

import com.example.firstj2eeproject.dao.TaskDAO;
import com.example.firstj2eeproject.model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();  // Initialize DAO instance
    }

    private final UserValidationService userValidationService = new UserValidationService();

    //for invalid login
    String message = "Invalid Username or password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //getting parameter

        //
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("password", request.getParameter("password"));

        //use login.jsp
       request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String user = request.getParameter("name");
        String password = request.getParameter("password");
        boolean isUserValid = userValidationService.isUserValid(user, password);

        //Successful Login
        if (isUserValid) {
            HttpSession session = request.getSession();
            session.setAttribute("name", user);
            session.setAttribute("password", password);
            List<Task> taskList;
            try {
                taskList = taskDAO.getAllTasks();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("taskList", taskList);
            request.getRequestDispatcher("/WEB-INF/taskList.jsp").forward(request, response);
        }

        //Unsuccessful Login
        else{
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
