package de.davelee.personalman.service;

import de.davelee.personalman.api.UserRequest;
import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.api.UsersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement some of the Employee Service functionality without having to run the backend server for JUnit tests.
 * @author Dave Lee
 */
public class EmployeeServiceMock extends EmployeeService {
	
	private final UserResponse employee = UserResponse.builder()
			.firstName("Max").surname("Mustermann").username("mmustermann").company("MyCompany").leaveEntitlementPerYear(4)
			.workingDays("Monday,Tuesday,Wednesday,Thursday,Friday").position("Tester").startDate("28-02-2015").build();

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceMock.class);

	/**
	 * Find all users who are associated with a particular company (fixed output to avoid backend server).
	 * @param company a <code>String</code> with the name of the company.
	 * @param token a <code>String</code> with the token of the currently logged in user.
	 * @return a <code>UsersResponse</code> object representing all users associated with the particular company.
	 */
	public UsersResponse findByCompany ( final String company, final String token ) {
		if ( "MyCompany".equalsIgnoreCase(company) ) {
			UserResponse[] employees = new UserResponse[2];
			employees[0] = employee;
			employees[1] = UserResponse.builder()
					.firstName("John")
					.surname("Smith")
					.username("jsmith")
					.company("MyCompany")
					.leaveEntitlementPerYear(30)
					.workingDays("Monday, Tuesday")
					.position("Producer")
					.startDate("01-03-2015")
					.build();
			return UsersResponse.builder()
					.userResponses(employees)
					.build();
		} else {
			return null;
		}
	}

	/**
	 * Find a user according to their company and user name (fixed output to avoid backend server).
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> with the user name.
	 * @param token a <code>String</code> with the token of the currently logged in user.
	 * @return a <code>UserResponse</code> representing the user which has this user name. Returns null if no matching user. User name is a unique field so no chance of more than one result!
	 */
	public UserResponse findByUserName( final String company, final String userName, final String token) {
		if ( "MyCompany".equalsIgnoreCase(company) && "mmustermann".equalsIgnoreCase(userName)) {
			return employee;
		} else if ( "MyCompany".equalsIgnoreCase(company) && "csmith".equalsIgnoreCase(userName)) {
			UserResponse userResponse = new UserResponse();
			userResponse.setFirstName("Chris");
			userResponse.setCompany("MyCompany");
			userResponse.setSurname("Smith");
			userResponse.setWorkingDays("Tuesday,Wednesday,Thursday,Friday,Saturday");
			userResponse.setPosition("Doctor");
			userResponse.setStartDate("01-04-2015");
			userResponse.setUsername("csmith");
			userResponse.setLeaveEntitlementPerYear(5);
			return userResponse;
		} else if ( "MyCompany".equalsIgnoreCase(company) && "jmctavish".equalsIgnoreCase(userName)) {
			UserResponse userResponse = new UserResponse();
			userResponse.setFirstName("Jock");
			userResponse.setCompany("MyCompany");
			userResponse.setSurname("McTavish");
			userResponse.setWorkingDays("Tuesday,Wednesday");
			userResponse.setPosition("Farmer");
			userResponse.setStartDate("01-04-2015");
			userResponse.setUsername("jmctavish");
			userResponse.setLeaveEntitlementPerYear(5);
			return userResponse;
		} else {
			return null;
		}
	}

	/**
	 * Do not save any data in the mock but display a log message.
	 * @param userRequest a <code>UserRequest</code> object to be saved to the server.
	 */
	public void save ( final UserRequest userRequest ) {
		LOG.info("The Mock does not save any data!");
	}

	/**
	 * Do not delete any data in the mock but display a log message.
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> object with the username to delete from the server.
	 * @param token a <code>String</code> with the token of the currently logged in user.
	 */
	public void delete ( final String company, final String userName, final String token ) {
		LOG.info("The Mock does not delete any data!");
	}

}
