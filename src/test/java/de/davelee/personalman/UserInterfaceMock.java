package de.davelee.personalman;

import de.davelee.personalman.api.AbsenceResponse;
import de.davelee.personalman.api.UserResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class mocks some of the methods in the UserInterface to allow JUnit testing.
 * @author Dave Lee
 */
public class UserInterfaceMock extends UserInterface {

	/**
	 * Get all absences for a particular date (fixed output to ensure no contact with backend server).
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param date a <code>String</code> with the date to get absences for in format dd-MM-yyyy.
	 * @return a <code>List</code> of <code>AbsenceResponse</code> objects representing all absences being taken by all employees on the supplied date.
	 */
	public List<AbsenceResponse> getAbsences ( final String company, final String date ) {
		if ( LocalDate.parse(date, UserInterface.DATE_TIME_FORMATTER).isEqual(LocalDate.of(2015, 4, 3))) {
			List<AbsenceResponse> absences = new ArrayList<>();
			AbsenceResponse absence = new AbsenceResponse("MyCompany", "mmustermann", "03-04-2015", "06-04-2015", "Federal Holiday");
			absences.add(absence);
			return absences;
		}
		return new ArrayList<>();
	}

	/**
	 * Get all user names as a String array (fixed output to ensure no contact with backend server).
	 * @return a <code>String</code> array with all usernames.
	 */
	public String[] getUserNames ( ) {
		return new String[] { "Max Mustermann(mmustermann)" };
	}

	/**
	 * Retrieve a user by their company and user name. Each user has a different user name. (fixed output to ensure no contact with backend server)
	 * @param company a <code>String</code> with the name of the company.
	 * @param userName a <code>String</code> with the user name of the user to be retrieved.
	 * @return a <code>UserResponse</code> object representing the user matching the supplied user name or null if no user is found.
	 */
	public UserResponse getEmployeeByUserName ( final String company, final String userName ) {
		LocalDate startDate = LocalDate.of(2015, 3, 1);
		return UserResponse.builder()
				.firstName("Max")
				.surname("Mustermann")
				.username("mmustermann")
				.company("MyCompany")
				.leaveEntitlementPerYear(26)
				.workingDays("Saturday, Sunday")
				.position("Tester")
				.startDate(startDate.format(UserInterface.DATE_TIME_FORMATTER)).build();
	}
	
}