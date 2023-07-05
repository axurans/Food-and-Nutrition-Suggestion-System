import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class Food {
    private Connection connection;

   public Food() {
	   
	   
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "piyush", "piyush");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver not found.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void insertFood(int foodId, String foodName, int calories, int weightInGrams, int userId) throws SQLException {
        String query = "INSERT INTO foods (food_id, food_name, calories, weight_in_grams, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, foodId);
            statement.setString(2, foodName);
            statement.setInt(3, calories);
            statement.setInt(4, weightInGrams);
            statement.setInt(5, userId);
            statement.executeUpdate();
        }
    }

    public void insertNutrient(int protein, int sugar, int fiber, int nutrientId, int userId, int foodId) throws SQLException {
        String query = "INSERT INTO nutrients (protein, sugar, fiber, nutrient_id, user_id, food_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, protein);
            statement.setInt(2, sugar);
            statement.setInt(3, fiber);
            statement.setInt(4, nutrientId);
            statement.setInt(5, userId);
            statement.setInt(6, foodId);
            statement.executeUpdate();
        }
    }
	 public List<Map<String, Object>> getFoodsFromDatabase() {
        List<Map<String, Object>> foods = new ArrayList<>();

        try {
            // Establish the database connection
            //Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

            // Execute the query to retrieve foods from the database
            String query = "SELECT * FROM foods";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate over the result set and populate the foods list
            while (resultSet.next()) {
                Map<String, Object> food = new HashMap<>();
                food.put("food_id", resultSet.getInt("food_id"));
                food.put("food_name", resultSet.getString("food_name"));
                food.put("calories", resultSet.getDouble("calories"));
                food.put("weight_in_grams", resultSet.getDouble("weight_in_grams"));
                food.put("user_id", resultSet.getInt("user_id"));
                foods.add(food);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foods;
    }

    public List<Map<String, Object>> getNutrientsFromDatabase() {
        List<Map<String, Object>> nutrients = new ArrayList<>();

        try {
            
            //Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

            // Execute the query to retrieve nutrients from the database
            String query = "SELECT * FROM nutrients";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate over the result set and populate the nutrients list
            while (resultSet.next()) {
                Map<String, Object> nutrient = new HashMap<>();
                nutrient.put("nutrient_id", resultSet.getInt("nutrient_id"));
                nutrient.put("protein", resultSet.getDouble("protein"));
                nutrient.put("sugar", resultSet.getDouble("sugar"));
                nutrient.put("fiber", resultSet.getDouble("fiber"));
                nutrient.put("user_id", resultSet.getInt("user_id"));
                nutrient.put("food_id", resultSet.getInt("food_id"));
                nutrients.add(nutrient);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nutrients;
    }
	
	
	public List<String> getFoodIdsFromDatabase() {
    List<String> foodIDs = new ArrayList<>();
    ResultSet resultSet = null;
    Statement statement = null;

    try {
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "piyush", "piyush");
        
        statement = connection.createStatement();

        // Select all food IDs from the foods table
        String selectFoodIDsQuery = "SELECT food_id FROM foods";
         resultSet = statement.executeQuery(selectFoodIDsQuery);

        // Iterate over the result set and add each food ID to the list
        while (resultSet.next()) {
            String foodID = resultSet.getString("food_id");
            foodIDs.add(foodID);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors or exceptions
    } finally {
        // Close the result set, statement, and connection
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return foodIDs;
}
	public List<String> getNutrientIdsFromDatabase() {
    List<String> nutrientIds = new ArrayList<>();
	ResultSet resultSet = null;
    Statement statement = null;


    try {
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "piyush", "piyush");
        statement = connection.createStatement();

        // Select all nutrient IDs from the nutrients table
        String selectNutrientIDsQuery = "SELECT nutrient_id FROM nutrients";
        resultSet = statement.executeQuery(selectNutrientIDsQuery);

        // Iterate over the result set and add each nutrient ID to the list
        while (resultSet.next()) {
            String nutrientID = resultSet.getString("nutrient_id");
            nutrientIds.add(nutrientID);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors or exceptions
    } finally {
        // Close the result set, statement, and connection
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    return nutrientIds;
}


	
	public void deleteFood(String foodId) {
    PreparedStatement statement = null;

    try {
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "piyush", "piyush");
        // Delete the food record from the foods table
        String deleteFoodQuery = "DELETE FROM foods WHERE food_id = ?";
        statement = connection.prepareStatement(deleteFoodQuery);
        statement.setString(1, foodId);
        int foodRowsAffected = statement.executeUpdate();

        // Delete the corresponding nutrient record from the nutrients table
        String deleteNutrientQuery = "DELETE FROM nutrients WHERE food_id = ?";
        statement = connection.prepareStatement(deleteNutrientQuery);
        statement.setString(1, foodId);
        int nutrientRowsAffected = statement.executeUpdate();

        if (foodRowsAffected > 0 || nutrientRowsAffected > 0) {
            System.out.println("Food with ID " + foodId + " deleted successfully!");
        } else {
            System.out.println("No food found with the specified ID.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors or exceptions
    } finally {
        // Close the statement
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
		public void updateFood(String selectedFoodId, String selectedField, String newValue) {
    try {
        Statement statement = connection.createStatement();

        // Update the selected field of the specified food ID in the foods table
        String updateFoodQuery = "UPDATE foods SET " + selectedField + " = '" + newValue + "' WHERE food_id = '" + selectedFoodId + "'";
        statement.executeUpdate(updateFoodQuery);

        // Print a message to indicate the successful update
        System.out.println("Food with ID " + selectedFoodId + " updated successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors or exceptions
    }
}

	public void updateNutrient(String selectedFoodId, String selectedField, String newValue) {
    try {
        Statement statement = connection.createStatement();

        // Update the selected field of the specified food ID in the nutrients table
        String updateNutrientQuery = "UPDATE nutrients SET " + selectedField + " = '" + newValue + "' WHERE food_id = '" + selectedFoodId + "'";
        statement.executeUpdate(updateNutrientQuery);

        // Print a message to indicate the successful update
        System.out.println("Nutrient for food with ID " + selectedFoodId + " updated successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any database errors or exceptions
    }
}



}
