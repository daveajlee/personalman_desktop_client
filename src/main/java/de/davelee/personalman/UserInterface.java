package de.davelee.personalman;

import de.davelee.personalman.api.AbsenceRequest;
import de.davelee.personalman.api.AbsenceResponse;
import de.davelee.personalman.api.AbsencesResponse;
import de.davelee.personalman.api.UserRequest;
import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.api.UsersResponse;
import de.davelee.personalman.gui.ReasonNames;
import de.davelee.personalman.gui.SplashScreen;
import de.davelee.personalman.gui.UserInterfaceMessages;
import de.davelee.personalman.gui.WelcomeScreen;
import de.davelee.personalman.service.AbsenceService;
import de.davelee.personalman.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.net.ConnectException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * This class controls the user interface of the Personalman program. 
 * @author Dave Lee.
 */
@Component
public class UserInterface {
    
    private JFrame currentFrame;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private AbsenceService absenceService;
    
    @Autowired
    private UserInterfaceMessages userInterfaceMessages;
    
    @Autowired
    private ReasonNames reasonNames;
    
    @Value("${locale.language}")
    private String localeLanguage;
    
    private Locale myLocale;
    
    private static final Logger LOG = LoggerFactory.getLogger(UserInterface.class);

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    /**
     * Retrieve the employee service to run tasks on employees.
     * @return a <code>EmployeeService</code> object representing an employee service to run tasks on.
     */
    public EmployeeService getEmployeeService() {
		return employeeService;
	}
    
    /**
     * Set the employee service to run tasks on employees.
     * @param employeeService a <code>EmployeeService</code> object representing an employee service to run tasks on.
     */
	public void setEmployeeService(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
     * Retrieve the absence service to run tasks on absences.
     * @return a <code>AbsenceService</code> object representing an absence service to run tasks on.
     */
	public AbsenceService getAbsenceService() {
		return absenceService;
	}
	
	/**
     * Set the absence service to run tasks on absences.
     * @param absenceService a <code>AbsenceService</code> object representing an absence service to run tasks on.
     */
	public void setAbsenceService(final AbsenceService absenceService) {
		this.absenceService = absenceService;
	}

	/**
	 * Retrieve the user interface messages to allow localisation of user interface prompts.
	 * @return a <code>UserInterfaceMessages</code> object representing the current localisation.
	 */
	public UserInterfaceMessages getUserInterfaceMessages ( ) {
    	return userInterfaceMessages;
    }
    
	/**
	 * Set the user interface messages to allow a new localisation of user interface prompts.
	 * @param userInterfaceMessages a <code>UserInterfaceMessages</code> object representing the new localisation.
	 */
    public void setUserInterfaceMessages ( final UserInterfaceMessages userInterfaceMessages ) {
    	this.userInterfaceMessages = userInterfaceMessages;
    }
    
    /**
     * Retrieve the reason names to allow localisation of absence reasons.
     * @return a <code>ReasonNames</code> object representing the current localisation.
     */
    public ReasonNames getReasonNames ( ) {
    	return reasonNames;
    }
    
    /**
     * Set the reason names to allow a new localisation of absence reasons.
     * @param reasonNames a <code>ReasonNames</code> object representing the new localisation.
     */
    public void setReasonNames ( final ReasonNames reasonNames ) {
    	this.reasonNames = reasonNames;
    }
    
    /**
     * Retrieve the language to control localisation.
     * @return a <code>String</code> with the current localisation language.
     */
    public String getLocaleLanguage() {
		return localeLanguage;
	}

    /**
     * Set the language to control localisation
     * @param localeLanguage a <code>String</code> with the new localisation language.
     */
	public void setLocaleLanguage(final String localeLanguage) {
		this.localeLanguage = localeLanguage;
	}
	
	/**
	 * Determine the Java Locale based on the localisation language.
	 * @param localeLanguage a <code>String</code> with the localisation language. Currently only German supported. Default is English.
	 */
	public void determineLocale ( final String localeLanguage ) {
		if ( localeLanguage.contentEquals("German")) {
			setMyLocale(Locale.GERMAN);
		} else {
			setMyLocale(Locale.ENGLISH);
		}
	}

	/**
	 * Return the current Java Locale.
	 * @return a <code>Locale</code> object representing the current Java Locale.
	 */
	public Locale getMyLocale() {
		return myLocale;
	}

	/**
	 * Set a new Java Locale.
	 * @param myLocale a <code>Locale</code> object representing the new Java Locale.
	 */
	public void setMyLocale(final Locale myLocale) {
		this.myLocale = myLocale;
	}

	/**
	 * Get all user names for a particular company as a String array.
	 * @param company a <code>String</code> with the name of the company.
	 * @return a <code>String</code> array with all usernames for this company.
	 */
	public String[] getUserNames(final String company) throws ConnectException {
		UsersResponse usersResponse = employeeService.findByCompany(company);
		if ( usersResponse == null ) {
			return new String[0];
		}
    	UserResponse[] userResponses = usersResponse.getUserResponses();
    	String[] userNames = new String[userResponses.length];
    	int count = 0;
    	for ( UserResponse userResponse : userResponses ) {
    		userNames[count] = userResponse.getUsername();
    		count++;
    	}
    	return userNames;
    }
   
    /**
     * Add a user to the server.
     * @param firstName a <code>String</code> with the first name of the user.
     * @param lastName a <code>String</code> with the surname of the user.
     * @param userName a <code>String</code> with the user name of the user.
	 * @param company a <code>String</code> with the name of the company.
     * @param annualLeave a <code>int</code> with the number of days of annual leave per year the user is entitled to.
     * @param workingDays a <code>String</code> as comma-separated String with the working days that a user has per week e.g. Monday,Tuesday,Wednesday
     * @param position a <code>String</code> with the position of the user.
     * @param startDate a <code>String</code> with the start date of the user in format dd-MM-yyyy.
     */
    public void addEmployee ( final String firstName, final String lastName, final String userName, final String company, final int annualLeave, final String workingDays, final String position, final String startDate) {
    	UserRequest userRequest = new UserRequest();
		userRequest.setFirstName(firstName);
		userRequest.setSurname(lastName);
		userRequest.setUsername(userName);
		userRequest.setLeaveEntitlementPerYear(annualLeave);
		userRequest.setPosition(position);
		userRequest.setWorkingDays(workingDays);
		userRequest.setStartDate(startDate);
		userRequest.setCompany(company);
		employeeService.save(userRequest);
    }
    
    /**
     * Remove the supplied user matching the company and user from the server.
	 * @param company a <code>String</code> with the name of the company.
     * @param userName a <code>String</code> object representing the username to be deleted from the server.
     */
    public void removeEmployee ( final String company, final String userName ) {
    	employeeService.delete(company, userName);
    }
    
    /**
     * Retrieve a user by their company and user name. Each user has a different user name.
	 * @param company a <code>String</code> with the name of the company.
     * @param userName a <code>String</code> with the user name of the user to be retrieved.
     * @return a <code>UserResponse</code> object representing the user matching the supplied user name or null if no user is found.
     */
    public UserResponse getEmployeeByUserName ( final String company, final String userName ) {
    	return employeeService.findByUserName(company, userName);
    }
    
    /**
     * Add an absence.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param userName a <code>String</code> with the name of the employee in format firstName lastName(username) who is absent.
     * @param startDate a <code>LocalDate</code> with the start date of the absence in format dd-MM-yyyy.
     * @param endDate a <code>LocalDate</code> with the end date of the absence in format dd-MM-yyyy.
     * @param reason a <code>String</code> with the reason for the absence.
     * @return a <code>boolean</code> which is true iff the absence was added successfully.
     */
    public boolean addAbsence ( final String company, final String userName, final String startDate, final String endDate, final String reason ) {
    	//Return the result of saving the absence to the server.
    	return saveAbsence(company, userName, startDate, endDate, reason);
    }


    
    /**
     * Save the result to the database.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param userName a <code>String</code> with the user name of the user who is absent.
     * @param startDate a <code>String</code> with the start date of the absence in format dd-MM-yyyy.
     * @param endDate a <code>String</code> with the end date of the absence in format dd-MM-yyyy.
     * @param category a <code>String</code> object representing the category for the absence.
     */
    private boolean saveAbsence ( final String company, final String userName,
									  final String startDate, final String endDate, final String category) {
		return absenceService.save(new AbsenceRequest(company, userName, startDate, endDate, category));
    }
    
    /**
     * Get all absences for a particular date.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param date a <code>String</code> with the date to get absences for in format dd-MM-yyyy.
     * @return a <code>List</code> of <code>AbsenceResponse</code> objects representing all absences being taken by all employees on the supplied date.
     */
    public List<AbsenceResponse> getAbsences ( final String company, final String date ) {
    	return absenceService.findByDate(company, date);
    }
    
    /**
     * Delete the specified absences from the database.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the user name of the user who's absences should be deleted.
	 * @param startDate a <code>String</code> with the start date to get absences for in format dd-MM-yyyy.
	 * @param endDate a <code>String</code> with the end date to get absences for in format dd-MM-yyyy.
     */
    public void deleteAbsences ( final String company, final String username, final String startDate, final String endDate ) {
    	absenceService.delete(company, username, startDate, endDate);
    }
    
    /**
     * Calculate all absences by reason for the supplied company, user name and year.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param userName a <code>String</code> with the user name.
     * @param year a <code>int</code> with the year to find absences for.
     * @return a formatted <code>String</code> with a reason-by-reason calculation of all absences for this employee for the supplied year
	 * or a text if the server is not reachable.
     */
    public String getStatistics ( final String company, final String userName, final int year ) {
    	AbsencesResponse absencesResponse = absenceService.findByNameAndYear(company, userName, year);
    	if ( absencesResponse == null ) {
    		return "Could not reach PersonalMan server so no statistics can be displayed!";
		}
    	StringBuilder returnTextBuilder = new StringBuilder();
    	returnTextBuilder.append("Illness: " + absencesResponse.getStatisticsMap().get("Illness") + " " + userInterfaceMessages.getDaysMessage() + "\n");
    	UserResponse userResponse = employeeService.findByUserName(company, userName);
    	long numAnnualLeaveRemaining = userResponse.getLeaveEntitlementPerYear() - absenceService.countByNameAndYearAndReason(company, userName, year, "Holiday");
    	returnTextBuilder.append("Holiday: " + absencesResponse.getStatisticsMap().get("Holiday") + " " + userInterfaceMessages.getDaysMessage() + " (" + userInterfaceMessages.getToTakeMessage() + " " + numAnnualLeaveRemaining + " " + userInterfaceMessages.getDaysMessage() + ")\n");
    	returnTextBuilder.append("Trip: " + absencesResponse.getStatisticsMap().get("Trip") + " " + userInterfaceMessages.getDaysMessage() + "\n");
    	returnTextBuilder.append("Conference: " + absencesResponse.getStatisticsMap().get("Conference") + " " + userInterfaceMessages.getDaysMessage() + "\n");
    	long numDaysInLieuRemaining = absenceService.countByNameAndYearAndReason(company, userName, year, "Day in Lieu Request") - absencesResponse.getStatisticsMap().get("Day in Lieu");
    	returnTextBuilder.append("Day in Lieu: " + absencesResponse.getStatisticsMap().get("Day in Lieu") + " " + userInterfaceMessages.getDaysMessage() + " (" + userInterfaceMessages.getToTakeMessage() + " " + numDaysInLieuRemaining + " " + userInterfaceMessages.getDaysMessage() + ")\n");
    	returnTextBuilder.append("Federal Holiday: " + absencesResponse.getStatisticsMap().get("Federal Holiday") + " " + userInterfaceMessages.getDaysMessage() + "\n");
    	return returnTextBuilder.toString();
    }
    
    /**
     * Set the current frame displayed to the user.
     * @param currentFrame a <code>JFrame</code> with the current frame.
     */
    public void setCurrentFrame ( final JFrame currentFrame ) {
        this.currentFrame = currentFrame;
    }
    
    /**
     * Get the current frame displayed to the user.
     * @return a <code>JFrame</code> with the current frame.
     */
    public JFrame getCurrentFrame ( ) {
        return currentFrame;
    }
    
    /**
     * Confirm and exit the PersonalMan program.
     */
    public void exit ( ) {
		ImageIcon imageIcon = new ImageIcon(UserInterface.class.getResource("/images/personalmanlogo-icon.png"));
        //Confirm user did wish to exit.
        int result = JOptionPane.showOptionDialog(currentFrame, getUserInterfaceMessages().getExitMessage(), 
        		getUserInterfaceMessages().getExitTitleMessage(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
        		imageIcon, new String[] { getUserInterfaceMessages().getYesMessage(), getUserInterfaceMessages().getNoMessage() }, getUserInterfaceMessages().getNoMessage());
        if ( result == JOptionPane.YES_OPTION ) {
            System.exit(0);
        }
    }
    
}
