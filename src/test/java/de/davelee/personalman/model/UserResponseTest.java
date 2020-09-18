package de.davelee.personalman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.davelee.personalman.api.UserResponse;

/**
 * Class to test the User Response object.
 * @author Dave Lee
 */
public class UserResponseTest {
	
	private static final String SURNAME = "Mustermann";
	private static final String WORKING_DAYS = "Saturday, Sunday";
	private static final String POSITION = "Tester";
	
	@Test
	/**
	 * Test case: Test the constructor by creating a new user.
	 * Expected result: the user response object is created with all fields set successfully.
	 */
	public void testConstructor() {
		UserResponse userResponse = new UserResponse("Max", SURNAME, "mmustermann", "MyCompany", 26, WORKING_DAYS, POSITION, "27-02-2015");
		assertEquals(userResponse.getFirstName(), "Max");
		assertEquals(userResponse.getSurname(), SURNAME);
		assertEquals(userResponse.getUsername(), "mmustermann");
		assertEquals(userResponse.getWorkingDays(), WORKING_DAYS);
		assertEquals(userResponse.getLeaveEntitlementPerYear(), 26);
		assertEquals(userResponse.getPosition(), POSITION);
		assertEquals(userResponse.getStartDate(), "27-02-2015");
		assertEquals("UserResponse(firstName=Max, surname=Mustermann, username=mmustermann, company=MyCompany, leaveEntitlementPerYear=26, workingDays=Saturday, Sunday, position=Tester, startDate=27-02-2015)", userResponse.toString());
	}
	
	@Test
	/**
	 * Test case: Test the setters to create a new user and check they work using the getter methods.
	 * Expected result: the user response object is created with all fields set successfully.
	 */
	public void testSetAndGet() {
		UserResponse userResponse = new UserResponse();
		userResponse.setLeaveEntitlementPerYear(26);
		userResponse.setFirstName("Max");
		userResponse.setSurname(SURNAME);
		userResponse.setUsername("mmustermann");
		userResponse.setWorkingDays(WORKING_DAYS);
		userResponse.setPosition(POSITION);
		userResponse.setStartDate("27-02-2015");
		userResponse.setCompany("MyCompany");
		assertEquals(userResponse.getFirstName(), "Max");
		assertEquals(userResponse.getSurname(), SURNAME);
		assertEquals(userResponse.getUsername(), "mmustermann");
		assertEquals(userResponse.getWorkingDays(), WORKING_DAYS);
		assertEquals(userResponse.getLeaveEntitlementPerYear(), 26);
		assertEquals(userResponse.getPosition(), POSITION);
		assertEquals(userResponse.getStartDate(), "27-02-2015");
		assertEquals(userResponse.getCompany(), "MyCompany");
		assertEquals("UserResponse(firstName=Max, surname=Mustermann, username=mmustermann, company=MyCompany, leaveEntitlementPerYear=26, workingDays=Saturday, Sunday, position=Tester, startDate=27-02-2015)", userResponse.toString());
	}

}
