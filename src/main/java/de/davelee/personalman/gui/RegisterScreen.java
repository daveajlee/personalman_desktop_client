package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.gui.config.RegisterScreenConfig;
import de.davelee.personalman.gui.panels.RegisterCompanyPanel;
import de.davelee.personalman.gui.panels.RegisterPersonPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Class to display the login screen to the PersonalMan program.
 * @author Dave Lee
 */
@Getter
@Setter
public class RegisterScreen extends PersonalManBaseScreen {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UserInterface userInterface;

    private JTabbedPane tabbedPane;

    /**
     * Create a new register screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     * @param registerScreenConfig a <code>RegisterScreenConfig</code> object containing the localised text for the registration screen.
     */
    public RegisterScreen(final UserInterface userInterface, final RegisterScreenConfig registerScreenConfig) {

        super(userInterface);

        this.userInterface = userInterface;

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
        RegisterCompanyPanel registerCompanyPanel = new RegisterCompanyPanel(this);
        RegisterPersonPanel registerPersonPanel = new RegisterPersonPanel(this);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.add("Company", registerCompanyPanel);
        tabbedPane.add("Person", registerPersonPanel);
        //If no companies available then disable person tab.
        if ( userInterface.getCompanies().size() == 0 ) {
            tabbedPane.setEnabledAt(1, false);
        }
        centrePanel.add(tabbedPane);

        //Add centre and bottom panels to container.
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centrePanel, BorderLayout.CENTER);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(850,500);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(850,500) );

    }

    public void enablePersonTab ( ) {
        tabbedPane.setEnabledAt(1, true);
    }

}
