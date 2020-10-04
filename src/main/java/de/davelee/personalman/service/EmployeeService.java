package de.davelee.personalman.service;

import de.davelee.personalman.api.RegisterUserRequest;
import de.davelee.personalman.api.UserRequest;
import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.api.UsersResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
	 * Delete the user corresponding to the company and username from the server.
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> object with the username to delete from the server.
	 */
	public void delete ( final String company, final String userName ) {
		restTemplate.delete(userServiceUrl + "?company=" + company + "&username=" + userName);
	}
	
}
