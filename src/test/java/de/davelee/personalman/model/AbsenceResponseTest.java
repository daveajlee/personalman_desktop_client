package de.davelee.personalman.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.davelee.personalman.api.AbsenceResponse;

/**
 * Class to test the Absence Response object.
 * @author Dave Lee
 */
public class AbsenceResponseTest {
	
	private static final String EMPLOYEE_USER_NAME = "mmustermann";

	/**
	 * Test case: Test the constructor by creating a new absence.
	 * Expected result: the absence response object is created with all fields set successfully.
	 */
	@Test
	public void testConstructor() {
		AbsenceResponse absence = new AbsenceResponse("MyCompany", EMPLOYEE_USER_NAME, "03-04-2015", "06-04-2015", "Federal Holiday");
		assertEquals(absence.getUsername(), EMPLOYEE_USER_NAME);
		assertEquals(absence.getStartDate(), "03-04-2015");
		assertEquals(absence.getEndDate(), "06-04-2015");
		assertEquals(absence.getCategory(), "Federal Holiday");
		assertEquals("AbsenceResponse(company=MyCompany, username=mmustermann, startDate=03-04-2015, endDate=06-04-2015, category=Federal Holiday)", absence.toString());
	}

	/**
	 * Test case: Test the setters to create a new absence and check they work using the getter methods.
	 * Expected result: the absence response object is created with all fields set successfully.
	 */
	@Test
	public void testSetAndGet() {
		AbsenceResponse absence = new AbsenceResponse("MyCompany 2", EMPLOYEE_USER_NAME, "03-04-2016", "06-04-2016", "Federal Holiday");
		absence.setUsername(EMPLOYEE_USER_NAME);
		absence.setStartDate("03-04-2015");
		absence.setEndDate("06-04-2015");
		absence.setCategory("Federal Holiday");
		absence.setCompany("MyCompany");
		assertEquals("AbsenceResponse(company=MyCompany, username=mmustermann, startDate=03-04-2015, endDate=06-04-2015, category=Federal Holiday)", absence.toString());
	}

}
