package services;

import dao.UserDAO;
import models.User;
import java.util.Scanner;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private int loggedInUserId = -1;
    private final Scanner scanner = new Scanner(System.in);

    public void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!register(username, password)) {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!login(username, password)) {
            System.out.println("Login failed. Please try again.");
        }
    }

    public boolean register(String username, String password) {
        if (userDAO.userExists(username)) {
            System.out.println("Username already taken.");
            return false;
        }
        userDAO.addUser(new User(username, password));
        System.out.println("Registration successful.");
        return true;
    }

    public boolean login(String username, String password) {
        int userId = userDAO.login(username, password);
        if (userId != -1) {
            loggedInUserId = userId;
            System.out.println("Login successful.");
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void logout() {
        loggedInUserId = -1;
        System.out.println("Logged out.");
    }

    public int getLoggedInUserId() {
        return loggedInUserId;
    }

    public boolean isLoggedIn() {
        return loggedInUserId != -1;
    }
}
