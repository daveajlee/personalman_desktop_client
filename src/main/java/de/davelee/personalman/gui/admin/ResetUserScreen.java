package de.davelee.personalman.gui.admin;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.ResetUserRequest;
import de.davelee.personalman.gui.AdminScreen;
import de.davelee.personalman.gui.LoginScreen;
import de.davelee.personalman.gui.PersonalManBaseScreen;
import de.davelee.personalman.gui.RegisterScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Class to display the reset user screen for Adninistrators in the PersonalMan program.
 * @author Dave Lee
 */
public class ResetUserScreen extends PersonalManBaseScreen {

    /**
     * Create a new reset screen with the following information.
     * @param ui a <code>UserInterface</code> object which contains the User Interface information.
     * @param company a <code>String</code> containing the name of the company that the admin belongs to.
     * @param adminUserName a <code>String</code> containing the user name of the administration.
     */
    public ResetUserScreen(final UserInterface ui, final String company, final String adminUserName ) {
        super(ui);

        screenPanel.setBackground(Color.WHITE);
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));

        JPanel userDataPanel = new JPanel(new GridLayout(3,2,5,5));
        userDataPanel.setBackground(Color.WHITE);
        //User Label + list of possible users.
        userDataPanel.add(new JLabel("User:", SwingConstants.LEFT));
        JComboBox<String> usernameBox = new JComboBox<>(ui.getUserNames(company));
        userDataPanel.add(usernameBox);
        //New Password label + field
        userDataPanel.add(new JLabel("New Password:", SwingConstants.LEFT));
        JPasswordField passwordField = new JPasswordField();
        userDataPanel.add(passwordField);
        //Confirm Password label + field
        userDataPanel.add(new JLabel("Confirm Password:", SwingConstants.LEFT));
        JPasswordField confirmPasswordField = new JPasswordField();
        userDataPanel.add(confirmPasswordField);
        screenPanel.add(userDataPanel);

        //Create button panel with three buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton deleteButton = new JButton("Reset");
        deleteButton.addActionListener(e -> {
            if ( !passwordField.getText().contentEquals(confirmPasswordField.getText())) {
                JOptionPane.showMessageDialog(this, "The passwords entered do not match. Please verify and submit your request again.",
                        "Passwords do not match.", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            } else {
                ResetUserRequest resetUserRequest = ResetUserRequest.builder()
                        .company(company)
                        .username(usernameBox.getSelectedItem().toString().split(" - ")[0])
                        .password(passwordField.getText())
                        .build();
                if (userInterface.resetUserPassword(resetUserRequest)) {
                    JOptionPane.showMessageDialog(this, "The password for user " + resetUserRequest.getUsername() + " was changed successfully.",
                            "Password changed successfully.", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                } else {
                    JOptionPane.showMessageDialog(this, "The password for user " + resetUserRequest.getUsername() + " could not be changed.",
                            "Password could not be changed.", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
                }
            }
        });
        buttonPanel.add(deleteButton);
        JButton backToAdminScreenButton = new JButton("Back to admin screen");
        backToAdminScreenButton.addActionListener(e -> {
            dispose();
            new AdminScreen(userInterface, company, adminUserName);
        });
        buttonPanel.add(backToAdminScreenButton);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen(userInterface);
        });
        buttonPanel.add(logoutButton);
        screenPanel.add(buttonPanel);

        container.add(screenPanel, BorderLayout.CENTER);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(600,200);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(600,200) );
    }

}
