package de.davelee.personalman.gui;

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
 * Test the employee screen to check that it can be built and displayed successfully.
 * @author Dave Lee
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Disabled
public class EmployeeScreenTest {
	
	@Autowired
	private UserInterface userInterface;

	/**
	 * Test case: test the employee screen with no employees selected.
	 * Expected result: screen is displayed successfully.
	 */
	@Test
	public void testWithNoEmployees ( ) {
		UserInterface userInterfaceMock = new UserInterfaceMock();
		userInterfaceMock.setUserInterfaceMessages(userInterface.getUserInterfaceMessages());
		userInterfaceMock.determineLocale(userInterface.getLocaleLanguage());
		userInterfaceMock.setEmployeeService(new EmployeeServiceMock());
		EmployeeScreen employeeScreen = new EmployeeScreenMock(userInterfaceMock, "MyCompany", "testadmin");
		assertNotNull(employeeScreen);
		employeeScreen.removeEmployee();
		employeeScreen.changeEmployeeValueInList();
	}

	/**
	 * Test case: test the employee screen with employees selected.
	 * Expected result: screen is displayed successfully.
	 */
	@Test
	public void testWithEmployees ( ) {
		UserInterface userInterfaceMock = new UserInterfaceMock();
		userInterfaceMock.setUserInterfaceMessages(userInterface.getUserInterfaceMessages());
		userInterfaceMock.determineLocale(userInterface.getLocaleLanguage());
		userInterfaceMock.setEmployeeService(new EmployeeServiceMock());
		EmployeeScreenMock employeeScreen = new EmployeeScreenMock(userInterfaceMock, "MyCompany", "testadmin");
		assertNotNull(employeeScreen);
		employeeScreen.selectList();
		employeeScreen.removeEmployee();
		employeeScreen.changeEmployeeValueInList();
		employeeScreen.getWorkingDays();
	}
	
	
}
