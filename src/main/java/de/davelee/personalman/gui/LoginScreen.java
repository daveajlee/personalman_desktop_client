package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.LoginRequest;
import de.davelee.personalman.api.LoginResponse;
import de.davelee.personalman.api.UserResponse;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Class to display the login screen to the PersonalMan program.
 * @author Dave Lee
 */
public class LoginScreen extends PersonalManBaseScreen {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new login screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     */
    public LoginScreen (final UserInterface userInterface ) {

        super(userInterface);

        //Create top, centre and bottom panels to add things to.
        JPanel topPanel = new JPanel();
        JPanel centrePanel = new JPanel(new GridLayout(3,1,5,5));
        JPanel bottomPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        centrePanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);

        //Construct logo panel to add to the top panel.
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel(userInterface.getUserInterfaceMessages().getWelcomeMessage(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 40));
        welcomePanel.add(welcomeLabel);
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        ImageDisplay logoDisplay = new ImageDisplay("images/personalmanlogo-small.png", 0, 0);
        logoDisplay.setSize(162,81);
        logoDisplay.setBackground(Color.WHITE);
        logoPanel.add(logoDisplay);
        welcomePanel.add(logoPanel);
        topPanel.add(welcomePanel);

        //Construct company panel to add to the centre panel.
        JPanel companyPanel = new JPanel();
        companyPanel.setLayout ( new BoxLayout ( companyPanel, BoxLayout.LINE_AXIS ) );
        companyPanel.setBackground(Color.WHITE);
        JLabel companyLabel = new JLabel("Company:", JLabel.CENTER);
        companyPanel.add(companyLabel);
        JComboBox<String> companyBox = new JComboBox<>(userInterface.getCompanies().toArray(new String[userInterface.getCompanies().size()]));
        companyPanel.add(companyBox);
        centrePanel.add(companyPanel);

        //Construct username panel to add to the centre panel.
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout ( new BoxLayout ( usernamePanel, BoxLayout.LINE_AXIS ) );
        usernamePanel.setBackground(Color.WHITE);
        JLabel usernameLabel = new JLabel("Username:", JLabel.CENTER);
        usernamePanel.add(usernameLabel);
        JTextField usernameField = new JTextField();
        usernamePanel.add(usernameField);
        centrePanel.add(usernamePanel);

        //Construct password panel to add to the centre panel.
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout ( new BoxLayout ( passwordPanel, BoxLayout.LINE_AXIS ) );
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordPanel.add(passwordLabel);
        JTextField passwordField = new JPasswordField();
        passwordPanel.add(passwordField);
        centrePanel.add(passwordPanel);

        //Create footer panel with button panel and register panel.
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.PAGE_AXIS));
        footerPanel.setBackground(Color.WHITE);
        //Create button panel with two buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton loginButton = new JButton("Login");
        //If no companies available then do not allow login.
        if ( companyBox.getModel().getSize() == 0 ) {
            loginButton.setEnabled(false);
        }
        loginButton.addActionListener(e -> {
            LoginRequest loginRequest = LoginRequest.builder()
                    .company(companyBox.getSelectedItem().toString())
                    .username(usernameField.getText())
                    .password(passwordField.getText())
                    .build();
            LoginResponse loginResponse = userInterface.login(loginRequest);
            if ( loginResponse.getErrorMessage() != null ) {
                JOptionPane.showMessageDialog(this, loginResponse.getErrorMessage(),
                        "Failure with Login", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            } else {
                dispose();
                UserResponse userResponse = userInterface.getEmployeeByUserName(companyBox.getSelectedItem().toString(), usernameField.getText());
                if ( userResponse.getRole().contentEquals("Admin")) {
                    new WelcomeScreen(userInterface, companyBox.getSelectedItem().toString(), usernameField.getText());
                } else {
                    new AbsenceScreen(userInterface, LocalDate.now(), companyBox.getSelectedItem().toString(), usernameField.getText());
                }
            }
        });
        buttonPanel.add(loginButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> userInterface.exit() );
        buttonPanel.add(exitButton);
        footerPanel.add(buttonPanel);
        //Create register panel with message and button.
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.LINE_AXIS));
        registerPanel.setBackground(Color.WHITE);
        registerPanel.add(new JLabel("Not yet registered for PersonalMan?"));
        JButton registerButton = new JButton(("Register"));
        registerButton.addActionListener(e -> {
                dispose();
                new RegisterScreen(userInterface, userInterface.getRegisterScreenConfig());
        });
        registerPanel.add(registerButton);
        footerPanel.add(registerPanel);
        bottomPanel.add(footerPanel);

        //Add centre and bottom panels to container.
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centrePanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,300);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,300) );

    }

}
