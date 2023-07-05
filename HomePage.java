import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.*;

public class HomePage extends JFrame {
    private JLabel titleLabel;
    private JLabel welcomeLabel;
	private JPanel foodsPanel;
    private BufferedImage backgroundImage;
    private JTabbedPane tabbedPane;
    private JButton proceedButton;

    
    public HomePage() {
        setTitle("Food and Nutrition Suggestion System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
		//createMenu();
		Users users = new Users();
		Food food = new Food();
        
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("E:/food and nutrition suggestion system/canvas.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
        // Add the content panel
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        });
        
        // Set layout for the content panel
        setLayout(new GridBagLayout());
		JComboBox<Integer> userIDComboBox = new JComboBox<>();

		// Populate the userIDComboBox with existing user IDs
		List<Integer> userIDs = users.getUserIDs();
		for (Integer userID : userIDs) {
			userIDComboBox.addItem(userID);
		}
        
        // Add title label
        titleLabel = new JLabel("Food and Nutrition Suggestion System");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(20, 0, 20, 0);
        add(titleLabel, titleConstraints);
        
        // Add welcome label
        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        GridBagConstraints welcomeConstraints = new GridBagConstraints();
        welcomeConstraints.gridx = 0;
        welcomeConstraints.gridy = 1;
        add(welcomeLabel, welcomeConstraints);
        
        // Add proceed button
        proceedButton = new JButton("Click here to proceed");
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 2;
        buttonConstraints.insets = new Insets(20, 0, 0, 0);
        add(proceedButton, buttonConstraints);
        
        // Create the tabbed pane
        /*tabbedPane = new JTabbedPane();
		JPanel foodsPanel = new JPanel();
		frame.add(foodsPanel);
		JPanel foodSectionPanel = new JPanel();


		JPanel nutrientsSectionPanel = new JPanel();
		JTabbedPane foodsTabbedPane = new JTabbedPane();
		foodsTabbedPane.addTab("Food", foodSectionPanel);
		foodsTabbedPane.addTab("Nutrients", nutrientsSectionPanel);
		foodsPanel.add(foodsTabbedPane);*/        
        // Add action listener to the proceed button
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the tabbed pane
                tabbedPane.setVisible(true);
				proceedButton.setVisible(false);
            }
        });
		tabbedPane = new JTabbedPane();
        
        // Add the tabbed pane to the main content pane
        GridBagConstraints tabbedPaneConstraints = new GridBagConstraints();
        tabbedPaneConstraints.gridx = 0;
        tabbedPaneConstraints.gridy = 3;
        tabbedPaneConstraints.fill = GridBagConstraints.BOTH;
        tabbedPaneConstraints.weightx = 1.0;
        tabbedPaneConstraints.weighty = 1.0;
        tabbedPaneConstraints.insets = new Insets(10, 10, 10, 10);
        tabbedPane.setVisible(false); // Initially hide the tabbed pane
        add(tabbedPane, tabbedPaneConstraints);
		setVisible(true);
        // USERS PANEL
		JPanel usersPanel = new JPanel();
		usersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		// Add User ID label and text field
		JLabel userIdLabel = new JLabel("User ID:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		usersPanel.add(userIdLabel, gbc);

		JTextField userIdField = new JTextField(10); // Adjust the size as needed
		gbc.gridx = 1;
		gbc.gridy = 0;
		usersPanel.add(userIdField, gbc);

		// Add Name label and text field
		JLabel nameLabel = new JLabel("Name:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		usersPanel.add(nameLabel, gbc);

		JTextField nameField = new JTextField(10); // Adjust the size as needed
		gbc.gridx = 1;
		gbc.gridy = 1;
		usersPanel.add(nameField, gbc);

// Add Age label and text field
JLabel ageLabel = new JLabel("Age:");
gbc.gridx = 0;
gbc.gridy = 2;
usersPanel.add(ageLabel, gbc);

JTextField ageField = new JTextField(10); // Adjust the size as needed
gbc.gridx = 1;
gbc.gridy = 2;
usersPanel.add(ageField, gbc);

// Add Weight label and text field
JLabel weightLabel = new JLabel("Weight:");
gbc.gridx = 0;
gbc.gridy = 3;
usersPanel.add(weightLabel, gbc);

JTextField weightField = new JTextField(10); // Adjust the size as needed
gbc.gridx = 1;
gbc.gridy = 3;
usersPanel.add(weightField, gbc);

// Add Height label and text field
JLabel heightLabel = new JLabel("Height:");
gbc.gridx = 0;
gbc.gridy = 4;
usersPanel.add(heightLabel, gbc);

JTextField heightField = new JTextField(10); // Adjust the size as needed
gbc.gridx = 1;
gbc.gridy = 4;
usersPanel.add(heightField, gbc);

// Add Submit button
JButton submitButton = new JButton("Submit");
gbc.gridx = 0;
gbc.gridy = 5;
gbc.gridwidth = 2;
usersPanel.add(submitButton, gbc);

// Add Delete button
JButton deleteButton = new JButton("Delete");
gbc.gridx = 0;
gbc.gridy = 6;
gbc.gridwidth = 2;
usersPanel.add(deleteButton, gbc);

// Add Update button
JButton updateButton = new JButton("Update");
gbc.gridx = 0;
gbc.gridy = 7;
gbc.gridwidth = 2;

usersPanel.add(updateButton, gbc);

JButton viewUserButton = new JButton("View Users");
gbc.gridx = 0;
gbc.gridy = 8;
gbc.gridwidth = 2;
usersPanel.add(viewUserButton, gbc);

		// Create the tabbed pane
tabbedPane = new JTabbedPane();

// Create the FOODS panel
JPanel foodsPanel = new JPanel();
foodsPanel.setLayout(new GridBagLayout());
GridBagConstraints gbcFoods = new GridBagConstraints();
gbcFoods.insets = new Insets(10, 10, 10, 10);

// Add User ID label and combobox
JLabel userLabel = new JLabel("Select your user id:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 0;
foodsPanel.add(userLabel, gbcFoods);

JComboBox<String> userIdComboBox = new JComboBox<>();
gbc.gridx = 1;
gbc.gridy = 0;
foodsPanel.add(userIdComboBox, gbc);

List<Integer> userIds = users.getUserIDs();
List<String> userIdStrings = new ArrayList<>();

// Convert List<Integer> to List<String>
for (Integer userId : userIds) {
    userIdStrings.add(String.valueOf(userId));
}

// Populate the userIdComboBox with existing user IDs
for (String userId : userIdStrings) {
    userIdComboBox.addItem(userId);
}

// Add Food ID label and text field
JLabel foodIdLabel = new JLabel("Food ID:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 1;
foodsPanel.add(foodIdLabel, gbcFoods);

JTextField foodIdField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 1;
foodsPanel.add(foodIdField, gbcFoods);

// Add Food Name label and text field
JLabel foodNameLabel = new JLabel("Food Name:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 2;
foodsPanel.add(foodNameLabel, gbcFoods);

JTextField foodNameField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 2;
foodsPanel.add(foodNameField, gbcFoods);

// Add Calories label and text field
JLabel caloriesLabel = new JLabel("Calories:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 3;
foodsPanel.add(caloriesLabel, gbcFoods);

JTextField caloriesField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 3;
foodsPanel.add(caloriesField, gbcFoods);

// Add Weight label and text field
JLabel foodweightLabel = new JLabel("Weight in grams:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 4;
foodsPanel.add(foodweightLabel, gbcFoods);

JTextField foodweightField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 4;
foodsPanel.add(foodweightField, gbcFoods);

// Add Submit button
JButton submitfoodButton = new JButton("Submit");
gbcFoods.gridx = 0;
gbcFoods.gridy = 5;
gbcFoods.gridwidth = 2;
foodsPanel.add(submitfoodButton, gbcFoods);

// Add Nutrient ID label and text field
JLabel nutrientIdLabel = new JLabel("Nutrient ID:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 6;
gbcFoods.gridwidth = 1;
foodsPanel.add(nutrientIdLabel, gbcFoods);

JTextField nutrientIdField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 6;
foodsPanel.add(nutrientIdField, gbcFoods);

// Add Protein label and text field
JLabel proteinLabel = new JLabel("Protein:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 7;
foodsPanel.add(proteinLabel, gbcFoods);

JTextField proteinField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 7;
foodsPanel.add(proteinField, gbcFoods);

// Add Sugar label and text field
JLabel sugarLabel = new JLabel("Sugar:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 8;
foodsPanel.add(sugarLabel, gbcFoods);

JTextField sugarField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 8;
foodsPanel.add(sugarField, gbcFoods);

// Add Fiber label and text field
JLabel fiberLabel = new JLabel("Fiber:");
gbcFoods.gridx = 0;
gbcFoods.gridy = 9;
foodsPanel.add(fiberLabel, gbcFoods);

JTextField fiberField = new JTextField(10);
gbcFoods.gridx = 1;
gbcFoods.gridy = 9;
foodsPanel.add(fiberField, gbcFoods);

JButton foodUpdateButton = new JButton("Update");
JButton foodDeleteButton = new JButton("Delete");
// Add Update button
gbcFoods.gridx = 0;
gbcFoods.gridy = 10;
foodsPanel.add(foodUpdateButton, gbcFoods);

// Add Delete button
JButton viewFoodButton = new JButton("View Foods");
gbc.gridx = 1;
gbc.gridy = 11;
gbc.gridwidth = 1;
foodsPanel.add(viewFoodButton, gbc);

// Create the deleteButton


gbcFoods.gridx = 1;
foodsPanel.add(foodDeleteButton, gbcFoods);

// Hide the input fields initially
foodIdLabel.setVisible(false);
foodIdField.setVisible(false);
foodNameLabel.setVisible(false);
foodNameField.setVisible(false);
caloriesLabel.setVisible(false);
caloriesField.setVisible(false);
foodweightLabel.setVisible(false);
foodweightField.setVisible(false);
submitfoodButton.setVisible(false);
nutrientIdLabel.setVisible(false);
nutrientIdField.setVisible(false);
proteinLabel.setVisible(false);
proteinField.setVisible(false);
foodUpdateButton.setVisible(false);
foodDeleteButton.setVisible(false);
sugarLabel.setVisible(false);
sugarField.setVisible(false);
fiberLabel.setVisible(false);
fiberField.setVisible(false);
//deleteFoodButton.setVisible(false);
viewFoodButton.setVisible(false);

userIdComboBox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Show or hide the input fields based on user ID selection
        boolean showInputs = userIdComboBox.getSelectedItem() != null;
		userIdComboBox.setVisible(false);
		userLabel.setVisible(false);
        foodIdLabel.setVisible(showInputs);
        foodIdField.setVisible(showInputs);
        foodNameLabel.setVisible(showInputs);
        foodNameField.setVisible(showInputs);
        caloriesLabel.setVisible(showInputs);
        caloriesField.setVisible(showInputs);
        foodweightLabel.setVisible(showInputs);
        foodweightField.setVisible(showInputs);
        submitfoodButton.setVisible(showInputs);
        nutrientIdLabel.setVisible(showInputs);
        nutrientIdField.setVisible(showInputs);
        proteinLabel.setVisible(showInputs);
        proteinField.setVisible(showInputs);
        sugarLabel.setVisible(showInputs);
        sugarField.setVisible(showInputs);
        fiberLabel.setVisible(showInputs);
        fiberField.setVisible(showInputs);
		foodUpdateButton.setVisible(showInputs);
        foodDeleteButton.setVisible(showInputs);
		viewFoodButton.setVisible(showInputs);
		//deleteFoodButton.setVisible(showInputs);
    }
});


/* Add the Foods panel to the main content pane
gbc.gridx = 0;
gbc.gridy = 1;
homePanel.add(foodsPanel, gbc);*/



// Add the tabbed pane to the main content pane
//GridBagConstraints tabbedPaneConstraints = new GridBagConstraints();
tabbedPaneConstraints.gridx = 0;
tabbedPaneConstraints.gridy = 3;
tabbedPaneConstraints.fill = GridBagConstraints.BOTH;
tabbedPaneConstraints.weightx = 1.0;
tabbedPaneConstraints.weighty = 1.0;
tabbedPaneConstraints.insets = new Insets(10, 10, 10, 10);


tabbedPane.setVisible(false); // Initially hide the tabbed pane
add(tabbedPane, tabbedPaneConstraints);
tabbedPane.addTab("Users", usersPanel);
tabbedPane.addTab("Foods", foodsPanel);



//ACTION LISTENERS
    //SUBMIT USER DATA
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Users users = new Users();
				int userId = Integer.parseInt(userIdField.getText());
				String name = nameField.getText();
				int age = Integer.parseInt(ageField.getText());
				double weight = Double.parseDouble(weightField.getText());
				double height = Double.parseDouble(heightField.getText());


				if (name.isEmpty() || userIdField.getText().isEmpty() || ageField.getText().isEmpty() || weightField.getText().isEmpty() || heightField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(HomePage.this, "Fill all details", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
    

			// Call the insertIntoUsers method from Users class
				users.insertUser(userId, name, age, weight, height);

			JOptionPane.showMessageDialog(HomePage.this, "Data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}

				}
			});
	
	//DELETE USERS BUTTON
	deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Fetch the available user IDs from the database
		Users users = new Users();
        List<Integer> userIDs = users.getUserIDs();

        if (userIDs.isEmpty()) {
            JOptionPane.showMessageDialog(HomePage.this, "No users found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Create a JComboBox with the available user IDs
            JComboBox<Integer> idComboBox = new JComboBox<>(userIDs.toArray(new Integer[0]));

            // Show an input dialog with the ID dropdown
            int option = JOptionPane.showOptionDialog(HomePage.this, idComboBox, "Select User ID to delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                // Get the selected user ID
                int selectedUserID = (int) idComboBox.getSelectedItem();

                // Call the deleteFromUsers method from Users class
                users.deleteUser(selectedUserID);

                JOptionPane.showMessageDialog(HomePage.this, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
});
	foodDeleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> foodIds = food.getFoodIdsFromDatabase();

        // Create a dropdown menu for food IDs
        JComboBox<String> foodIdDropdown = new JComboBox<>();
        for (String foodId : foodIds) {
            foodIdDropdown.addItem(foodId);
        }

        // Create a panel to hold the dropdown
        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Select Food ID:"));
        deletePanel.add(foodIdDropdown);

        // Show the confirmation dialog with the dropdown
        int result = JOptionPane.showConfirmDialog(null, deletePanel, "Delete Food",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedFoodId = (String) foodIdDropdown.getSelectedItem();

            // Call the deleteFood method in Food.java with the selected food ID
            food.deleteFood(selectedFoodId);

            // Clear the input fields
            foodIdField.setText("");
            foodNameField.setText("");
            caloriesField.setText("");
            foodweightField.setText("");
            nutrientIdField.setText("");
            proteinField.setText("");
            sugarField.setText("");
            fiberField.setText("");

            // Show a confirmation message to the user
            JOptionPane.showMessageDialog(null, "Food deleted successfully!", "Delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

	submitfoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if all fields are filled
                if (foodIdField.getText().isEmpty() || foodNameField.getText().isEmpty() ||
                    caloriesField.getText().isEmpty() || foodweightField.getText().isEmpty() ||
                    nutrientIdField.getText().isEmpty() || proteinField.getText().isEmpty() ||
                    sugarField.getText().isEmpty() || fiberField.getText().isEmpty()) {
                    // Display a warning message if any field is empty
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Get the selected user ID from the combobox
                    int selectedUserId = Integer.parseInt(userIdComboBox.getSelectedItem().toString());

                    try {
                        // Insert data into foods table
                        int foodId = Integer.parseInt(foodIdField.getText());
                        String foodName = foodNameField.getText();
                        int calories = Integer.parseInt(caloriesField.getText());
                        int weightInGrams = Integer.parseInt(foodweightField.getText());
                        food.insertFood(foodId, foodName, calories, weightInGrams, selectedUserId);

                        // Insert data into nutrients table
                        int nutrientId = Integer.parseInt(nutrientIdField.getText());
                        int protein = Integer.parseInt(proteinField.getText());
                        int sugar = Integer.parseInt(sugarField.getText());
                        int fiber = Integer.parseInt(fiberField.getText());
                        food.insertNutrient(protein, sugar, fiber, nutrientId, selectedUserId, foodId);

                        // Clear the input fields
                        foodIdField.setText("");
                        foodNameField.setText("");
                        caloriesField.setText("");
                        foodweightField.setText("");
                        nutrientIdField.setText("");
                        proteinField.setText("");
                        sugarField.setText("");
                        fiberField.setText("");

                        // Display a success message
                        JOptionPane.showMessageDialog(null, "Data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        // Handle any database errors
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error inserting data", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
			}
	});
	
	//UPDATE USERS
	updateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the available user IDs
        List<Integer> userIDs = users.getUserIDs();

        // Show a dialog to choose the user ID
        Integer selectedUserID = (Integer) JOptionPane.showInputDialog(HomePage.this,
                "Select a User ID:", "Update User", JOptionPane.PLAIN_MESSAGE,
                null, userIDs.toArray(), userIDs.get(0));

        if (selectedUserID != null) {
            // Show an input dialog to choose the field to update
            String[] fields = { "Name", "Age", "Weight", "Height" };
            String selectedField = (String) JOptionPane.showInputDialog(HomePage.this,
                    "Select a field to update:", "Update User", JOptionPane.PLAIN_MESSAGE,
                    null, fields, fields[0]);

            if (selectedField != null) {
                // Show an input dialog to enter the new value
                String newValue = JOptionPane.showInputDialog(HomePage.this,
                        "Enter the new value for " + selectedField + ":", "Update User",
                        JOptionPane.PLAIN_MESSAGE);

                if (newValue != null) {
                    // Perform the update operation
                    users.updateUser(selectedUserID, selectedField, newValue);
                    
                    // Show appropriate message
                    JOptionPane.showMessageDialog(HomePage.this,
                            "User details updated successfully", "Update Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // User canceled entering the new value
                    JOptionPane.showMessageDialog(HomePage.this,
                            "Update canceled", "Update Failed",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // User canceled selecting the field to update
                JOptionPane.showMessageDialog(HomePage.this,
                        "Update canceled", "Update Failed",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // User canceled selecting the User ID
            JOptionPane.showMessageDialog(HomePage.this,
                    "Update canceled", "Update Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
});

//VIEW USERS
	// Action listener for View Users button
viewUserButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Call the getUsersFromDatabase() method to retrieve all rows from the users table
        List<Map<String, Object>> userList = users.getUsersFromDatabase();

        // Create a two-dimensional array to hold the table data
        Object[][] tableData = new Object[userList.size()][5];

        // Populate the table data array with user information
        for (int i = 0; i < userList.size(); i++) {
            Map<String, Object> user = userList.get(i);
            tableData[i][0] = user.get("user_id");
            tableData[i][1] = user.get("name");
            tableData[i][2] = user.get("age");
            tableData[i][3] = user.get("weight");
            tableData[i][4] = user.get("height");
        }

        // Create an array of column names
        String[] columnNames = { "User ID", "Name", "Age", "Weight", "Height" };

        // Create a new JTable with the table data and column names
        JTable table = new JTable(tableData, columnNames);

        // Customize the table appearance if needed
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Create a JScrollPane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Show the table in a dialog box
        JOptionPane.showMessageDialog(null, scrollPane, "Users", JOptionPane.PLAIN_MESSAGE);
    }
});


	foodUpdateButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Display a message dialog to ask the user to select table (foods or nutrients)
        String[] options = {"Foods", "Nutrients"};
		List<String> foodIds = food.getFoodIdsFromDatabase();
		List<String> nutrientIds = food.getNutrientIdsFromDatabase();
        int tableChoice = JOptionPane.showOptionDialog(
                null,
                "Select a table to update:",
                "Table Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (tableChoice == 0) {
            // User selected Foods table
            String selectedFoodId = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a food ID:",
                    "Food ID Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    foodIds.toArray(),
                    null
            );

            if (selectedFoodId != null) {
                String[] fieldOptions = {"food_name", "calories", "weight_in_grams"};
                String selectedField = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a field to update:",
                        "Field Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        fieldOptions,
                        null
                );

                if (selectedField != null) {
                    String newValue = JOptionPane.showInputDialog(
                            null,
                            "Enter the new value:",
                            "New Value",
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (newValue != null) {
                        // Call the updateFood method with the selected food ID, field, and new value
                        food.updateFood(selectedFoodId, selectedField, newValue);
                    }
                }
            }
        } else if (tableChoice == 1) {
            // User selected Nutrients table
            String selectedNutrientId = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a nutrient ID:",
                    "Nutrient ID Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nutrientIds.toArray(),
                    null
            );

            if (selectedNutrientId != null) {
                String[] fieldOptions = {"protein", "sugar", "fiber"};
                String selectedField = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a field to update:",
                        "Field Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        fieldOptions,
                        null
                );

                if (selectedField != null) {
                    String newValue = JOptionPane.showInputDialog(
                            null,
                            "Enter the new value:",
                            "New Value",
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (newValue != null) {
                        // Call the updateNutrient method with the selected nutrient ID, field, and new value
                        food.updateNutrient(selectedNutrientId, selectedField, newValue);
                    }
                }
            }
        }
    }
});

	}
	
	
  

		


        
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage::new);
    }
}

	
