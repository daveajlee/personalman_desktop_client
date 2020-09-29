package de.davelee.personalman.gui.panels;

import de.davelee.personalman.api.RegisterCompanyRequest;
import de.davelee.personalman.gui.LoginScreen;
import de.davelee.personalman.gui.RegisterScreen;

import javax.swing.*;
import java.awt.*;

/**
 * This panel creates a form where the user can enter company information such as name etc.
 * The submit buttons are provided by the frame which adds this panel.
 */
public class RegisterCompanyPanel extends JPanel {

    /**
     * Create a new panel and add all necessary components to this panel.
     */
    public RegisterCompanyPanel ( final RegisterScreen registerScreen ) {

        //create a new box layout for the grid panel and button panel.
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);

        //create a grid panel to show the required data in a table format
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,2,5,5));
        gridPanel.setBackground(Color.WHITE);

        //add company name
        gridPanel.add(new JLabel("Company Name:", JLabel.CENTER));
        JTextField nameField = new JTextField();
        gridPanel.add(nameField);

        //add default amount of annual leave
        gridPanel.add(new JLabel("Default Annual Leave", JLabel.CENTER));
        JSpinner annualLeaveSpinner = new JSpinner(new SpinnerNumberModel(25,1,365,1));
        gridPanel.add(annualLeaveSpinner);

        //add base country (for holidays etc.)
        gridPanel.add(new JLabel("Base Country", JLabel.CENTER));
        JComboBox<String> baseCountryBox = new JComboBox<>(java.util.List.of("Germany", "Scotland", "England").toArray(new String[3]));
        gridPanel.add(baseCountryBox);

        //add grid panel to box layout
        add(gridPanel);

        //Create button panel with three buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            if ( registerScreen.getUserInterface().registerCompany(RegisterCompanyRequest.builder()
                    .name(nameField.getText())
                    .defaultAnnualLeaveInDays(Integer.parseInt(annualLeaveSpinner.getValue().toString()))
                    .country(baseCountryBox.getSelectedItem().toString())
                    .build()) ) {
                JOptionPane.showMessageDialog(registerScreen, "Thank you for registering " + nameField.getText() + " for PersonalMan. Please start to register new users by clicking on the person tab.",
                        "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            } else {
                JOptionPane.showMessageDialog(registerScreen, "The company could not be registered either because the server is not available or the company already exists. Please verify and submit your registration request again.",
                        "Could not register company", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            }
            registerScreen.enablePersonTab();
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

}
