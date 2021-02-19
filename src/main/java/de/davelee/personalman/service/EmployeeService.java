package de.davelee.personalman.service;

import de.davelee.personalman.api.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to provide service operations for employees in the PersonalMan program.
 * @author Dave Lee
 */
@Service
@Getter
@Setter
public class EmployeeService {

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${server.userservice.url}")
	private String userServiceUrl;

	@Value("${server.login.url}")
	private String loginUrl;
	
	/**
	 * Find a user according to their company and user name.
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> with the user name.
	 * @return a <code>UserResponse</code> representing the user which has this user name. Returns null if no matching user. User name is a unique field so no chance of more than one result!
	 */
	public UserResponse findByUserName ( final String company, final String userName ) {
		return restTemplate.getForObject(userServiceUrl + "?company=" + company + "&username=" + userName, UserResponse.class);
	}

	/**
	 * Find all users who are associated with a particular company.
	 * @param company a <code>String</code> with the name of the company.
	 * @return a <code>UsersResponse</code> object representing all users associated with the particular company.
	 *
	 */
	public UsersResponse findByCompany ( final String company )  {
		return restTemplate.getForObject(userServiceUrl + "s?company=" + company, UsersResponse.class);
	}
	
	/**
	 * Save the specified UserRequest object in the database.
	 * @param userRequest a <code>UserRequest</code> object to be saved to the server.
	 */
	public void save ( final UserRequest userRequest ) {
		restTemplate.postForObject(userServiceUrl, userRequest, UserRequest.class);
	}

	public void register (final RegisterUserRequest registerUserRequest, final int leaveEntitlementPerYear ) {
		UserRequest userRequest = UserRequest.builder()
				.firstName(registerUserRequest.getFirstName())
				.surname(registerUserRequest.getSurname())
				.company(registerUserRequest.getCompany())
				.username(registerUserRequest.getUsername())
				.password(registerUserRequest.getPassword())
				.workingDays(registerUserRequest.getWorkingDays())
				.startDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
				.position(registerUserRequest.getPosition())
				.leaveEntitlementPerYear(leaveEntitlementPerYear)
				.build();
		save(userRequest);
	}

	/**
	 * Attempt to log the user in based on the supplied request.
	 * @param loginRequest a <code>LoginRequest</code> object containing the company, username and password to use this for this login attempt.
	 * @return a <code>LoginResponse</code> object containing the response to this login from the server.
	 */
	public LoginResponse login ( final LoginRequest loginRequest ) {
		try {
			return restTemplate.postForObject(loginUrl, loginRequest, LoginResponse.class);
		} catch ( HttpClientErrorException exception ) {
			return LoginResponse.builder()
					.errorMessage(exception.getMessage().split(":")[2].split(",")[0].replace("\"", ""))
					.build();
		}
	}
	
	/**
	 * Delete the user corresponding to the company and username from the server.
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> object with the username to delete from the server.
	 */
	public void delete ( final String company, final String userName ) {
		restTemplate.delete(userServiceUrl + "?company=" + company + "&username=" + userName);
	}
	
}
