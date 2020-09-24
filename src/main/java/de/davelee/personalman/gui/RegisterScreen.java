package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Class to display the login screen to the PersonalMan program.
 * @author Dave Lee
 */
public class RegisterScreen extends PersonalManBaseScreen {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new register screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     */
    public RegisterScreen(final UserInterface userInterface ) {

        super(userInterface);

        //Create top, centre and bottom panels to add things to.
        JPanel topPanel = new JPanel();
        JPanel centrePanel = new JPanel(new GridLayout(7,2,5,5));
        JPanel bottomPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        centrePanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);

        //Construct logo panel to add to the top panel.
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(Color.WHITE);
        JLabel headingLabel = new JLabel(userInterface.getUserInterfaceMessages().getRegisterMessage(), SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 40));
        headingPanel.add(headingLabel);
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        ImageDisplay logoDisplay = new ImageDisplay("images/personalmanlogo-small.png", 0, 0);
        logoDisplay.setSize(162,81);
        logoDisplay.setBackground(Color.WHITE);
        logoPanel.add(logoDisplay);
        headingPanel.add(logoPanel);
        topPanel.add(headingPanel);

        //Add first name.
        centrePanel.add(new JLabel("First Name:", JLabel.CENTER));
        JTextField firstNameField = new JTextField();
        centrePanel.add(firstNameField);

        //Add surname.
        centrePanel.add(new JLabel("Surname:", JLabel.CENTER));
        JTextField surnameField = new JTextField();
        centrePanel.add(surnameField);

        //Add company.
        centrePanel.add(new JLabel("Company:", JLabel.CENTER));
        JComboBox<String> companyBox = new JComboBox(userInterface.getUserInterfaceMessages().getSupportedCompaniesList().toArray());
        centrePanel.add(companyBox);

        //Add username.
        centrePanel.add(new JLabel("Username:", JLabel.CENTER));
        JTextField usernameField = new JTextField();
        centrePanel.add(usernameField);

        //Add password.
        centrePanel.add(new JLabel("Password:", JLabel.CENTER));
        JTextField passwordField = new JPasswordField();
        centrePanel.add(passwordField);

        //Add confirm password.
        centrePanel.add(new JLabel("Confirm Password:", JLabel.CENTER));
        JTextField confirmPasswordField = new JPasswordField();
        centrePanel.add(confirmPasswordField);

        //Add role.
        centrePanel.add(new JLabel("Role:", JLabel.CENTER));
        JComboBox<String> roleBox = new JComboBox<String>(java.util.List.of("Employee", "Admin").toArray(new String[2]));
        centrePanel.add(roleBox);

        //Create footer panel with button panel and register panel.
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.PAGE_AXIS));
        footerPanel.setBackground(Color.WHITE);
        //Create button panel with two buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //First confirm that the passwords are equal.
                if ( !passwordField.getText().contentEquals(confirmPasswordField.getText())) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "The passwords entered do not match. Please verify and submit your registration request again.",
                            "Passwords do not match", JOptionPane.ERROR_MESSAGE, new ImageIcon(UserInterface.class.getResource("/images/personalmanlogo-icon.png")));
                }
                else {
                    dispose();
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Thank you for registering for PersonalMan. Your account was created successfully. Please login with your new account on the next screen.",
                            "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(UserInterface.class.getResource("/images/personalmanlogo-icon.png")));
                    new LoginScreen(userInterface);
                }
            }
        });
        buttonPanel.add(registerButton);
        JButton loginButton = new JButton("Back to login screen");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginScreen(userInterface);
            }
        });
        buttonPanel.add(loginButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInterface.exit();
            }
        });
        buttonPanel.add(exitButton);
        footerPanel.add(buttonPanel);
        bottomPanel.add(footerPanel);

        //Add centre and bottom panels to container.
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centrePanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,300);
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,300) );

    }

}
