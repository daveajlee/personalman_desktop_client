package de.davelee.personalman.gui;

import de.davelee.personalman.UserInterface;

/**
 * This class mocks some of the methods of EmployeeScreen to allow testing of the functionality through JUnit.
 * @author Dave Lee
 */
public class EmployeeScreenMock extends EmployeeScreen {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8867366137264115227L;

	/**
	 * Create a new employee screen mock.
	 * @param ui a <code>UserInterface</code> object with the current user interface.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the username of the currently logged in admin user.
	 *
	 */
	public EmployeeScreenMock ( final UserInterface ui, final String company, final String username ) {
		super(ui, company, username);
	}

	/**
	 * Additional helper method to select the first element in the list.
	 */
	public void selectList ( ) {
		employeeList.setSelectedIndex(0);
	}

	/**
	 * Additional helper method to unselect the first element in the list.
	 */
	public void unselectList ( ) {
		employeeList.setSelectedIndex(-1);
	}

}
