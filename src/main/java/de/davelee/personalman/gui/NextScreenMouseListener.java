package de.davelee.personalman.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;

/**
 * Mouse Listener to process the next screen button.
 * @author Dave Lee
 */
public class NextScreenMouseListener implements MouseListener {
	
	private ScreenType screenType;
	private UserInterface userInterface;
	private WelcomeScreen welcomeScreen;
	private String company;
	private String username;
	
	private static final Logger LOG = LoggerFactory.getLogger(NextScreenMouseListener.class);
	
	/**
	 * Create a new mouse listener for the next screen.
	 * @param screenType a <code>ScreenType</code> object representing the screen type.
	 * @param userInterface a <code>UserInterface</code> object representing the current user interface.
	 * @param welcomeScreen a <code>WelcomeScreen</code> object representing the welcome screen to display to the user (if clicked).
	 * @param company a <code>String</code> with the company that should be used in the client.
	 * @param username a <code>String</code> with the username which may be null if no specific user info should be displayed.
	 */
	public NextScreenMouseListener ( final ScreenType screenType, final UserInterface userInterface, final WelcomeScreen welcomeScreen,
									 final String company, final String username ) {
		this.screenType = screenType;
		this.userInterface = userInterface;
		this.welcomeScreen = welcomeScreen;
		this.company = company;
		this.username = username;
	}
	
	/**
	 * Process the event when the user clicks the mouse - by absenceScreen move to absence screen - by employeeScreen move to employee screen.
	 * @param mouseEvent a <code>MouseEvent</code> object with the mouse event.
	 */
	public void mouseClicked(final MouseEvent mouseEvent) {
    	if ( screenType == ScreenType.ABSENCE_SCREEN) {
    		createAbsenceScreen();
    		welcomeScreen.dispose();
    	} else if ( screenType == ScreenType.EMPLOYEE_SCREEN) {
    		createEmployeeScreen();
    		welcomeScreen.dispose();
    	} else {
    		LOG.warn("Screen type not defined - cannot move to next screen!");
    	}
    	
    }
	
	/**
	 * Create a new absence screen based on the user interface and the current date.
	 */
	public void createAbsenceScreen() {
		new AbsenceScreen(userInterface, LocalDate.now(), company, username);
	}
	
	/**
	 * Create a new employee screen based on the user interface.
	 */
	public void createEmployeeScreen() {
		new EmployeeScreen(userInterface, company, username);
	}
	
	/**
	 * Perform actions when the user presses the mouse button (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
    public void mousePressed(final MouseEvent event) {
    	LOG.info("mousePressed method for absenceMouseListener is not implemented!");
    }
    
    /**
	 * Perform actions when the user releases the mouse button (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
    public void mouseReleased(final MouseEvent event) {
    	LOG.info("mouseReleased method for absenceMouseListener is not implemented!");
    }
    
    /**
	 * Perform actions when the mouse enters the clickable area (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
    public void mouseEntered(final MouseEvent event) {
    	LOG.info("mouseEntered method for absenceMouseListener is not implemented!");
    }
    
    /**
	 * Perform actions when the mouse is exited from the clickable area (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
    public void mouseExited(final MouseEvent event) {
    	LOG.info("mouseExited method for absenceMouseListener is not implemented!");
    }

}
