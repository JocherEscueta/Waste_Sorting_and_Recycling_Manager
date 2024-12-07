package main;

import services.UserService;
import services.WasteService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private final UserService userService = new UserService();
    private final WasteService wasteService = new WasteService();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            if (userService.isLoggedIn()) {
                showLoggedInMenu();
            } else {
                showLoggedOutMenu();
            }
        }
    }

    private void showLoggedOutMenu() {
        int choice;
        loggedOutOptions();
        
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); 
            return; 
        }

        switch (choice) {
            case 1:
                userService.registerUser();
                break;
            case 2:
                userService.loginUser();
                break;
            case 3:
                exitApplication();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void loggedOutOptions() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private void showLoggedInMenu() {
        int choice;
        loggedInOptions();
        
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                wasteService.addWasteItemMenu(userService.getLoggedInUserId());
                break;
            case 2:
                wasteService.generateWasteReport(userService.getLoggedInUserId());
                break;
            case 3:
                wasteService.deleteWasteItemMenu(userService.getLoggedInUserId());
                break;
            case 4:
                wasteService.clearAllWaste(userService.getLoggedInUserId());
                break;
            case 5:
                logoutUser();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void loggedInOptions() {
        System.out.println("\n--- Logged In Menu ---");
        System.out.println("1. Add Waste Item");
        System.out.println("2. View Waste Report");
        System.out.println("3. Delete Specific Waste Item");
        System.out.println("4. Clear All Waste Items");
        System.out.println("5. Logout");
       
        System.out.print("Choose an option: ");
    }

    private void logoutUser() {
        userService.logout();
        System.out.println("You have been logged out.");
    }

    private void exitApplication() {
        System.out.println("Exiting the application.");
        System.exit(0);
    }
}
