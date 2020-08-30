package de.davelee.personalman.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;
/**
 * Class to display the welcome screen to the PersonalMan program.
 * @author Dave Lee
 */
public class WelcomeScreen extends PersonalManBaseScreen {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String company;
    
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeScreen.class);
    
    /**
     * Create a new welcome screen.
     * @param userInterface a <code>UserInterface</code> object with the current user interface.
     * @param company a <code>String</code> with the company that should be used in the client.
     */
    public WelcomeScreen ( final UserInterface userInterface, final String company ) {
        
        super(userInterface);

        //Set the company to be used.
        this.company = company;
        
        //Create top, centre and bottom panels to add things to.
        JPanel topPanel = new JPanel();
        JPanel centrePanel = new JPanel(new GridLayout(1,2,5,5)); 
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
        ImageIcon logoImage = new ImageIcon(SplashScreen.class.getResource("/images/PersonalManlogo.png"));
        JLabel logoLabel = new JLabel("", logoImage, JLabel.CENTER);
        logoPanel.add(logoLabel);
        welcomePanel.add(logoPanel);
        topPanel.add(welcomePanel);
        
        //Create employee panel with image and label first.
        JPanel employeePanel = new JPanel(); 
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
        centrePanel.add(absencePanel);
        
        //Create exit panel with image and label first.
        JPanel exitPanel = new JPanel(); 
        initialiseExitPanel(exitPanel);
        exitPanel.setLayout ( new BoxLayout ( exitPanel, BoxLayout.PAGE_AXIS ) );
        JPanel exitPicPanel = new JPanel();
        initialiseExitPanel(exitPicPanel);
        ImageIcon exitImage = new ImageIcon(SplashScreen.class.getResource(userInterface.getUserInterfaceMessages().getExitImage()));
        JLabel exitLabel = new JLabel("", exitImage, JLabel.CENTER);
        exitPicPanel.add(exitLabel);
        exitPanel.add(exitPicPanel);
        exitPanel.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        bottomPanel.add(exitPanel);
        bottomPanel.addMouseListener (createExitMouseListener());
        
        //Add centre and bottom panels to container.
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centrePanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,600);
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,600) );
        
    }
    
    /**
     * Initialise the panel to create a new game.
     * @param panel a <code>JPanel</code> to diplay the new game panel.
     */
    public void initialiseEmployeePanel ( final JPanel panel ) {
        panel.setBackground(Color.WHITE);
        panel.addMouseListener ( new NextScreenMouseListener(ScreenType.EMPLOYEE_SCREEN, userInterface, this, company) );
    }
    
    /**
     * Initialise the panel for absences.
     * @param panel a <code>JPanel</code> to diplay the absence panel.
     */
    public void initialiseAbsencePanel ( final JPanel panel ) {
        panel.setBackground(Color.WHITE);
        panel.addMouseListener ( new NextScreenMouseListener(ScreenType.ABSENCE_SCREEN, userInterface, this, company) );
    }
    
    /**
     * Create a new exit mouse listener.
     * @return a <code>MouseListener</code> object for exit operations.
     */
    public MouseListener createExitMouseListener ( ) {
    	return new MouseListener () {
            public void mouseClicked(MouseEvent e) {
                userInterface.exit();
            }
            public void mousePressed(MouseEvent e) {
            	LOG.info("mousePressed method for exitMouseListener is not implemented!");
            }
            public void mouseReleased(MouseEvent e) {
            	LOG.info("mouseReleased method for exitMouseListener is not implemented!");
            }
            public void mouseEntered(MouseEvent e) {
            	LOG.info("mouseEntered method for exitMouseListener is not implemented!");
            }
            public void mouseExited(MouseEvent e) {
            	LOG.info("mouseExited method for exitMouseListener is not implemented!");
            }
        };
    }
    
    /**
     * Initialise the panel to exit.
     * @param panel a <code>JPanel</code> to diplay the exit panel.
     */
    public void initialiseExitPanel ( final JPanel panel ) {
        panel.setBackground(Color.WHITE);
        panel.addMouseListener (createExitMouseListener());
    }
    
}

