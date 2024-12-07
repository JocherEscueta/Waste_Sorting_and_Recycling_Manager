package dao;

import config.DatabaseConnection;
import models.WasteItem;
import java.sql.*;

public class WasteItemDAO {
    private final Connection connection;

    public WasteItemDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed.", e);
        }
    }

    public void addWasteItem(WasteItem wasteItem, int userId) {
        String sql = "INSERT INTO waste_items (name, type, weight, disposal_instructions, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, wasteItem.getName());
            stmt.setString(2, wasteItem.getType());
            stmt.setDouble(3, wasteItem.getWeight());
            stmt.setString(4, wasteItem.getDisposalInstructions());
            stmt.setInt(5, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateWasteReport(int userId) {
        String sql = "SELECT * FROM waste_items WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
    
            // Print the table header
            System.out.printf("%-20s %-15s %-15s %-30s%n", "Name", "Type", "Weight (kg)", "Disposal Instructions");
            System.out.println("------------------------------------------------------------------------------------------");
    
            // Print each row of the table
            while (rs.next()) {
                System.out.printf("%-20s %-15s %-15.2f %-30s%n",
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("weight"),
                    rs.getString("disposal_instructions"));
            }
    
            System.out.println("------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listWasteItems(int userId) {
        String sql = "SELECT waste_id, name, type, weight FROM waste_items WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "Name", "Type", "Weight (kg)");
            System.out.println("-------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-10.2f%n",
                    rs.getInt("waste_id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getDouble("weight"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteWasteItem(int wasteId, int userId) {
        String sql = "DELETE FROM waste_items WHERE waste_id = ? AND user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, wasteId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllWasteItems(int userId) {
        String sql = "DELETE FROM waste_items WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
