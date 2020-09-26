package de.davelee.personalman.gui.panels;

import de.davelee.personalman.UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * This panel creates a form where the user can enter personal information such as name etc. to register for PersonalMan.
 * The submit buttons are provided by the frame which adds this panel.
 */
public class RegisterPersonPanel extends JPanel {

    private final JTextField passwordField = new JTextField();
    private final JTextField confirmPasswordField = new JTextField();

    public RegisterPersonPanel (final UserInterface userInterface ) {

        this.setLayout(new GridLayout(7,2,5,5));
        this.setBackground(Color.WHITE);

        //Add first name.
        add(new JLabel("First Name:", JLabel.CENTER));
        JTextField firstNameField = new JTextField();
        add(firstNameField);

        //Add surname.
        add(new JLabel("Surname:", JLabel.CENTER));
        JTextField surnameField = new JTextField();
        add(surnameField);

        //Add company.
        add(new JLabel("Company:", JLabel.CENTER));
        JComboBox<String> companyBox = new JComboBox<>(userInterface.getUserInterfaceMessages().getSupportedCompaniesList().toArray(new String[userInterface.getUserInterfaceMessages().getSupportedCompaniesList().size()]));
        add(companyBox);

        //Add username.
        add(new JLabel("Username:", JLabel.CENTER));
        JTextField usernameField = new JTextField();
        add(usernameField);

        //Add password.
        add(new JLabel("Password:", JLabel.CENTER));
        add(passwordField);

        //Add confirm password.
        add(new JLabel("Confirm Password:", JLabel.CENTER));
        add(confirmPasswordField);

        //Add role.
        add(new JLabel("Role:", JLabel.CENTER));
        JComboBox<String> roleBox = new JComboBox<>(java.util.List.of("Employee", "Admin").toArray(new String[2]));
        add(roleBox);
    }

    /**
     * Check if the two passwords which were entered by the user are identical and if so return true.
     * @return a <code>boolean</code> which is true iff the two passwords entered are identical.
     */
    public boolean passwordsSame ( ) {
        return passwordField.getText().contentEquals(confirmPasswordField.getText());
    }



}
