package de.davelee.personalman;

import de.davelee.personalman.api.*;
import de.davelee.personalman.gui.ReasonNames;
import de.davelee.personalman.gui.UserInterfaceMessages;
import de.davelee.personalman.gui.config.RegisterScreenConfig;
import de.davelee.personalman.service.AbsenceService;
import de.davelee.personalman.service.CompanyService;
import de.davelee.personalman.service.EmployeeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * This class controls the user interface of the Personalman program. 
 * @author Dave Lee.
 */
@Component
@Getter
@Setter
public class UserInterface {
    
    private JFrame currentFrame;

	@Autowired
	private AbsenceService absenceService;

	@Autowired
	private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private UserInterfaceMessages userInterfaceMessages;
    
    @Autowired
    private ReasonNames reasonNames;

    @Autowired
	private RegisterScreenConfig registerScreenConfig;
    
    @Value("${locale.language}")
    private String localeLanguage;
    
    private Locale myLocale;

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private String token;

	/**
	 * Default value for annual leave if user not logged in.
	 */
	@Value("${default.annualleave}")
	private int annualLeave;

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
	 * Register a company by saving the request to the server. The company is successfully registered if true is returned.
	 * @param registerCompanyRequest a <code>RegisterCompanyRequest</code> object containing all of the data needed to store the company.
	 * @return a <code>boolean</code> whoch is true iff the company is registered i.e. saved successfully.
	 */
	public boolean registerCompany (final RegisterCompanyRequest registerCompanyRequest ) {
		try {
			return companyService.registerCompany(registerCompanyRequest);
		} catch ( Exception exception ) {
			//If the server is not available, return false as company could not be added.
			return false;
		}
	}

	/**
	 * Retrieve all companies from the server. If no companies are available or the server is not available then an empty list will be returned.
	 * @return a <code>List</code> of <code>String</code> objects with the name of companies stored on the server.
	 */
	public List<String> getCompanies ( ) {
		try {
			List<String> companyNames = companyService.getCompanies();
			if ( companyNames != null ) {
				return companyService.getCompanies();
			} else {
				return List.of();
			}
		} catch ( Exception exception ) {
			return List.of();
		}
	}

	/**
	 * Register a new user based on the supplied request.
	 * Retrieve the default annual leave days for the company and forward to employee service to register.
	 * @param registerUserRequest a <code>RegisterUserRequest</code> object containing the data about this user.
	 */
	public void registerUser ( final RegisterUserRequest registerUserRequest ) {
		int defaultAnnualLeave;
		try {
			defaultAnnualLeave = companyService.getCompany(registerUserRequest.getCompany(), getToken()).getDefaultAnnualLeaveInDays();
		} catch ( HttpClientErrorException errorException ) {
			//If not logged in then add annual leave with 0.
			defaultAnnualLeave = annualLeave;
		}
		employeeService.register(registerUserRequest, defaultAnnualLeave);
	}

	/**
	 * Attempt to log the user in based on the supplied request.
	 * @param loginRequest a <code>LoginRequest</code> object containing the company, username and password to use this for this login attempt.
	 * @return a <code>LoginResponse</code> object containing the response to this login from the server.
	 */
	public LoginResponse login ( final LoginRequest loginRequest ) {
		LoginResponse loginResponse = employeeService.login(loginRequest);
		//Save a token if the user logged in successfully.
		if ( loginResponse.getToken() != null ) {
			token = loginResponse.getToken();
		}
		return employeeService.login(loginRequest);
	}

	/**
	 * Return the current token that the user is using.
	 * @return token a <code>String</code> with the current token of the user.
	 */
	public String getToken ( ) {
		return token;
	}

	/**
	 * Attempt to reset the password of the supplied user at the supplied company.
	 * @param resetUserRequest a <code>ResetUserRequest</code> object containing the company, username and new password.
	 * @return a <code>boolean</code> which is true iff the reset of the password was successful.
	 */
	public boolean resetUserPassword ( final ResetUserRequest resetUserRequest ) {
		resetUserRequest.setToken(token);
		return employeeService.resetPassword(resetUserRequest);
	}

	/**
	 * Attempt to change the password of the supplied user at the supplied company.
	 * @param changePasswordRequest a <code>ChangePasswordRequest</code> object containing the company, username, current password and new password.
	 * @return a <code>boolean</code> which is true iff the reset of the password was successful.
	 */
	public boolean changePassword ( final ChangePasswordRequest changePasswordRequest ) {
		changePasswordRequest.setToken(token);
		return employeeService.changePassword(changePasswordRequest);
	}

	/**
	 * Get all user names for a particular company as a String array.
	 * @param company a <code>String</code> with the name of the company.
	 * @return a <code>String</code> array with all usernames for this company.
	 */
	public String[] getUserNames(final String company) {
		UsersResponse usersResponse = employeeService.findByCompany(company, token);
		if ( usersResponse == null ) {
			return new String[0];
		}
    	UserResponse[] userResponses = usersResponse.getUserResponses();
    	String[] userNames = new String[userResponses.length];
    	int count = 0;
    	for ( UserResponse userResponse : userResponses ) {
    		userNames[count] = userResponse.getUsername() + " - " + userResponse.getFirstName() + " " + userResponse.getSurname();
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
    	employeeService.delete(company, userName, token);
    }
    
    /**
     * Retrieve a user by their company and user name. Each user has a different user name.
	 * @param company a <code>String</code> with the name of the company.
     * @param userName a <code>String</code> with the user name of the user to be retrieved.
	 * @param token a <code>String</code> with the token of the currently logged in user.
     * @return a <code>UserResponse</code> object representing the user matching the supplied user name or null if no user is found.
     */
    public UserResponse getEmployeeByUserName ( final String company, final String userName, final String token ) {
    	return employeeService.findByUserName(company, userName, token);
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
		return absenceService.save(AbsenceRequest.builder()
				.company(company)
				.username(userName)
				.startDate(startDate)
				.endDate(endDate)
				.category(category)
				.token(token)
				.build());
    }
    
    /**
     * Get all absences for a particular date.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param date a <code>String</code> with the date to get absences for in format dd-MM-yyyy.
     * @return a <code>List</code> of <code>AbsenceResponse</code> objects representing all absences being taken by all employees on the supplied date.
     */
    public List<AbsenceResponse> getAbsences ( final String company, final String date ) {
    	return absenceService.findByDate(company, date, token);
    }
    
    /**
     * Delete the specified absences from the database.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the user name of the user who's absences should be deleted.
	 * @param startDate a <code>String</code> with the start date to get absences for in format dd-MM-yyyy.
	 * @param endDate a <code>String</code> with the end date to get absences for in format dd-MM-yyyy.
     */
    public void deleteAbsences ( final String company, final String username, final String startDate, final String endDate ) {
    	absenceService.delete(company, username, startDate, endDate, token);
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
    	AbsencesResponse absencesResponse = absenceService.findByNameAndYear(company, userName, year, token);
    	if ( absencesResponse == null ) {
    		return "Could not reach PersonalMan server so no statistics can be displayed!";
		}
    	StringBuilder returnTextBuilder = new StringBuilder();
    	returnTextBuilder.append(getStatisticsForCategory("Illness", absencesResponse));
    	UserResponse userResponse = employeeService.findByUserName(company, userName, token);
    	long numAnnualLeaveRemaining = userResponse.getLeaveEntitlementPerYear() - absencesResponse.getStatisticsMap().get("Holiday") /*absenceService.countByNameAndYearAndReason(company, userName, year, "Holiday", token)*/;
    	returnTextBuilder.append(composeReturnText("Holiday: ", absencesResponse.getStatisticsMap().get("Holiday"), numAnnualLeaveRemaining));
    	returnTextBuilder.append(getStatisticsForCategory("Trip", absencesResponse));
    	returnTextBuilder.append(getStatisticsForCategory("Conference", absencesResponse));
    	long numDaysInLieuRemaining = 0; // 0 if no days in lieu requests exist.
    	if ( absencesResponse.getStatisticsMap().get("Day in Lieu Request") != null ) {
			numDaysInLieuRemaining = absencesResponse.getStatisticsMap().get("Day in Lieu Request") - absencesResponse.getStatisticsMap().get("Day in Lieu");
		}
    	returnTextBuilder.append(composeReturnText("Day in Lieu: ", absencesResponse.getStatisticsMap().get("Day in Lieu"), numDaysInLieuRemaining ));
    	returnTextBuilder.append(getStatisticsForCategory("Federal Holiday", absencesResponse));
    	return returnTextBuilder.toString();
    }

	/**
	 * Private helper method to compose the return message for the absence response per category.
	 * @param category a <code>String</code> with the name of the category.
	 * @param daysTaken a <code>int</code> with the number of days already taken this year for that category.
	 * @param daysRemaining a <code>int</code> with the number of days still to take this year for that category.
	 * @return a <code>String</code> with the text to display to the user.
	 */
    private String composeReturnText ( final String category, final int daysTaken, final long daysRemaining ) {
    	StringBuilder returnTextBuilder = new StringBuilder();
		returnTextBuilder.append(category);
		returnTextBuilder.append(daysTaken);
		returnTextBuilder.append(" ");
		returnTextBuilder.append(userInterfaceMessages.getDaysMessage());
		returnTextBuilder.append(" (");
		returnTextBuilder.append(userInterfaceMessages.getToTakeMessage());
		returnTextBuilder.append(" ");
		returnTextBuilder.append(daysRemaining);
		returnTextBuilder.append(" ");
		returnTextBuilder.append(userInterfaceMessages.getDaysMessage());
		returnTextBuilder.append(")\n");
		return returnTextBuilder.toString();
	}

	/**
	 * Private Helper Method to display statistics for the supplied category based on the supplied AbsencesResponse object.
	 * @param category a <code>String</code> containing the name of the category to retrieve data for (without any special formatting)
	 * @param absencesResponse a <code>AbsencesResponse</code> object containing the data to display.
	 * @return a <code>String</code> in the format category: x days.
	 */
	private String getStatisticsForCategory ( final String category, final AbsencesResponse absencesResponse ) {
		return category + ": " + absencesResponse.getStatisticsMap().get(category) + " " + userInterfaceMessages.getDaysMessage() + "\n";
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
