package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel companyLabel = new JLabel("Company:", JLabel.CENTER);
        companyPanel.add(companyLabel);
        JComboBox<String> companyBox = new JComboBox(userInterface.getUserInterfaceMessages().getSupportedCompaniesList().toArray());
        companyPanel.add(companyBox);
        centrePanel.add(companyPanel);

        //Construct username panel to add to the centre panel.
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout ( new BoxLayout ( usernamePanel, BoxLayout.LINE_AXIS ) );
        JLabel usernameLabel = new JLabel("Username:", JLabel.CENTER);
        usernamePanel.add(usernameLabel);
        JTextField usernameField = new JTextField();
        usernamePanel.add(usernameField);
        centrePanel.add(usernamePanel);

        //Construct password panel to add to the centre panel.
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout ( new BoxLayout ( passwordPanel, BoxLayout.LINE_AXIS ) );
        JLabel passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordPanel.add(passwordLabel);
        JTextField passwordField = new JPasswordField();
        passwordPanel.add(passwordField);
        centrePanel.add(passwordPanel);

        //Create button panel with two buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeScreen(userInterface, companyBox.getSelectedItem().toString());
            }
        });
        bottomPanel.add(loginButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInterface.exit();
            }
        });
        bottomPanel.add(exitButton);

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
