package de.davelee.personalman.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;

/**
 * Class to overwrite some click functionality for the next screen for JUnit tests.
 * @author Dave Lee
 */
public class NextScreenMouseListenerMock extends NextScreenMouseListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(NextScreenMouseListenerMock.class);

	/**
	 * Create a new mouse listener mock for the next screen.
	 * @param screenType a <code>ScreenType</code> object representing the screen type.
	 * @param userInterface a <code>UserInterface</code> object representing the current user interface.
	 * @param welcomeScreen a <code>WelcomeScreen</code> object representing the welcome screen to display to the user (if clicked).
	 * @param company a <code>String</code> with the company that should be used in the client.
	 * @param username a <code>String</code> with the username which may be null if no specific user info should be displayed.
	 */
	public NextScreenMouseListenerMock ( final ScreenType screenType, final UserInterface userInterface, final WelcomeScreen welcomeScreen,
										 final String company, final String username ) {
		super(screenType, userInterface, welcomeScreen, company, username);
	}

	/**
	 * Instead of creating the absence screen, simply display a log message.
	 */
	public void createAbsenceScreen() {
		LOG.info("Displaying Absence Screen");
	}

	/**
	 * Instead of creating the employee screen, simply display a log message.
	 */
	public void createEmployeeScreen() {
		LOG.info("Displaying Employee Screen");
	}
	
}
