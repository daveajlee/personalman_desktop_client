package de.davelee.personalman.gui.admin;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.gui.AdminScreen;
import de.davelee.personalman.gui.LoginScreen;
import de.davelee.personalman.gui.PersonalManBaseScreen;

import javax.swing.*;
import java.awt.*;

/**
 * Class to display the delete user screen for Adninistrators in the PersonalMan program.
 * @author Dave Lee
 */
public class DeleteUserScreen extends PersonalManBaseScreen {

    /**
     * Create a new delete screen with the following information.
     * @param ui a <code>UserInterface</code> object which contains the User Interface information.
     * @param company a <code>String</code> containing the name of the company that the admin belongs to.
     * @param adminUserName a <code>String</code> containing the user name of the administration.
     */
    public DeleteUserScreen (final UserInterface ui, final String company, final String adminUserName ) {
        super(ui);

        screenPanel.setBackground(Color.WHITE);
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));

        JPanel deleteUserPanel = new JPanel();
        deleteUserPanel.setLayout(new BoxLayout(deleteUserPanel, BoxLayout.PAGE_AXIS));
        deleteUserPanel.setBackground(Color.WHITE);
        deleteUserPanel.add(new JLabel("Please choose a username to remove from PersonalMan:", SwingConstants.LEFT));
        JComboBox<String> usernameBox = new JComboBox<>(ui.getUserNames(company));
        deleteUserPanel.add(usernameBox);
        screenPanel.add(deleteUserPanel);

        //Create button panel with three buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout ( new BoxLayout ( buttonPanel, BoxLayout.LINE_AXIS ) );
        buttonPanel.setBackground(Color.WHITE);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            if ( usernameBox.getSelectedItem() != null ) {
                String usernameToDelete = usernameBox.getSelectedItem().toString().split(" - ")[0];
                userInterface.removeEmployee(company, usernameToDelete);
                //If user wants to delete themselves, then return to login screen.
                if (usernameToDelete.contentEquals(adminUserName)) {
                    new LoginScreen(ui);
                    dispose();
                }
                //Otherwise return to admin screen.
                else {
                    new AdminScreen(ui, company, adminUserName);
                    dispose();
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
