package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.UserInterfaceMock;
import de.davelee.personalman.service.AbsenceServiceMock;
import de.davelee.personalman.service.EmployeeServiceMock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Disabled
/**
 * Test the absence screen to ensure that the GUI functions work as expected.
 * @author Dave Lee
 */
public class AbsenceScreenTest {

	@Autowired
	private UserInterfaceMessages userInterfaceMessages;

	@Autowired
	private ReasonNames reasonNames;
	
	@Test
	/**
	 * Test Case: build and display the absence screen.
	 * Expected result: the absence screen can be built successfully without any errors.
	 */
	public void testAbsenceScreen() {
		UserInterface userInterface = new UserInterfaceMock();
		userInterface.setAbsenceService(new AbsenceServiceMock());
		userInterface.setEmployeeService(new EmployeeServiceMock());
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		userInterface.determineLocale("English");
		AbsenceScreen screen = new AbsenceScreenMock(userInterface, LocalDate.of(2015, 2, 28), "MyCompany" );
		LocalDate date = screen.processPreviousMonthButton();
		assertEquals(date.getMonthValue(), 1);
		LocalDate date2 = screen.processNextMonthButton();
		assertEquals(date2.getMonthValue(), 2);
		screen.processViewStatisticsButton();
		UserInterface userInterfaceMock = new UserInterfaceMock();
		userInterfaceMock.determineLocale("English");
		userInterfaceMock.setUserInterfaceMessages(userInterface.getUserInterfaceMessages());
		userInterfaceMock.setAbsenceService(new AbsenceServiceMock());
		userInterfaceMock.setReasonNames(reasonNames);
		userInterfaceMock.setEmployeeService(new EmployeeServiceMock());
		AbsenceScreenMock screen2 = new AbsenceScreenMock(userInterfaceMock, LocalDate.of(2015, 2, 28), "MyCompany" );
		screen2.setSelectedEmployee();
		screen2.processViewStatisticsButton();
	}
	
}
