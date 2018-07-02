package de.davelee.personalman.gui;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;

/**
 * Class to overwrite some click functionality for each of the dates for JUnit tests.
 * @author Dave Lee
 */
public class ClickDateMouseListenerMock extends ClickDateMouseListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClickDateMouseListenerMock.class);

	/**
	 * Create a new ClickDateMouseListener mock object.
	 * @param userInterface a <code>UserInterface</code> object which currently manages the user interface.
	 * @param localDate a <code>LocalDate</code> object representing the clickable date.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param monthPanel a <code>MonthPanel</code> object representing the displayed month panel.
	 */
	public ClickDateMouseListenerMock ( final UserInterface userInterface, final LocalDate localDate, final String company, final MonthPanel monthPanel ) {
		super(userInterface, localDate, company, monthPanel);
	}

	/**
	 * Instead of showing an option dialog, based on the supplied text return the option yes or no.
	 * @param text a <code>String</code> with the specified text.
	 * @return a <code>int</code> with the user-selected option.
	 */
	public int showOptionDialog( final String text ) {
		if ( text.contentEquals("No Absences planned for this date!") ) {
			return JOptionPane.NO_OPTION;
		}
		return JOptionPane.YES_OPTION;
	}

	/**
	 * Instead of creating a new absence screen, display a log message instead.
	 */
	public void createAddAbsenceScreen() {
		LOG.info("Displaying AddAbsenceScreen...");
	}

}
