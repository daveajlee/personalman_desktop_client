package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.gui.config.RegisterScreenConfig;
import de.davelee.personalman.gui.panels.RegisterCompanyPanel;
import de.davelee.personalman.gui.panels.RegisterPersonPanel;

import javax.swing.*;
import java.awt.*;

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
     * @param registerScreenConfig a <code>RegisterScreenConfig</code> object containing the localised text for the registration screen.
     */
    public RegisterScreen(final UserInterface userInterface, final RegisterScreenConfig registerScreenConfig) {

        super(userInterface);

        //Create top, centre and bottom panels to add things to.
        JPanel topPanel = new JPanel();
        JPanel centrePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        centrePanel.setBackground(Color.WHITE);
        bottomPanel.setBackground(Color.WHITE);

        //Construct logo panel to add to the top panel.
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(Color.WHITE);
        JLabel headingLabel = new JLabel(registerScreenConfig.getRegisterMessage(), SwingConstants.CENTER);
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

        //Use a tabbed pane to use between registering for companies and registering for people.
        RegisterCompanyPanel registerCompanyPanel = new RegisterCompanyPanel();
        RegisterPersonPanel registerPersonPanel = new RegisterPersonPanel(userInterface);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.add("Company", registerCompanyPanel);
        tabbedPane.add("Person", registerPersonPanel);
        centrePanel.add(tabbedPane);

        //Create footer panel with button panel and register panel.
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.PAGE_AXIS));
        footerPanel.setBackground(Color.WHITE);
        //Create button panel with two buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            //First confirm that the passwords are equal.
            if ( !registerPersonPanel.passwordsSame() ) {
                JOptionPane.showMessageDialog(RegisterScreen.this, "The passwords entered do not match. Please verify and submit your registration request again.",
                        "Passwords do not match", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            }
            else {
                dispose();
                JOptionPane.showMessageDialog(RegisterScreen.this, "Thank you for registering for PersonalMan. Your account was created successfully. Please login with your new account on the next screen.",
                        "Account Created", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                new LoginScreen(userInterface);
            }
        });
        buttonPanel.add(registerButton);
        JButton loginButton = new JButton("Back to login screen");
        loginButton.addActionListener(e -> {
            dispose();
            new LoginScreen(userInterface);
        });
        buttonPanel.add(loginButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> userInterface.exit());
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
        Dimension displayDim = new Dimension(750,500);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,500) );

    }

}
