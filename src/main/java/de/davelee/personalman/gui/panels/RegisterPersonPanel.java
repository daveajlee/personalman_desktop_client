package de.davelee.personalman.gui.panels;

import de.davelee.personalman.gui.LoginScreen;
import de.davelee.personalman.gui.RegisterScreen;

import javax.swing.*;
import java.awt.*;

/**
 * This panel creates a form where the user can enter personal information such as name etc. to register for PersonalMan.
 * The submit buttons are provided by the frame which adds this panel.
 */
public class RegisterPersonPanel extends JPanel {

    private final JTextField passwordField = new JTextField();
    private final JTextField confirmPasswordField = new JTextField();

    public RegisterPersonPanel (final RegisterScreen registerScreen ) {

        //create a new box layout for the grid panel and button panel.
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);

        //create a grid panel to show the required data in a table format
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(7,2,5,5));
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
        JComboBox<String> companyBox = new JComboBox<>(registerScreen.getUserInterface().getUserInterfaceMessages().getSupportedCompaniesList().toArray(new String[registerScreen.getUserInterface().getUserInterfaceMessages().getSupportedCompaniesList().size()]));
        gridPanel.add(companyBox);

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
                JOptionPane.showMessageDialog(registerScreen, "The passwords entered do not match. Please verify and submit your registration request again.",
                        "Passwords do not match", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            }
            else {
                registerScreen.dispose();
                JOptionPane.showMessageDialog(registerScreen, "Thank you for registering for PersonalMan. Your account was created successfully. Please login with your new account on the next screen.",
                        "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                new LoginScreen(registerScreen.getUserInterface());
            }
        });
        buttonPanel.add(registerButton);
        JButton loginButton = new JButton("Back to login screen");
        loginButton.addActionListener(e -> {
            registerScreen.dispose();
            new LoginScreen(registerScreen.getUserInterface());
        });
        buttonPanel.add(loginButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> registerScreen.getUserInterface().exit());
        buttonPanel.add(exitButton);

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



}
