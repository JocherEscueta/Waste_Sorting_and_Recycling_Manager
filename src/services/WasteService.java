package services;

import dao.WasteItemDAO;
import models.WasteItem;
import models.wasteTypes.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WasteService {
    private final WasteItemDAO wasteItemDAO = new WasteItemDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void addWasteItemMenu(int userId) {
        System.out.println("\n--- Add Waste Item ---");
        System.out.print("Enter waste name (or press Enter to return): ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Returning to menu.");
            return;
        }

        System.out.println("Select a waste type:");
        String[] options = {"Organic", "Plastic", "Metal", "Glass", "E-Waste"};
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int typeChoice;
        while (true) {
            System.out.print("Enter the number corresponding to the waste type: ");
            typeChoice = getIntInput();
            if (typeChoice > 0 && typeChoice <= options.length) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        String type = options[typeChoice - 1];

        System.out.print("Enter weight (in kg): ");
        double weight = getDoubleInput();

        addWasteItem(name, type, weight, userId);
    }

    private void addWasteItem(String name, String type, double weight, int userId) {
        WasteItem wasteItem;

        switch (type.toLowerCase()) {
            case "organic":
                wasteItem = new OrganicWaste(name, weight);
                break;
            case "plastic":
                wasteItem = new PlasticWaste(name, weight);
                break;
            case "metal":
                wasteItem = new MetalWaste(name, weight);
                break;
            case "glass":
                wasteItem = new GlassWaste(name, weight);
                break;
            case "e-waste":
                wasteItem = new EWaste(name, weight);
                break;
            default:
                System.out.println("Invalid waste type.");
                return;
        }

        wasteItemDAO.addWasteItem(wasteItem, userId);
    }

    public void deleteWasteItemMenu(int userId) {
        System.out.println("\n--- Delete Specific Waste Item ---");
    
        wasteItemDAO.listWasteItems(userId);
    
        System.out.print("Enter the ID of the waste item to delete (or press Enter to return): ");
        int wasteId = getIntInput();

        if (Integer.toString(wasteId).trim().isEmpty()) {
            System.out.println("Returning to menu.");
            return;
        }
    
        boolean success = wasteItemDAO.deleteWasteItem(wasteId, userId);
        if (success) {
            System.out.println("Waste item deleted successfully.");
        } else {
            System.out.println("Failed to delete the waste item. Please check the ID.");
        }
    }
    
    public void clearAllWaste(int userId) {
        System.out.println("\n--- Clear All Waste Items ---");
        System.out.print("Are you sure you want to delete all waste items? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
    
        if (confirmation.equals("yes")) {
            boolean success = wasteItemDAO.deleteAllWasteItems(userId);
            if (success) {
                System.out.println("All waste items cleared successfully.");
            } else {
                System.out.println("Failed to clear all waste items.");
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    public void generateWasteReport(int userId) {
        System.out.println("\n--- Waste Report ---");

        wasteItemDAO.generateWasteReport(userId);
    }

    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
