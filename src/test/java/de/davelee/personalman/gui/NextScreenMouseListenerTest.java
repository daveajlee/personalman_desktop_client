package de.davelee.personalman.gui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.davelee.personalman.UserInterface;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the functionality of the NextScreenMouseListener using some features of the mock object.
 * @author Dave Lee
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Disabled
public class NextScreenMouseListenerTest {
	
	@Autowired
	private UserInterfaceMessages userInterfaceMessages;

	/**
	 * Test case: test that the NextScreenMouseListener can be created with type Absence Screen.
	 * Expected result: the Listener is built successfully.
	 */
	@Test
	public void testAbsenceScreenType() {
		UserInterface userInterface = new UserInterface();
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		NextScreenMouseListener nextScreenMouseListener = new NextScreenMouseListenerMock(ScreenType.ABSENCE_SCREEN, userInterface, new AdminScreen(userInterface, "MyCompany", "testadmin"), "MyCompany", "testadmin");
		assertNotNull(nextScreenMouseListener);
		nextScreenMouseListener.mouseClicked(null);
	}

	/**
	 * Test case: test that the NextScreenMouseListener can be created with type Employee Screen.
	 * Expected result: the Listener is built successfully.
	 */
	@Test
	public void testEmployeeScreenType() {
		UserInterface userInterface = new UserInterface();
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		NextScreenMouseListener nextScreenMouseListener = new NextScreenMouseListenerMock(ScreenType.EMPLOYEE_SCREEN, userInterface, new AdminScreen(userInterface, "MyCompany", "testadmin"), "MyCompany", "testadmin");
		assertNotNull(nextScreenMouseListener);
		nextScreenMouseListener.mouseClicked(null);
	}

	/**
	 * Test case: create the NextScreenMouseListener with type null.
	 * Expected result: Listener is built but has no functionality.
	 */
	@Test
	public void testNullScreenType() {
		UserInterface userInterface = new UserInterface();
		userInterface.setUserInterfaceMessages(userInterfaceMessages);
		NextScreenMouseListener nextScreenMouseListener = new NextScreenMouseListenerMock(null, userInterface, new AdminScreen(userInterface, "MyCompany", "testadmin"), "MyCompany", "testadmin");
		assertNotNull(nextScreenMouseListener);
		nextScreenMouseListener.mouseClicked(null);
	}

}
