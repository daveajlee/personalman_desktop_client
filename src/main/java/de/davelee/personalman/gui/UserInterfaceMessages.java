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
	@Value("${image.employees}")
	private String employeesImage;
	@Value("${image.absences}")
	private String absencesImage;
	@Value("${image.exit}")
	private String exitImage;
	@Value("${message.slogan}")
	private String sloganMessage;
	@Value("${message.exit}")
	private String exitMessage;
	@Value("${message.exit.title}")
	private String exitTitleMessage;
	@Value("${message.yes}")
	private String yesMessage;
	@Value("${message.no}")
	private String noMessage;
	@Value("${message.employees.title}")
	private String employeesTitleMessage;
	@Value("${message.employees.name}")
	private String employeesNameMessage;
	@Value("${message.employees.username}")
	private String employeesUserNameMessage;
	@Value("${message.employees.company}")
	private String employeesCompanyMessage;
	@Value("${message.employees.position}")
	private String employeesPositionMessage;
	@Value("${message.employees.startdate}")
	private String employeesStartDateMessage;
	@Value("${message.employees.annualleave}")
	private String employeesAnnualLeaveMessage;
	@Value("${message.employees.workingdays}")
	private String employeesWorkingDaysMessage;
	@Value("${button.employees.add}")
	private String employeesAddButton;
	@Value("${button.employees.remove}")
	private String employeesRemoveButton;
	@Value("${button.employees.reset}")
	private String employeesResetButton;
	@Value("${button.employees.welcome}")
	private String employeesWelcomeButton;
	@Value("${message.absences.title}")
	private String absencesTitleMessage;
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
	@Value("${message.absences.employee}")
	private String absencesEmployeeMessage;
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
	 * Set the localisation text for the message on the welcome screen.
	 * @param welcomeMessage a <code>String</code> containing the localisation text for the message on the welcome screen.
	 */
	public void setWelcomeMessage(final String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	/**
	 * Return the localisation path to the picture for employees on the welcome screen.
	 * @return a <code>String</code> containing the localisation path to the picture for employees on the welcome screen.
	 */
	public String getEmployeesImage() {
		return employeesImage;
	}

	/**
	 * Set the localisation path to the picture for employees on the welcome screen.
	 * @param employeesImage a <code>String</code> containing the localisation path to the picture for employees on the welcome screen.
	 */
	public void setEmployeesImage(final String employeesImage) {
		this.employeesImage = employeesImage;
	}

	/**
	 * Return the localisation path to the picture for absences on the welcome screen.
	 * @return a <code>String</code> containing the localisation path to the picture for absences on the welcome screen.
	 */
	public String getAbsencesImage() {
		return absencesImage;
	}

	/**
	 * Set the localisation path to the picture for absences on the welcome screen.
	 * @param absencesImage a <code>String</code> containing the localisation path to the picture for absences on the welcome screen.
	 */
	public void setAbsencesImage(final String absencesImage) {
		this.absencesImage = absencesImage;
	}

	/**
	 * Return the localisation path to the picture for exit on the welcome screen.
	 * @return a <code>String</code> containing the localisation path to the picture for exit on the welcome screen.
	 */
	public String getExitImage() {
		return exitImage;
	}

	/**
	 * Set the localisation path to the picture for exit on the welcome screen.
	 * @param exitImage a <code>String</code> containing the localisation path to the picture for exit on the welcome screen.
	 */
	public void setExitImage(final String exitImage) {
		this.exitImage = exitImage;
	}

	/**
	 * Return the localisation text for the slogan part of the program title.
	 * @return a <code>String</code> containing the localisation text for the slogan part of the program title.
	 */
	public String getSloganMessage() {
		return sloganMessage;
	}

	/**
	 * Set the localisation text for the slogan part of the program title.
	 * @param sloganMessage a <code>String</code> containing the localisation text for the slogan part of the program title.
	 */
	public void setSloganMessage(final String sloganMessage) {
		this.sloganMessage = sloganMessage;
	}

	/**
	 * Return the localisation text for the message for the exit dialog.
	 * @return a <code>String</code> containing the localisation text for the message for the exit dialog.
	 */
	public String getExitMessage() {
		return exitMessage;
	}

	/**
	 * Set the localisation text for the message for the exit dialog.
	 * @param exitMessage a <code>String</code> containing the localisation text for the message for the exit dialog.
	 */
	public void setExitMessage(final String exitMessage) {
		this.exitMessage = exitMessage;
	}

	/**
	 * Return the localisation text for the message title for the exit dialog.
	 * @return a <code>String</code> containing the localisation text for the message for exit option on the welcome screen.
	 */
	public String getExitTitleMessage() {
		return exitTitleMessage;
	}

	/**
	 * Set the localisation text for the message title for the exit dialog.
	 * @param exitTitleMessage a <code>String</code> containing the localisation text for the message title for the exit dialog.
	 */
	public void setExitTitleMessage(final String exitTitleMessage) {
		this.exitTitleMessage = exitTitleMessage;
	}

	/**
	 * Return the localisation text for yes on dialog boxes.
	 * @return a <code>String</code> containing the localisation text for yes on dialog boxes.
	 */
	public String getYesMessage() {
		return yesMessage;
	}

	/**
	 * Set the localisation text for yes on dialog boxes.
	 * @param yesMessage a <code>String</code> containing the localisation text for yes on dialog boxes.
	 */
	public void setYesMessage(final String yesMessage) {
		this.yesMessage = yesMessage;
	}

	/**
	 * Return the localisation text for no on dialog boxes.
	 * @return a <code>String</code> containing the localisation text for no on dialog boxes.
	 */
	public String getNoMessage() {
		return noMessage;
	}

	/**
	 * Set the localisation text for no on dialog boxes.
	 * @param noMessage a <code>String</code> containing the localisation text for no on dialog boxes.
	 */
	public void setNoMessage(final String noMessage) {
		this.noMessage = noMessage;
	}

	/**
	 * Return the localisation text for name of employee.
	 * @return a <code>String</code> containing the localisation text for name of employee.
	 */
	public String getEmployeesNameMessage() {
		return employeesNameMessage;
	}

	/**
	 * Set the localisation text for name of employee.
	 * @param employeesNameMessage a <code>String</code> containing the localisation text for name of employee.
	 */
	public void setEmployeesNameMessage(final String employeesNameMessage) {
		this.employeesNameMessage = employeesNameMessage;
	}

	/**
	 * Return the localisation text for username of employee.
	 * @return a <code>String</code> containing the localisation text for username of employee.
	 */
	public String getEmployeesUserNameMessage() {
		return employeesUserNameMessage;
	}

	/**
	 * Set the localisation text for username of employee.
	 * @param employeesUserNameMessage a <code>String</code> containing the localisation text for username of employee.
	 */
	public void setEmployeesUserNameMessage(final String employeesUserNameMessage) {
		this.employeesUserNameMessage = employeesUserNameMessage;
	}

	/**
	 * Return the localisation text for company of employee.
	 * @return a <code>String</code> containing the localisation text for company of employee.
	 */
	public String getEmployeesCompanyMessage() {
		return employeesCompanyMessage;
	}

	/**
	 * Set the localisation text for company of employee.
	 * @param employeesCompanyMessage a <code>String</code> containing the localisation text for company of employee.
	 */
	public void setEmployeesCompanyMessage(final String employeesCompanyMessage) {
		this.employeesCompanyMessage = employeesCompanyMessage;
	}

	/**
	 * Return the localisation text for position.
	 * @return a <code>String</code> containing the localisation text for position.
	 */
	public String getEmployeesPositionMessage() {
		return employeesPositionMessage;
	}

	/**
	 * Set the localisation text for position.
	 * @param employeesPositionMessage a <code>String</code> containing the localisation text for position.
	 */
	public void setEmployeesPositionMessage(final String employeesPositionMessage) {
		this.employeesPositionMessage = employeesPositionMessage;
	}

	/**
	 * Return the localisation text for start date.
	 * @return a <code>String</code> containing the localisation text for start date.
	 */
	public String getEmployeesStartDateMessage() {
		return employeesStartDateMessage;
	}

	/**
	 * Set the localisation text for start date.
	 * @param employeesStartDateMessage a <code>String</code> containing the localisation text for start date.
	 */
	public void setEmployeesStartDateMessage(final String employeesStartDateMessage) {
		this.employeesStartDateMessage = employeesStartDateMessage;
	}

	/**
	 * Return the localisation text for annual leave.
	 * @return a <code>String</code> containing the localisation text for annual leave.
	 */
	public String getEmployeesAnnualLeaveMessage() {
		return employeesAnnualLeaveMessage;
	}

	/**
	 * Set the localisation text for annual leave.
	 * @param employeesAnnualLeaveMessage a <code>String</code> containing the localisation text for annual leave.
	 */
	public void setEmployeesAnnualLeaveMessage(final String employeesAnnualLeaveMessage) {
		this.employeesAnnualLeaveMessage = employeesAnnualLeaveMessage;
	}

	/**
	 * Return the localisation text for working days.
	 * @return a <code>String</code> containing the localisation text for working days.
	 */
	public String getEmployeesWorkingDaysMessage() {
		return employeesWorkingDaysMessage;
	}

	/**
	 * Set the localisation text for working days.
	 * @param employeesWorkingDaysMessage a <code>String</code> containing the localisation text for working days.
	 */
	public void setEmployeesWorkingDaysMessage(final String employeesWorkingDaysMessage) {
		this.employeesWorkingDaysMessage = employeesWorkingDaysMessage;
	}

	/**
	 * Return the localisation text for the title of employee screen.
	 * @return a <code>String</code> containing the localisation text for the title of employee screen.
	 */
	public String getEmployeesTitleMessage() {
		return employeesTitleMessage;
	}

	/**
	 * Set the localisation text for the title of employee screen.
	 * @param employeesTitleMessage a <code>String</code> containing the localisation text for the title of employee screen.
	 */
	public void setEmployeesTitleMessage(final String employeesTitleMessage) {
		this.employeesTitleMessage = employeesTitleMessage;
	}

	/**
	 * Return the localisation text for add employee button.
	 * @return a <code>String</code> containing the localisation text for add employee button.
	 */
	public String getEmployeesAddButton() {
		return employeesAddButton;
	}

	/**
	 * Set the localisation text for add employee button.
	 * @param employeesAddButton a <code>String</code> containing the localisation text for add employee button.
	 */
	public void setEmployeesAddButton(final String employeesAddButton) {
		this.employeesAddButton = employeesAddButton;
	}

	/**
	 * Return the localisation text for remove employee button.
	 * @return a <code>String</code> containing the localisation text for remove employee button.
	 */
	public String getEmployeesRemoveButton() {
		return employeesRemoveButton;
	}

	/**
	 * Set the localisation text for remove employee button.
	 * @param employeesRemoveButton a <code>String</code> containing the localisation text for remove employee button.
	 */
	public void setEmployeesRemoveButton(final String employeesRemoveButton) {
		this.employeesRemoveButton = employeesRemoveButton;
	}

	/**
	 * Return the localisation text for the reset option on employee screen.
	 * @return a <code>String</code> containing the localisation text for the reset option on employee screen.
	 */
	public String getEmployeesResetButton() {
		return employeesResetButton;
	}

	/**
	 * Set the localisation text for the reset option on employee screen.
	 * @param employeesResetButton a <code>String</code> containing the localisation text for the reset option on employee screen.
	 */
	public void setEmployeesResetButton(final String employeesResetButton) {
		this.employeesResetButton = employeesResetButton;
	}

	/**
	 * Return the localisation text for the employee text on the welcome screen.
	 * @return a <code>String</code> containing the localisation text for the employee text on the welcome screen.
	 */
	public String getEmployeesWelcomeButton() {
		return employeesWelcomeButton;
	}

	/**
	 * Set the localisation text for the employee text on the welcome screen.
	 * @param employeesWelcomeButton a <code>String</code> containing the localisation text for the employee text on the welcome screen.
	 */
	public void setEmployeesWelcomeButton(final String employeesWelcomeButton) {
		this.employeesWelcomeButton = employeesWelcomeButton;
	}

	/**
	 * Return the localisation text for the title of absence screen.
	 * @return a <code>String</code> containing the localisation text for the title of absence screen.
	 */
	public String getAbsencesTitleMessage() {
		return absencesTitleMessage;
	}

	/**
	 * Set the localisation text for the title of absence screen.
	 * @param absencesTitleMessage a <code>String</code> containing the localisation text for the title of absence screen.
	 */
	public void setAbsencesTitleMessage(final String absencesTitleMessage) {
		this.absencesTitleMessage = absencesTitleMessage;
	}

	/**
	 * Return the localisation text for the statistics button on the absence screen.
	 * @return a <code>String</code> containing the localisation text for the statistics button on the absence screen.
	 */
	public String getAbsencesStatsButton() {
		return absencesStatsButton;
	}

	/**
	 * Set the localisation text for the statistics button on the absence screen.
	 * @param absencesStatsButton a <code>String</code> containing the localisation text for the statistics button on the absence screen.
	 */
	public void setAbsencesStatsButton(final String absencesStatsButton) {
		this.absencesStatsButton = absencesStatsButton;
	}

	/**
	 * Return the localisation text for the text when there are no absences for a particular day.
	 * @return a <code>String</code> containing the localisation text for the text when there are no absences for a particular day.
	 */
	public String getAbsencesNoneMessage() {
		return absencesNoneMessage;
	}

	/**
	 * Set the localisation text for the text when there are no absences for a particular day.
	 * @param absencesNoneMessage a <code>String</code> containing the localisation text for the text when there are no absences for a particular day.
	 */
	public void setAbsencesNoneMessage(final String absencesNoneMessage) {
		this.absencesNoneMessage = absencesNoneMessage;
	}

	/**
	 * Return the localisation text for the add absence button.
	 * @return a <code>String</code> containing the localisation text for the add absence button.
	 */
	public String getAbsencesAddButton() {
		return absencesAddButton;
	}

	/**
	 * Set the localisation text for the add absence button.
	 * @param absencesAddButton a <code>String</code> containing the localisation text for the add absence button.
	 */
	public void setAbsencesAddButton(final String absencesAddButton) {
		this.absencesAddButton = absencesAddButton;
	}

	/**
	 * Return the localisation text for the remove absence button.
	 * @return a <code>String</code> containing the localisation text for the remove absence button.
	 */
	public String getAbsencesRemoveButton() {
		return absencesRemoveButton;
	}

	/**
	 * Set the localisation text for the remove absence button.
	 * @param absencesRemoveButton a <code>String</code> containing the localisation text for the remove absence button.
	 */
	public void setAbsencesRemoveButton(final String absencesRemoveButton) {
		this.absencesRemoveButton = absencesRemoveButton;
	}

	/**
	 * Return the localisation text for the close absence dialog box.
	 * @return a <code>String</code> containing the localisation text for the close absence dialog box.
	 */
	public String getAbsencesCloseButton() {
		return absencesCloseButton;
	}

	/**
	 * Set the localisation text for the close absence dialog box.
	 * @param absencesCloseButton a <code>String</code> containing the localisation text for the close absence dialog box.
	 */
	public void setAbsencesCloseButton(final String absencesCloseButton) {
		this.absencesCloseButton = absencesCloseButton;
	}

	/**
	 * Return the localisation text for the employee text on an absence screen.
	 * @return a <code>String</code> containing the localisation text for the employee text on an absence screen.
	 */
	public String getAbsencesEmployeeMessage() {
		return absencesEmployeeMessage;
	}

	/**
	 * Set the localisation text for the employee text on an absence screen.
	 * @param absencesEmployeeMessage a <code>String</code> containing the localisation text for the employee text on an absence screen.
	 */
	public void setAbsencesEmployeeMessage(final String absencesEmployeeMessage) {
		this.absencesEmployeeMessage = absencesEmployeeMessage;
	}

	/**
	 * Return the localisation text for the end date of an absence.
	 * @return a <code>String</code> containing the localisation text for the end date of an absence.
	 */
	public String getAbsencesEndDateMessage() {
		return absencesEndDateMessage;
	}

	/**
	 * Set the localisation text for the end date of an absence.
	 * @param absencesEndDateMessage a <code>String</code> containing the localisation text for the end date of an absence.
	 */
	public void setAbsencesEndDateMessage(final String absencesEndDateMessage) {
		this.absencesEndDateMessage = absencesEndDateMessage;
	}

	/**
	 * Return the localisation text for the reason heading of an absence.
	 * @return a <code>String</code> containing the localisation text for the reason heading of an absence.
	 */
	public String getAbsencesReasonMessage() {
		return absencesReasonMessage;
	}

	/**
	 * Set the localisation text for the reason heading of an absence.
	 * @param absencesReasonMessage a <code>String</code> containing the localisation text for the reason heading of an absence.
	 */
	public void setAbsencesReasonMessage(final String absencesReasonMessage) {
		this.absencesReasonMessage = absencesReasonMessage;
	}

	/**
	 * Return the localisation text for the title of the statistics dialog.
	 * @return a <code>String</code> containing the localisation text for the title of the statistics dialog.
	 */
	public String getStatisticsTitleMessage() {
		return statisticsTitleMessage;
	}

	/**
	 * Set the localisation text for the title of the statistics dialog.
	 * @param statisticsTitleMessage a <code>String</code> containing the localisation text for the title of the statistics dialog.
	 */
	public void setStatisticsTitleMessage(final String statisticsTitleMessage) {
		this.statisticsTitleMessage = statisticsTitleMessage;
	}

	/**
	 * Return the localisation text for the title on the splash screen.
	 * @return a <code>String</code> containing the localisation text for the title on the splash screen.
	 */
	public String getSplashTitle() {
		return splashTitle;
	}

	/**
	 * Set the localisation text for the title on the splash screen.
	 * @param splashTitle a <code>String</code> containing the localisation text for the title on the splash screen.
	 */
	public void setSplashTitle(final String splashTitle) {
		this.splashTitle = splashTitle;
	}

	/**
	 * Return the localisation text for the copyright on the splash screen.
	 * @return a <code>String</code> containing the localisation text for the copyright on the splash screen.
	 */
	public String getSplashCopyright() {
		return splashCopyright;
	}

	/**
	 * Set the localisation text for the copyright on the splash screen.
	 * @param splashCopyright a <code>String</code> containing the localisation text for the copyright on the splash screen.
	 */
	public void setSplashCopyright(final String splashCopyright) {
		this.splashCopyright = splashCopyright;
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
