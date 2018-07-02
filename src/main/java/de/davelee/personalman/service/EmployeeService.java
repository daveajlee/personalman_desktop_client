package de.davelee.personalman.service;

import de.davelee.personalman.api.UserRequest;
import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.api.UsersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to provide service operations for employees in the PersonalMan program.
 * @author Dave Lee
 */
@Service
public class EmployeeService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${server.userservice.url}")
	private String userServiceUrl;

	/**
	 * Retrieve the server url for communicating with the user server.
	 * @return a <code>String</code> with the server url for communicating with the user server.
	 */
	public String getUserServiceUrl() {
		return userServiceUrl;
	}

	/**
	 * Set the server url for communicating with the user server.
	 * @param userServiceUrl a <code>String</code> with the new server url for communicating with the user server.
	 */
	public void setUserServiceUrl(final String userServiceUrl) {
		this.userServiceUrl = userServiceUrl;
	}
	
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
	 */
	public UsersResponse findByCompany ( final String company ) {
		return restTemplate.getForObject(userServiceUrl + "s?company=" + company, UsersResponse.class);
	}
	
	/**
	 * Save the specified UserRequest object in the database.
	 * @param userRequest a <code>UserRequest</code> object to be saved to the server.
	 */
	public void save ( final UserRequest userRequest ) {
		restTemplate.postForObject(userServiceUrl, userRequest, UserRequest.class);
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
