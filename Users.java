import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Users {
    private Connection connection;

    public Users() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "piyush", "piyush");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver not found.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void insertUser(int userId, String name, int age, double weight, double height) {
        try {
            String query = "INSERT INTO users (user_id, name, age, weight, height) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setDouble(4, weight);
            pstmt.setDouble(5, height);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();

            if (rowsAffected > 0) {
                System.out.println("User inserted successfully!");
            } else {
                System.out.println("User insertion failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    public void deleteUser(int userId) {
        try {
            String query = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User deletion failed. User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void updateUser(int userId, String field, Object newValue) {
    try {
        String query = "UPDATE users SET " + field.toLowerCase() + " = ? WHERE user_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        
        if (newValue instanceof String) {
            pstmt.setString(1, (String) newValue);
        } else if (newValue instanceof Integer) {
            pstmt.setInt(1, (Integer) newValue);
        } else if (newValue instanceof Double) {
            pstmt.setDouble(1, (Double) newValue);
        }
        
        pstmt.setInt(2, userId);

        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();

        if (rowsAffected > 0) {
            System.out.println("User updated successfully!");
        } else {
            throw new SQLException("User update failed. User not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating user: " + e.getMessage());
        
    }
}

	public  List<Integer> getUserIDs() {
        List<Integer> userIDs = new ArrayList<>();

        // Establish a connection to the database
        try {
            //Class.forName("oracle.jdbc.OracleDriver");
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "phani", "phani");

            // Execute a SELECT query to retrieve user IDs
            String query = "SELECT user_id FROM users";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate over the result set and add user IDs to the list
            while (rs.next()) {
                int userID = rs.getInt("user_id");
                userIDs.add(userID);
            }

            // Close the database resources
            rs.close();
            stmt.close();
            //con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userIDs;
    }
	 public List<Map<String, Object>> getUsersFromDatabase() {
    List<Map<String, Object>> users = new ArrayList<>();

    try {
        Statement statement = connection.createStatement();

        // Execute the query to retrieve users from the database
        String query = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate over the result set and populate the users list
        while (resultSet.next()) {
            Map<String, Object> user = new HashMap<>();
            user.put("user_id", resultSet.getInt("user_id"));
            user.put("name", resultSet.getString("name"));
            user.put("age", resultSet.getInt("age"));
            user.put("weight", resultSet.getDouble("weight"));
            user.put("height", resultSet.getDouble("height"));
            users.add(user);
        }

        // Close the resources
        resultSet.close();
        statement.close();
        //connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return users;
}


}
