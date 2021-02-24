package de.davelee.personalman.gui.admin;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.gui.PersonalManBaseScreen;
import de.davelee.personalman.gui.panels.RegisterPersonPanel;

import java.awt.*;

/**
 * Class to display the add new user screen for Adninistrators in the PersonalMan program.
 * @author Dave Lee
 */
public class AddUserScreen extends PersonalManBaseScreen {

    /**
     * Create a new add user screen with the following information.
     * @param ui a <code>UserInterface</code> object which contains the User Interface information.
     * @param company a <code>String</code> containing the name of the company that the admin belongs to.
     * @param adminUserName a <code>String</code> containing the user name of the administration.
     */
    public AddUserScreen (final UserInterface ui, final String company, final String adminUserName ) {
        super(ui);

        screenPanel.setBackground(Color.WHITE);
        screenPanel.add(new RegisterPersonPanel(ui, company, adminUserName, this));

        container.add(screenPanel, BorderLayout.CENTER);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(850,450);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(850,450) );
    }

}
