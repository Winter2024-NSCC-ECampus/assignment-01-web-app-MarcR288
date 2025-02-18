package com.example.firstj2eeproject;

public class UserValidationService {

    public UserValidationService() {
    }

    public boolean isUserValid(String user, String password) {
        return user.equals("admin") && password.equals("admin");
    }
}
