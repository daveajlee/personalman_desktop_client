package de.davelee.personalman.gui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Parameter class for localisation of messages in the user interface.
 * @author Dave Lee
 */
@Component
public class UserInterfaceMessages {

	@Value("${message.welcome}")
	private String welcomeMessage;
	@Value("${message.exit}")
	private String exitMessage;
	@Value("${message.exit.title}")
	private String exitTitleMessage;
	@Value("${message.yes}")
	private String yesMessage;
	@Value("${message.no}")
	private String noMessage;
	@Value("${message.employees.startdate}")
	private String employeesStartDateMessage;
	@Value("${button.absences.stats}")
	private String absencesStatsButton;
	@Value("${message.absences.none}")
	private String absencesNoneMessage;
	@Value("${button.absences.add}")
	private String absencesAddButton;
	@Value("${button.absences.remove}")
	private String absencesRemoveButton;
	@Value("${button.absences.close}")
	private String absencesCloseButton;
	@Value("${message.absences.enddate}")
	private String absencesEndDateMessage;
	@Value("${message.absences.reason}")
	private String absencesReasonMessage;
	@Value("${message.stats.title}")
	private String statisticsTitleMessage;
	@Value("${splash.title}")
	private String splashTitle;
	@Value("${splash.copyright}")
	private String splashCopyright;
	@Value("${message.days}")
	private String daysMessage;
	@Value("${message.annualleave.error}")
	private String annualLeaveErrorMessage;
	@Value("${title.annualleave.error}")
	private String annualLeaveErrorTitle;
	@Value("${message.totake}")
	private String toTakeMessage;
	
	/**
	 * Return the localisation text for the message on the welcome screen.
	 * @return a <code>String</code> containing the localisation text for the message on the welcome screen.
	 */
	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	/**
	 * Return the localisation text for the message for the exit dialog.
	 * @return a <code>String</code> containing the localisation text for the message for the exit dialog.
	 */
	public String getExitMessage() {
		return exitMessage;
	}

	/**
	 * Return the localisation text for the message title for the exit dialog.
	 * @return a <code>String</code> containing the localisation text for the message for exit option on the welcome screen.
	 */
	public String getExitTitleMessage() {
		return exitTitleMessage;
	}

	/**
	 * Return the localisation text for yes on dialog boxes.
	 * @return a <code>String</code> containing the localisation text for yes on dialog boxes.
	 */
	public String getYesMessage() {
		return yesMessage;
	}

	/**
	 * Return the localisation text for no on dialog boxes.
	 * @return a <code>String</code> containing the localisation text for no on dialog boxes.
	 */
	public String getNoMessage() {
		return noMessage;
	}

	/**
	 * Return the localisation text for start date.
	 * @return a <code>String</code> containing the localisation text for start date.
	 */
	public String getEmployeesStartDateMessage() {
		return employeesStartDateMessage;
	}

	/**
	 * Return the localisation text for the statistics button on the absence screen.
	 * @return a <code>String</code> containing the localisation text for the statistics button on the absence screen.
	 */
	public String getAbsencesStatsButton() {
		return absencesStatsButton;
	}

	/**
	 * Return the localisation text for the text when there are no absences for a particular day.
	 * @return a <code>String</code> containing the localisation text for the text when there are no absences for a particular day.
	 */
	public String getAbsencesNoneMessage() {
		return absencesNoneMessage;
	}

	/**
	 * Return the localisation text for the add absence button.
	 * @return a <code>String</code> containing the localisation text for the add absence button.
	 */
	public String getAbsencesAddButton() {
		return absencesAddButton;
	}

	/**
	 * Return the localisation text for the remove absence button.
	 * @return a <code>String</code> containing the localisation text for the remove absence button.
	 */
	public String getAbsencesRemoveButton() {
		return absencesRemoveButton;
	}

	/**
	 * Return the localisation text for the close absence dialog box.
	 * @return a <code>String</code> containing the localisation text for the close absence dialog box.
	 */
	public String getAbsencesCloseButton() {
		return absencesCloseButton;
	}

	/**
	 * Return the localisation text for the end date of an absence.
	 * @return a <code>String</code> containing the localisation text for the end date of an absence.
	 */
	public String getAbsencesEndDateMessage() {
		return absencesEndDateMessage;
	}

	/**
	 * Return the localisation text for the reason heading of an absence.
	 * @return a <code>String</code> containing the localisation text for the reason heading of an absence.
	 */
	public String getAbsencesReasonMessage() {
		return absencesReasonMessage;
	}

	/**
	 * Return the localisation text for the title of the statistics dialog.
	 * @return a <code>String</code> containing the localisation text for the title of the statistics dialog.
	 */
	public String getStatisticsTitleMessage() {
		return statisticsTitleMessage;
	}

	/**
	 * Return the localisation text for the title on the splash screen.
	 * @return a <code>String</code> containing the localisation text for the title on the splash screen.
	 */
	public String getSplashTitle() {
		return splashTitle;
	}

	/**
	 * Return the localisation text for the copyright on the splash screen.
	 * @return a <code>String</code> containing the localisation text for the copyright on the splash screen.
	 */
	public String getSplashCopyright() {
		return splashCopyright;
	}

	/**
	 * Return the localisation text for days.
	 * @return a <code>String</code> containing the localisation text for days.
	 */
	public String getDaysMessage() {
		return daysMessage;
	}

	/**
	 * Set the localisation text for days.
	 * @param daysMessage a <code>String</code> containing the localisation text for days.
	 */
	public void setDaysMessage(final String daysMessage) {
		this.daysMessage = daysMessage;
	}

	/**
	 * Return the localisation text for the error message when insufficient annual leave.
	 * @return a <code>String</code> containing the localisation text for the error message when insufficient annual leave.
	 */
	public String getAnnualLeaveErrorMessage() {
		return annualLeaveErrorMessage;
	}

	/**
	 * Set the localisation text for the error message when insufficient annual leave.
	 * @param annualLeaveErrorMessage a <code>String</code> containing the localisation text for the error message when insufficient annual leave.
	 */
	public void setAnnualLeaveErrorMessage(final String annualLeaveErrorMessage) {
		this.annualLeaveErrorMessage = annualLeaveErrorMessage;
	}

	/**
	 * Return the localisation text for the title text on the error dialog when insufficient annual leave.
	 * @return a <code>String</code> containing the localisation text for the title text on the error dialog when insufficient annual leave.
	 */
	public String getAnnualLeaveErrorTitle() {
		return annualLeaveErrorTitle;
	}

	/**
	 * Set the localisation text for the title text on the error dialog when insufficient annual leave.
	 * @param annualLeaveErrorTitle a <code>String</code> containing the localisation text for the title text on the error dialog when insufficient annual leave.
	 */
	public void setAnnualLeaveErrorTitle(final String annualLeaveErrorTitle) {
		this.annualLeaveErrorTitle = annualLeaveErrorTitle;
	}

	/**
	 * Return the localisation text for to take.
	 * @return a <code>String</code> containing the localisation text for to take.
	 */
	public String getToTakeMessage() {
		return toTakeMessage;
	}

	/**
	 * Set the localisation text for to take.
	 * @param toTakeMessage a <code>String</code> containing the localisation text for to take.
	 */
	public void setToTakeMessage(final String toTakeMessage) {
		this.toTakeMessage = toTakeMessage;
	}

}
