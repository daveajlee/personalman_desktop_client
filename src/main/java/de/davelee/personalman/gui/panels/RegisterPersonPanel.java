package de.davelee.personalman.gui.panels;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.RegisterUserRequest;
import de.davelee.personalman.gui.AdminScreen;
import de.davelee.personalman.gui.LoginScreen;
import de.davelee.personalman.gui.PersonalManBaseScreen;
import de.davelee.personalman.gui.RegisterScreen;

import javax.swing.*;
import java.awt.*;

/**
 * This panel creates a form where the user can enter personal information such as name etc. to register for PersonalMan.
 * The submit buttons are provided by the frame which adds this panel.
 */
public class RegisterPersonPanel extends JPanel {

    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();

    private JCheckBox[] daysOfWeekCheckBoxes;

    public RegisterPersonPanel (final UserInterface userInterface, final String company, final String adminUsername, final PersonalManBaseScreen personalManBaseScreen) {

        //create a new box layout for the grid panel and button panel.
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);

        //create a grid panel to show the required data in a table format
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9,2,5,5));
        gridPanel.setBackground(Color.WHITE);

        //Add first name.
        gridPanel.add(new JLabel("First Name:", JLabel.CENTER));
        JTextField firstNameField = new JTextField();
        gridPanel.add(firstNameField);

        //Add surname.
        gridPanel.add(new JLabel("Surname:", JLabel.CENTER));
        JTextField surnameField = new JTextField();
        gridPanel.add(surnameField);

        //Add company.
        gridPanel.add(new JLabel("Company:", JLabel.CENTER));
        JComboBox<String> companyBox;
        if ( company == null ) {
            companyBox = new JComboBox<>(userInterface.getCompanies().toArray(new String[userInterface.getCompanies().size()]));
        } else {
            companyBox = new JComboBox<>(new String[] { company });
        }
        gridPanel.add(companyBox);

        //Add position.
        gridPanel.add(new JLabel("Position: ", JLabel.CENTER));
        JTextField positionField = new JTextField();
        gridPanel.add(positionField);

        //Add working days.
        gridPanel.add(new JLabel("Working Days: ", JLabel.CENTER));
        JPanel workingDaysPanel = new JPanel(new GridLayout(1,7,5,5));
        workingDaysPanel.setBackground(Color.WHITE);
        String[] daysOfWeek = new String[] { "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        daysOfWeekCheckBoxes = new JCheckBox[daysOfWeek.length];
        for ( int i = 0; i < 7; i++ ) {
            daysOfWeekCheckBoxes [i] = new JCheckBox(daysOfWeek[i]);
            //Normal working week is Monday to Friday.
            if ( i < 5 ) { daysOfWeekCheckBoxes[i].setSelected(true); }
            workingDaysPanel.add(daysOfWeekCheckBoxes[i]);
        }
        gridPanel.add(workingDaysPanel);

        //Add username.
        gridPanel.add(new JLabel("Username:", JLabel.CENTER));
        JTextField usernameField = new JTextField();
        gridPanel.add(usernameField);

        //Add password.
        gridPanel.add(new JLabel("Password:", JLabel.CENTER));
        gridPanel.add(passwordField);

        //Add confirm password.
        gridPanel.add(new JLabel("Confirm Password:", JLabel.CENTER));
        gridPanel.add(confirmPasswordField);

        //Add role.
        gridPanel.add(new JLabel("Role:", JLabel.CENTER));
        JComboBox<String> roleBox = new JComboBox<>(java.util.List.of("Employee", "Admin").toArray(new String[2]));
        gridPanel.add(roleBox);

        //add grid panel to box layout
        add(gridPanel);

        //Create button panel with three buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            //First confirm that the passwords are equal.
            if ( !passwordsSame() ) {
                JOptionPane.showMessageDialog(personalManBaseScreen, "The passwords entered do not match. Please verify and submit your registration request again.",
                        "Passwords do not match", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            }
            else {
                RegisterUserRequest registerUserRequest = RegisterUserRequest.builder()
                        .company(companyBox.getSelectedItem().toString())
                        .position(positionField.getText())
                        .firstName(firstNameField.getText())
                        .surname(surnameField.getText())
                        .workingDays(getWorkingDays())
                        .role(roleBox.getSelectedItem().toString())
                        .username(usernameField.getText())
                        //TODO: encrypt password
                        .password(passwordField.getText())
                        .build();
                userInterface.registerUser(registerUserRequest);
                personalManBaseScreen.dispose();
                if ( company == null ) {
                    JOptionPane.showMessageDialog(personalManBaseScreen, "Thank you for registering for PersonalMan. Your account was created successfully. Please login with your new account on the next screen.",
                            "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                    new LoginScreen(userInterface);
                } else {
                    JOptionPane.showMessageDialog(personalManBaseScreen, "Thank you for registering " + registerUserRequest.getFirstName() + " " + registerUserRequest.getSurname() + " for PersonalMan. The account was created successfully.",
                            "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                    new AdminScreen(userInterface, company, adminUsername);
                }
            }
        });
        buttonPanel.add(registerButton);
        if ( company == null ) {
            JButton loginButton = new JButton("Back to login screen");
            loginButton.addActionListener(e -> {
                personalManBaseScreen.dispose();
                new LoginScreen(userInterface);
            });
            buttonPanel.add(loginButton);
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> userInterface.exit());
            buttonPanel.add(exitButton);
        } else {
            JButton backToAdminScreenButton = new JButton("Back to admin screen");
            backToAdminScreenButton.addActionListener(e -> {
                personalManBaseScreen.dispose();
                new AdminScreen(userInterface, company, adminUsername);
            });
            buttonPanel.add(backToAdminScreenButton);
            JButton logoutButton = new JButton("Logout");
            logoutButton.addActionListener(e -> {
                personalManBaseScreen.dispose();
                new LoginScreen(userInterface);
            });
            buttonPanel.add(logoutButton);
        }


        //add button panel to box layout
        add(buttonPanel);
    }

    /**
     * Check if the two passwords which were entered by the user are identical and if so return true.
     * @return a <code>boolean</code> which is true iff the two passwords entered are identical.
     */
    public boolean passwordsSame ( ) {
        return passwordField.getText().contentEquals(confirmPasswordField.getText());
    }

    public String getWorkingDays ( ) {
        String workingDays = "";
        if ( daysOfWeekCheckBoxes[0].isSelected() ) {
            workingDays += "Monday,";
        }
        if ( daysOfWeekCheckBoxes[1].isSelected() ) {
            workingDays += "Tuesday,";
        }
        if ( daysOfWeekCheckBoxes[2].isSelected() ) {
            workingDays += "Wednesday,";
        }
        if ( daysOfWeekCheckBoxes[3].isSelected() ) {
            workingDays += "Thursday,";
        }
        if ( daysOfWeekCheckBoxes[4].isSelected() ) {
            workingDays += "Friday,";
        }
        if ( daysOfWeekCheckBoxes[5].isSelected() ) {
            workingDays += "Saturday,";
        }
        if ( daysOfWeekCheckBoxes[6].isSelected() ) {
            workingDays += "Sunday,";
        }
        return workingDays.substring(0,workingDays.length()-1);
    }

}
