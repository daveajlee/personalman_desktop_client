package de.davelee.personalman.gui;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.personalman.UserInterface;

/**
 * Class to mock the employee screen for testing purposes - by hiding some of the dialog features which do not
 * work in JUnit.
 * @author Dave Lee
 */
public class AbsenceScreenMock extends AbsenceScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = 549639407235875069L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AbsenceScreenMock.class);

	/**
	 * Create a new absence screen mock.
	 * @param ui a <code>UserInterface</code> object with the current user interface.
	 * @param date a <code>LocalDate</code> object with the date to display absences for.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> containing the username to display absences for.
	 */
	public AbsenceScreenMock ( final UserInterface ui, final LocalDate date, final String company, final String username ) {
		super(ui, date, company, username);
	}

	/**
	 * Instead of showing the message dialog - show a log message instead.
	 */
	public void showMessageDialog ( ) {
		LOG.info("Show dialog message");
	}

	/**
	 * Set the selected employee always to Max Mustermann.
	 */
	public void setSelectedEmployee ( ) {
		employeeBox.setSelectedItem("Max Mustermann(mmustermann)");
	}

	/**
	 * Instead of showing the statistics dialog - show a log message instead.
	 */
	public void showStatisticsDialog ( ) {
		LOG.info("Show statistics message");
	}
	
}
