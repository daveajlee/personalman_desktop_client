package de.davelee.personalman;

import de.davelee.personalman.api.AbsenceResponse;
import de.davelee.personalman.api.AbsencesResponse;
import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.api.UsersResponse;
import de.davelee.personalman.service.AbsenceService;
import de.davelee.personalman.service.AbsenceServiceMock;
import de.davelee.personalman.service.EmployeeService;
import de.davelee.personalman.service.EmployeeServiceMock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test functionality of the UserInterface class.
 * @author Dave Lee
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class UserInterfaceTest {
	
	private static final String EMPLOYEE_USERNAME = "mmustermann";
	private static final String REASON_DAY_IN_LIEU = "Day in Lieu";
	private static final String REASON_HOLIDAY = "Holiday";
	
	@Autowired
	private UserInterface userInterface;

	/**
	 * Test case: build the user interface using mock services.
	 * Expected result: the user interface is built successfully.
	 */
	@Test
	public void testUserInterface ( ) {
		EmployeeService employeeService = new EmployeeServiceMock();
		userInterface.setEmployeeService(employeeService);
		AbsenceService absenceService = new AbsenceServiceMock();
		userInterface.setAbsenceService(absenceService);
		UsersResponse employees = employeeService.findByCompany("MyCompany", "token");
		if ( employees != null ) {
			for (UserResponse employee : employees.getUserResponses()) {
				employeeService.delete("MyCompany", employee.getUsername(), "token");
			}
		}
		assertEquals(userInterface.getUserNames("MyCompany").length, 2);
		userInterface.addEmployee("Max", "Mustermann", EMPLOYEE_USERNAME, "MyCompany", 26, "Saturday, Sunday", "Tester", "28-02-2015");
		AbsencesResponse absences = userInterface.getAbsenceService().findByNameAndYear("MyCompany", EMPLOYEE_USERNAME, 2015, "token");
		if ( !absences.getAbsenceResponseList().isEmpty() ) {
			for ( AbsenceResponse absence: absences.getAbsenceResponseList() ) {
				userInterface.getAbsenceService().delete(absence.getCompany(), absence.getUsername(), absence.getStartDate(), absence.getEndDate(), "token");
			}
		}
		assertEquals(userInterface.getUserNames("MyCompany").length, 2);
		assertEquals(userInterface.getUserNames("MyCompany")[0], EMPLOYEE_USERNAME + " - Max Mustermann");
		assertEquals(userInterface.getStatistics("MyCompany", EMPLOYEE_USERNAME, 2015), "Illness: 0 days\nHoliday: 0 days (Remaining: 4 days)\nTrip: 0 days\nConference: 0 days\nDay in Lieu: 0 days (Remaining: 0 days)\nFederal Holiday: 4 days\n");
		userInterface.determineLocale("English");
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "03-04-2016", "06-04-2016", "Federal Holiday");
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "05-04-2016", "05-04-2016", "Day in Lieu Request");
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "11-04-2016", "11-04-2016", REASON_DAY_IN_LIEU);
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "29-04-2016", "30-04-2016", "Conference");
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "03-05-2016", "06-05-2016", "Trip");
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "11-05-2016", "11-05-2016", REASON_HOLIDAY);
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "14-05-2016", "14-05-2016", "Illness");
		assertEquals(userInterface.getStatistics("MyCompany", EMPLOYEE_USERNAME, 2016), "Illness: 1 days\nHoliday: 1 days (Remaining: 3 days)\nTrip: 4 days\nConference: 2 days\nDay in Lieu: 1 days (Remaining: 0 days)\nFederal Holiday: 4 days\n");
		//Test generateDaysInLieu.
		userInterface.addAbsence("MyCompany", EMPLOYEE_USERNAME, "05-06-2017", "08-06-2017", "Conference");
		assertEquals(userInterface.getStatistics("MyCompany", EMPLOYEE_USERNAME, 2017), "Illness: 0 days\nHoliday: 0 days (Remaining: 4 days)\nTrip: 0 days\nConference: 4 days\nDay in Lieu: 0 days (Remaining: 0 days)\nFederal Holiday: 0 days\n");
	}
	
}
