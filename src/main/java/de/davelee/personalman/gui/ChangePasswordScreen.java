package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.ChangePasswordRequest;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Class to display the change password screen in the PersonalMan program.
 * @author Dave Lee
 */
public class ChangePasswordScreen extends PersonalManBaseScreen {

    /**
     * Create a new change password screen with the following information.
     * @param ui a <code>UserInterface</code> object which contains the User Interface information.
     * @param company a <code>String</code> containing the name of the company that the admin belongs to.
     * @param userName a <code>String</code> containing the user name of the user currently logged in.
     */
    public ChangePasswordScreen(final UserInterface ui, final String company, final String userName ) {
        super(ui);

        screenPanel.setBackground(Color.WHITE);
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));

        JPanel userDataPanel = new JPanel(new GridLayout(3,2,5,5));
        userDataPanel.setBackground(Color.WHITE);
        //User Label + list of possible users.
        userDataPanel.add(new JLabel("Current Password:", SwingConstants.LEFT));
        JPasswordField currentPasswordField = new JPasswordField();
        userDataPanel.add(currentPasswordField);
        //New Password label + field
        userDataPanel.add(new JLabel("New Password:", SwingConstants.LEFT));
        JPasswordField newPasswordField = new JPasswordField();
        userDataPanel.add(newPasswordField);
        //Confirm Password label + field
        userDataPanel.add(new JLabel("Confirm Password:", SwingConstants.LEFT));
        JPasswordField confirmPasswordField = new JPasswordField();
        userDataPanel.add(confirmPasswordField);
        screenPanel.add(userDataPanel);

        //Create button panel with three buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> {
            if ( !newPasswordField.getText().contentEquals(confirmPasswordField.getText())) {
                JOptionPane.showMessageDialog(this, "The passwords entered do not match. Please verify and submit your request again.",
                        "Passwords do not match.", JOptionPane.ERROR_MESSAGE, new ImageIcon(RegisterScreen.class.getResource("/images/personalmanlogo-icon.png")));
            } else {
                ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.builder()
                        .company(company)
                        .username(userName)
                        .currentPassword(currentPasswordField.getText())
                        .newPassword(newPasswordField.getText())
                        .build();
                if (userInterface.changePassword(changePasswordRequest)) {
                    JOptionPane.showMessageDialog(this, "Your password was changed successfully.",
                            "Password changed successfully.", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ChangePasswordScreen.class.getResource("/images/personalmanlogo-icon.png")));
                } else {
                    JOptionPane.showMessageDialog(this, "Your password could not be changed.",
                            "Password could not be changed.", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ChangePasswordScreen.class.getResource("/images/personalmanlogo-icon.png")));
                }
            }
        });
        buttonPanel.add(changePasswordButton);
        JButton backToAbsenceScreenButton = new JButton("Back to absence screen");
        backToAbsenceScreenButton.addActionListener(e -> {
            dispose();
            new AbsenceScreen(userInterface, LocalDate.now(), company, userName);
        });
        buttonPanel.add(backToAbsenceScreenButton);
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
