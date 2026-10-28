package System;

import HelperClasses.*;
import static HelperClasses.Connector.connectToDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Application extends JFrame {
    private JTextField FirstName_Field;
    private JTextField LastName_Field;
    private JTextField MiddleName_Field;
    private JTextField Birthdate_Field;
    private JComboBox<String> Sex_ComboBox;
    private JButton Proceed_Button;
    private JLabel Heading;

    private Connection connection;

    public Application() {
        setTitle("Join Us! Story Awaits...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        connection = connectToDatabase();

        NavigationBar_Blue navbar = new NavigationBar_Blue();
        navbar.setBounds(0, 0, 1920, 100);
        navbar.setVisible(true);
        getContentPane().add(navbar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                navbar.setSize(getWidth(), 100);
            }
        });

        Heading = new JLabel("APPLICATION");
        Heading.setFont(new Font("Gotham Rounded", Font.PLAIN, 51));
        Heading.setForeground(new Color(0, 43, 184));
        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setBounds(500, 150, 500, 60);
        getContentPane().add(Heading);

        JLabel FirstName_Label = new JLabel("First Name");
        FirstName_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        FirstName_Label.setForeground(new Color(128, 161, 219));
        FirstName_Label.setBounds(150, 250, 300, 30);
        getContentPane().add(FirstName_Label);

        FirstName_Field = new JTextField("   e.g. Juan");
        FirstName_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        FirstName_Field.setForeground(new Color(217, 217, 217));
        FirstName_Field.setBorder(new RoundedBorder(20));
        FirstName_Field.setBounds(150, 290, 500, 70);
        getContentPane().add(FirstName_Field);

        JLabel LastName_Label = new JLabel("Last Name");
        LastName_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        LastName_Label.setForeground(new Color(128, 161, 219));
        LastName_Label.setBounds(150, 380, 300, 30);
        getContentPane().add(LastName_Label);

        LastName_Field = new JTextField("   e.g. Cruz");
        LastName_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        LastName_Field.setForeground(new Color(217, 217, 217));
        LastName_Field.setBorder(new RoundedBorder(20));
        LastName_Field.setBounds(150, 420, 500, 70);
        getContentPane().add(LastName_Field);

        JLabel MiddleName_Label = new JLabel("Middle Name");
        MiddleName_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        MiddleName_Label.setForeground(new Color(128, 161, 219));
        MiddleName_Label.setBounds(150, 510, 300, 30);
        getContentPane().add(MiddleName_Label);

        MiddleName_Field = new JTextField("   e.g. Santos");
        MiddleName_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        MiddleName_Field.setForeground(new Color(217, 217, 217));
        MiddleName_Field.setBorder(new RoundedBorder(20));
        MiddleName_Field.setBounds(150, 550, 500, 70);
        getContentPane().add(MiddleName_Field);

        JLabel Birthdate_Label = new JLabel("Birthdate");
        Birthdate_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        Birthdate_Label.setForeground(new Color(128, 161, 219));
        Birthdate_Label.setBounds(900, 250, 300, 30);
        getContentPane().add(Birthdate_Label);

        Birthdate_Field = new JTextField("   DD/MM/YYYY");
        Birthdate_Field.setFont(new Font("Gotham Rounded", Font.BOLD, 27));
        Birthdate_Field.setForeground(new Color(217, 217, 217));
        Birthdate_Field.setBorder(new RoundedBorder(20));
        Birthdate_Field.setBounds(900, 290, 500, 70);
        getContentPane().add(Birthdate_Field);

        JLabel Sex_Label = new JLabel("Sex");
        Sex_Label.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        Sex_Label.setForeground(new Color(128, 161, 219));
        Sex_Label.setBounds(900, 380, 300, 30);
        getContentPane().add(Sex_Label);

        Sex_ComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        Sex_ComboBox.setFont(new Font("Gotham Rounded", Font.BOLD, 26));
        Sex_ComboBox.setBorder(new RoundedBorder(20));
        Sex_ComboBox.setFocusable(false);
        Sex_ComboBox.setBounds(900, 420, 500, 70);
        getContentPane().add(Sex_ComboBox);

        Proceed_Button = new JButton("Proceed");
        Proceed_Button.setFont(new Font("Gotham Rounded", Font.PLAIN, 33));
        Proceed_Button.setForeground(Color.WHITE);
        Proceed_Button.setBackground(new Color(52, 120, 242));
        Proceed_Button.setBorder(new RoundedWhite(50));
        Proceed_Button.setFocusPainted(false);
        Proceed_Button.setBounds(600, 650, 300, 80);
        Proceed_Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(Proceed_Button);

        addListeners();
    }

    private void addListeners() {
        addFocusListenerToField(FirstName_Field, "e.g. Juan");
        addFocusListenerToField(LastName_Field, "e.g. Cruz");
        addFocusListenerToField(MiddleName_Field, "e.g. Santos");
        addFocusListenerToField(Birthdate_Field, "DD/MM/YYYY");

Proceed_Button.addActionListener(evt -> {
    String firstName = FirstName_Field.getText().trim();
    String lastName = LastName_Field.getText().trim();
    String middleName = MiddleName_Field.getText().trim();
    String birthdateStr = Birthdate_Field.getText().trim();
    String sex = (String) Sex_ComboBox.getSelectedItem();

    if (firstName.isEmpty() || lastName.isEmpty() || middleName.isEmpty() || birthdateStr.isEmpty() || sex == null) {
        GcashDialog dialog = new GcashDialog(null, null, 0, "All fields must be filled!", 20, "Okay", null);
        return;
    }

    // Validate birthdate format and age
    if (!birthdateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
        GcashDialog dialog = new GcashDialog(null, null, 0, "Birthdate must be in DD/MM/YYYY format.", 20, "Okay", null);
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    try {
        Date birthdate = sdf.parse(birthdateStr);

        // Check age
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthdate);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        if (age < 18) {
            GcashDialog dialog = new GcashDialog(null, null, 0, "You must be at least 18 years old to apply.", 20, "Okay", null);
            return;
        }

        // Check if customer already exists
        String checkQuery = "SELECT CustomerID FROM Customer WHERE FirstName = ? AND LastName = ? AND MiddleName = ? AND Birthdate = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
        checkStmt.setString(1, firstName);
        checkStmt.setString(2, lastName);
        checkStmt.setString(3, middleName);
        checkStmt.setString(4, birthdateStr);
        ResultSet resultSet = checkStmt.executeQuery();

        if (resultSet.next()) {
            // Customer exists, get the existing CustomerID
            int customerID = resultSet.getInt("CustomerID");
            GcashDialog dialog = new GcashDialog(null, null, 0, "Customer already exists. Proceeding...", 20, "Okay", null);
            new Application2(customerID).setVisible(true); // Pass existing CustomerID to Application2
            dispose(); // Close current window
        } else {
            // If customer does not exist, insert new record
            String query = "INSERT INTO Customer (FirstName, LastName, MiddleName, Birthdate, Sex) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, middleName);
            statement.setString(4, birthdateStr);
            statement.setString(5, sex);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int customerID = generatedKeys.getInt(1); // Get the auto-generated CustomerID
                    GcashDialog dialog = new GcashDialog(null, null, 0, "Customer details inserted successfully!", 20, "Confirm", null);
                    new Application2(customerID).setVisible(true); // Pass new CustomerID to Application2
                    dispose();  // Close the current window
                }
            } else {
                GcashDialog dialog = new GcashDialog(null, null, 0, "Failed to insert customer details.", 20, "Okay", null);
            }
        }

    } catch (ParseException e) {
        GcashDialog dialog = new GcashDialog(null, "Invalid Date!", 24, "Please enter a valid date in DD/MM/YYYY format", 18, "Okay", null);
    } catch (SQLException e) {
        GcashDialog dialog = new GcashDialog(null, null, 0, "Database error: " + e.getMessage(), 17, "Okay", null);
    }
});

    }
    
    private void addFocusListenerToField(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (field.getText().trim().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (field.getText().trim().isEmpty()) {
                    field.setText("   " + placeholder);
                    field.setForeground(new Color(217, 217, 217));
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }
}
