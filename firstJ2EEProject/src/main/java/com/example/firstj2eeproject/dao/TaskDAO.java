package com.example.firstj2eeproject.dao;

import com.example.firstj2eeproject.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {


    public TaskDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }


    public List<Task> getAllTasks() throws SQLException {
        List<Task> taskList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //get the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododb", "root", "root123");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT TaskName, TaskDescription FROM tasklist");

            while (rs.next()) {
                Task task = new Task();
                task.setTaskName(rs.getString("TaskName"));
                task.setTaskDescription(rs.getString("TaskDescription"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logging framework
        } finally {
            assert stmt != null;
            stmt.close();
            conn.close();
        }
        return taskList;
    }

    public void addTask(String newTaskName, String newTaskDescription) throws SQLException {
        Task task = new Task();
        Connection conn = null;
        PreparedStatement stmt = null;  // Use PreparedStatement instead of Statement
        try {
            //get the connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododb", "root", "root123");
            String sql = "INSERT INTO tasklist (TaskName, TaskDescription) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            //Set the values
            stmt.setString(1, newTaskName);  // Set value for the first placeholder (TaskName)
            stmt.setString(2, newTaskDescription);  // Set value for the second placeholder (TaskDescription)
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task added successfully!");
            } else {
                System.out.println("No rows were inserted.");
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Consider using a logging framework
        } finally {
            assert stmt != null;
            stmt.close();
            conn.close();
        }
    }

    public void deleteTask(String taskName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododb", "root", "root123");
            String sql = "DELETE FROM tasklist WHERE taskName = ?";
            stmt = conn.prepareStatement(sql);
            // Set the values
            stmt.setString(1, taskName);  // Set value for the first placeholder (TaskName)
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("No rows were deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert stmt != null;
            stmt.close();
            conn.close();
        }
    }

    public void updateTask(String updatedTaskName, String updatedTaskDescription, String oldTaskName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododb", "root", "root123");
            String sql = "UPDATE tasklist SET taskName = ?, taskDescription = ? WHERE taskName = ?";
            stmt = conn.prepareStatement(sql);
            // Set the values
            stmt.setString(1, updatedTaskName);
            stmt.setString(2, updatedTaskDescription);
            stmt.setString(3, oldTaskName);  // Set value for the first placeholder (TaskName)
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task Updated successfully!");
            } else {
                System.out.println("No rows were updated.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert stmt != null;
            stmt.close();
            conn.close();
        }
    }

}

