package de.davelee.personalman.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import de.davelee.personalman.service.EmployeeServiceMock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.UserInterfaceMock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Disabled
/**
 * Test the add absence screen to ensure that the GUI functions work as expected.
 * @author Dave Lee
 */
public class AddAbsenceScreenTest {
	
	@Autowired
	private UserInterface userInterface;
	
	@Test
	/**
	 * Test Case: build the add absence screen with some employee names.
	 * Expected result: the absence can be added successfully.
	 */
	public void testWithEmployeeNames ( ) {
		UserInterface userInterfaceMock = new UserInterfaceMock();
		userInterfaceMock.determineLocale(userInterface.getLocaleLanguage());
		userInterfaceMock.setUserInterfaceMessages(userInterface.getUserInterfaceMessages());
		userInterfaceMock.setReasonNames(userInterface.getReasonNames());
		userInterfaceMock.setEmployeeService(new EmployeeServiceMock());
		AddAbsenceScreen screen = new AddAbsenceScreen(userInterfaceMock, LocalDate.of(2015, 2, 27), "MyCompany", "testuser");
		assertNotNull(screen);
		screen.displayErrorOrDispose(true);
	}
	
	@Test
	/**
	 * Test case: check that the correct reason options for absences are displayed.
	 * Expected result: the reason options list has the correct size (6).
	 */
	public void testReasonCompare ( ) {
		UserInterface userInterfaceMock = new UserInterfaceMock();
		userInterfaceMock.setUserInterfaceMessages(userInterface.getUserInterfaceMessages());
		userInterfaceMock.determineLocale(userInterface.getLocaleLanguage());
		userInterfaceMock.setReasonNames(userInterface.getReasonNames());
		userInterfaceMock.setEmployeeService(new EmployeeServiceMock());
		AddAbsenceScreen screen = new AddAbsenceScreen(userInterfaceMock, LocalDate.of(2015, 2, 27), "MyCompany", "testuser");
		assertEquals(screen.processReasonsToStringList().length, 6);
	}

}
