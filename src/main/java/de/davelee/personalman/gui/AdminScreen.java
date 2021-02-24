package de.davelee.personalman.gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;

import de.davelee.personalman.gui.admin.AddUserScreen;
import de.davelee.personalman.gui.admin.DeleteUserScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;
/**
 * Class to display the admin screen to the PersonalMan program.
 * @author Dave Lee
 */
public class AdminScreen extends PersonalManBaseScreen {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private final String company;
    private final String username;
    
    private static final Logger LOG = LoggerFactory.getLogger(AdminScreen.class);
    
    /**
     * Create a new admin screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     * @param company a <code>String</code> with the company that should be used in the client.
     * @param username a <code>String</code> containing the username of the admin currently logged in.
     */
    public AdminScreen(final UserInterface userInterface, final String company, final String username ) {
        
        super(userInterface);

        //Set the company and username to be used.
        this.company = company;
        this.username = username;
        
        //Create top, centre and bottom panels to add things to.
        JPanel topPanel = new JPanel();
        JPanel centrePanel = new JPanel(new GridLayout(2,2,5,5));
        JPanel bottomPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        centrePanel.setBackground(Color.WHITE); 
        bottomPanel.setBackground(Color.WHITE);
        
        //Construct logo panel to add to the centre panel.
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
        screenPanel.setBackground(Color.WHITE);
        screenPanel.add(topPanel, BorderLayout.NORTH);

        //Create new user.
        JPanel newUserPanel = new JPanel();
        newUserPanel.setBackground(Color.WHITE);
        JPanel newUserIconPanel = new JPanel();
        newUserIconPanel.setBackground(Color.WHITE);
        ImageDisplay newUserIconDisplay = new ImageDisplay("images/iconfinder_SEO-09_2588770.png", 0, 0);
        newUserIconDisplay.addMouseListener(addNewUserMouseListener());
        newUserIconDisplay.setSize(81,81);
        newUserIconDisplay.setBackground(Color.WHITE);
        newUserIconPanel.add(newUserIconDisplay);
        newUserPanel.add(newUserIconPanel);
        JLabel newUserLabel = new JLabel("Add New User", SwingConstants.CENTER);
        newUserLabel.setFont(new Font("Arial", Font.BOLD, 30));
        newUserLabel.addMouseListener(addNewUserMouseListener());
        newUserPanel.add(newUserLabel);
        centrePanel.add(newUserPanel);

        //Remove user.
        JPanel removeUserPanel = new JPanel();
        removeUserPanel.setBackground(Color.WHITE);
        JPanel removeUserIconPanel = new JPanel();
        removeUserIconPanel.setBackground(Color.WHITE);
        ImageDisplay removeUserIconDisplay = new ImageDisplay("images/iconfinder_SEO-07_2588768.png", 0, 0);
        removeUserIconDisplay.setSize(81,81);
        removeUserIconDisplay.setBackground(Color.WHITE);
        removeUserIconDisplay.addMouseListener(removeUserMouseListener());
        removeUserIconPanel.add(removeUserIconDisplay);
        removeUserPanel.add(removeUserIconPanel);
        JLabel removeUserLabel = new JLabel("Remove User", SwingConstants.CENTER);
        removeUserLabel.setFont(new Font("Arial", Font.BOLD, 30));
        removeUserLabel.addMouseListener(removeUserMouseListener());
        removeUserPanel.add(removeUserLabel);
        centrePanel.add(removeUserPanel);

        //Reset/change password for a user.
        JPanel resetUserPasswordPanel = new JPanel();
        resetUserPasswordPanel.setBackground(Color.WHITE);
        JPanel resetUserPasswordIconPanel = new JPanel();
        resetUserPasswordIconPanel.setBackground(Color.WHITE);
        ImageDisplay resetUserPasswordIconDisplay = new ImageDisplay("images/iconfinder_POWER - RESTART_16946.png", 0, 0);
        resetUserPasswordIconDisplay.setSize(81,81);
        resetUserPasswordIconDisplay.setBackground(Color.WHITE);
        resetUserPasswordIconPanel.add(resetUserPasswordIconDisplay);
        resetUserPasswordPanel.add(resetUserPasswordIconPanel);
        JLabel resetUserPasswordLabel = new JLabel("Reset User", SwingConstants.CENTER);
        resetUserPasswordLabel.setFont(new Font("Arial", Font.BOLD, 30));
        resetUserPasswordPanel.add(resetUserPasswordLabel);
        centrePanel.add(resetUserPasswordPanel);

        //Absence overview.
        JPanel absenceOverviewPanel = new JPanel();
        absenceOverviewPanel.setBackground(Color.WHITE);
        JPanel absenceOverviewIconPanel = new JPanel();
        absenceOverviewIconPanel.setBackground(Color.WHITE);
        ImageDisplay absenceOverviewIconDisplay = new ImageDisplay("images/iconfinder_travel_bag_4677535.png", 0, 0);
        absenceOverviewIconDisplay.setSize(81,81);
        absenceOverviewIconDisplay.setBackground(Color.WHITE);
        absenceOverviewIconDisplay.addMouseListener(absenceScreenMouseListener());
        absenceOverviewIconPanel.add(absenceOverviewIconDisplay);
        absenceOverviewPanel.add(absenceOverviewIconPanel);
        JLabel absenceOverviewLabel = new JLabel("Absence Overview", SwingConstants.CENTER);
        absenceOverviewLabel.setFont(new Font("Arial", Font.BOLD, 30));
        absenceOverviewLabel.addMouseListener(absenceScreenMouseListener());
        absenceOverviewPanel.add(absenceOverviewLabel);
        centrePanel.add(absenceOverviewPanel);
        
        //Create employee panel with image and label first.
        /*JPanel employeePanel = new JPanel();
        initialiseEmployeePanel(employeePanel);
        employeePanel.setLayout ( new BoxLayout ( employeePanel, BoxLayout.PAGE_AXIS ) );
        JPanel employeePicPanel = new JPanel();
        initialiseEmployeePanel(employeePicPanel);
        ImageIcon employeeImage = new ImageIcon(SplashScreen.class.getResource(userInterface.getUserInterfaceMessages().getEmployeesImage()));
        JLabel employeeLabel = new JLabel("", employeeImage, JLabel.CENTER);
        employeePicPanel.add(employeeLabel);
        employeePanel.add(employeePicPanel);
        employeePanel.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        centrePanel.add(employeePanel);
        
        //Create absence panel with image and label first.
        JPanel absencePanel = new JPanel(); 
        initialiseAbsencePanel(absencePanel);
        absencePanel.setLayout ( new BoxLayout ( absencePanel, BoxLayout.PAGE_AXIS ) );
        JPanel absencePicPanel = new JPanel();
        initialiseAbsencePanel(absencePicPanel);
        ImageIcon absenceImage = new ImageIcon(SplashScreen.class.getResource(userInterface.getUserInterfaceMessages().getAbsencesImage()));
        JLabel absenceLabel = new JLabel("", absenceImage, JLabel.CENTER);
        absencePicPanel.add(absenceLabel);
        absencePanel.add(absencePicPanel);
        absencePanel.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        centrePanel.add(absencePanel);*/

        screenPanel.add(centrePanel, BorderLayout.CENTER);

        //Create bottom button panel with logout and exit buttons.
        JPanel bottomButtonPanel = new JPanel(new GridLayout(2,2,5,5));
        bottomButtonPanel.setBackground(Color.WHITE);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener ( e -> {
            new LoginScreen(userInterface);
            dispose();
        });
        bottomButtonPanel.add(logoutButton);
        //Add button to return to welcome / admin screen if user has admin role.
        JButton exitButton = new JButton("Exit PersonalMan");
        exitButton.addActionListener( e -> {
            userInterface.exit();
        });
        bottomButtonPanel.add(exitButton);
        screenPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        
        //Add centre and bottom panels to container.
        container.add(screenPanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,600);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,600) );
        
    }

    /**
     * Make the Add New User Icon and Text clickable and go to the AddUserScreen.
     * @return a <code>MouseListener</code> object which can be added to Swing Components.
     */
    public MouseListener addNewUserMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AddUserScreen(userInterface, company, username);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    /**
     * Make the Remove User Icon and Text clickable and go to th AddUserScreen.
     * @return a <code>MouseListener</code> object which can be added to Swing Components.
     */
    public MouseListener removeUserMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new DeleteUserScreen(userInterface, company, username);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    /**
     * Make the Absence Screen Icon and Text clickable and go to the AbsenceScreen.
     * @return a <code>MouseListener</code> object which can be added to Swing Components.
     */
    public MouseListener absenceScreenMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AbsenceScreen(userInterface, LocalDate.now(), company, username);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

}

