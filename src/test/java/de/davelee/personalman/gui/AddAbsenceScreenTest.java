package de.davelee.personalman.gui;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import de.davelee.personalman.service.EmployeeServiceMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.UserInterfaceMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
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
		AddAbsenceScreen screen = new AddAbsenceScreen(userInterfaceMock, LocalDate.of(2015, 2, 27), "MyCompany");
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
		AddAbsenceScreen screen = new AddAbsenceScreen(userInterfaceMock, LocalDate.of(2015, 2, 27), "MyCompany");
		assertEquals(screen.processReasonsToStringList().length, 6);
	}

}
