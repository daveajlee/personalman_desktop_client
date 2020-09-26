package de.davelee.personalman.gui;

import java.time.LocalDate;
import java.time.Month;

import de.davelee.personalman.api.UserResponse;
import de.davelee.personalman.service.AbsenceServiceMock;
import de.davelee.personalman.service.EmployeeServiceMock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.UserInterfaceMock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the functionality of the ClickDateMouseListener using the ClickDateMouseListenerMock object.
 * @author Dave Lee
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Disabled
public class ClickDateMouseListenerTest {

	@Autowired
	private UserInterfaceMessages userInterfaceMessages;
	
	@Autowired
	private ReasonNames reasonNames;

	/**
	 * Test case: check that the click mouse listener works when there are no absences.
	 * Expected result: null since no absences can be clicked on if they do not exist.
	 */
	@Test
	public void testWithoutAbsences ( ) {
		UserInterface userInterface = new UserInterfaceMock();
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		userInterface.determineLocale("English");
		userInterface.setAbsenceService(new AbsenceServiceMock());
		userInterface.setReasonNames(reasonNames);
		ClickDateMouseListener clickDateMouseListener = new ClickDateMouseListenerMock(userInterface, LocalDate.of(2015, 2, 27), "MyCompany", "testuser", new MonthPanel(Month.APRIL, 2015, "MyCompany", "testuser", userInterface));
		assertNotNull(clickDateMouseListener);
		clickDateMouseListener.mouseClicked(null);
	}

	/**
	 * Test case: check that the click mouse listener works when there are absences which can be clicked on.
	 * Expected result: the clicked on absence is removed successfully.
	 */
	@Test
	public void testAbsences ( ) {
		UserInterface userInterface = new UserInterfaceMock();
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		userInterface.determineLocale("English");
		userInterface.setAbsenceService(new AbsenceServiceMock());
		userInterface.setEmployeeService(new EmployeeServiceMock());
		userInterface.setReasonNames(reasonNames);
		userInterface.removeEmployee("MyCompany", "mmustermann");
		userInterface.addEmployee("Max", "Mustermann", "mmustermann", "MyCompany", 4, "Saturday,Sunday", "Tester", "01-04-2015");
		userInterface.deleteAbsences("MyCompany", "mmustermann", "03-04-2015", "06-04-2015");
		userInterface.addAbsence("MyCompany", "mmustermann", "03-04-2015", "06-04-2015", "FEDERAL_HOLIDAY");
		ClickDateMouseListener clickDateMouseListener = new ClickDateMouseListenerMock(userInterface, LocalDate.of(2015, 4, 3), "MyCompany", "testuser", new MonthPanel(Month.APRIL, 2015, "MyCompany", "testuser", userInterface));
		clickDateMouseListener.mouseClicked(null);
		UserResponse userResponse = userInterface.getEmployeeByUserName("MyCompany", "mmustermann");
		assertNotNull(userResponse);
		userInterface.deleteAbsences("MyCompany", "mmustermann", "03-04-2015", "06-04-2015");
		userInterface.removeEmployee(userResponse.getCompany(), userResponse.getUsername());
	}

}
