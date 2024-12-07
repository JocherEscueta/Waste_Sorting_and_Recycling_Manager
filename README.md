### README

# Waste Sorting and Recycling Assistant

The **Waste Sorting and Recycling Assistant** is a console-based Java application that allows users to manage their waste responsibly. Users can register, log in, and manage waste items while following best practices for recycling and disposal.

---

## Features

### User Management
- **Registration:** Create a new account with a unique username and password.
- **Login/Logout:** Securely log in to access your data and log out when done.

### Waste Management
- **Add Waste Items:** Add items by specifying their name, type, and weight.
- **View Waste Report:** Display a clean and readable report of all waste items, including disposal instructions.
- **Delete Waste Items:** Delete specific waste items or clear all items for the logged-in user.

---

## Prerequisites

1. **Java Development Kit (JDK)**  
   Ensure JDK 8 or higher is installed.

2. **MySQL Database**  
   Install and set up MySQL. Create a database and use the provided table schema below.

3. **JDBC Connector**  
   Add the MySQL JDBC driver to your project dependencies.

---

## Database Tables Schema

### Users Table

```sql
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Waste Items Table

```sql
CREATE TABLE waste_items (
    waste_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type ENUM('Organic', 'Plastic', 'Metal', 'Glass', 'E-Waste', 'Other') NOT NULL,
    weight DOUBLE NOT NULL,
    disposal_instructions VARCHAR(255),
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```

## How to Run

1. Clone or download the repository.
2. Update the database configuration in `config.DatabaseConnection`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/wastesortingdb";
   private static final String USER = "your-username";
   private static final String PASSWORD = "your-password";
   ```
3. Compile and run the `App` class:
   ```bash
   javac -d . App.java
   java main.App
   ```

---

## Usage

1. Start the application.
2. Register a new user or log in with existing credentials.
3. Use the following options:
   - Add new waste items.
   - View a detailed waste report.
   - Delete specific waste items or clear all waste items.
4. Log out when done.

--- 
