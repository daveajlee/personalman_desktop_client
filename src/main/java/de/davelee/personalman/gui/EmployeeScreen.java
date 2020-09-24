package de.davelee.personalman.gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.UserResponse;

/**
 * Class to display the employee screen for the PersonalMan program.
 * @author Dave Lee
 */
public class EmployeeScreen extends PersonalManBaseScreen {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JList<String> employeeList;
	private DefaultListModel<String> employeeModel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userNameField;
    private JTextField companyField;
	private JTextField positionField;
	private JDateChooser startDateField;
	private JSpinner annualLeaveField;
	private DefaultListModel<String> workingDaysModel;
	private JList<String> workingDaysList;
        
    private JButton addEmployeeButton;
    private JButton removeEmployeeButton;
    private JButton clearButton;

    private String company;
    private String username;
    
    private static final String FONT_FAMILY = "Arial";
    
    /**
     * Create a new employee screen.
     * @param ui a <code>UserInterface</code> object with the current user interface.
     * @param company a <code>String</code> with the company that the user is associated with.
     * @param username a <code>String</code> with the username of the currently logged in admin user.
     */
    public EmployeeScreen ( final UserInterface ui, final String company, final String username ) {
        
        super(ui);

        //Set the company and username to be used.
        this.company = company;
        this.username = username;

        //Create employee panel.
        JPanel employeePanel = new JPanel();
        employeePanel.setBackground(Color.WHITE);
        employeePanel.setLayout ( new BoxLayout ( employeePanel, BoxLayout.PAGE_AXIS ) );

        try {
            JLabel employeeLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesTitleMessage(), SwingConstants.CENTER);
            employeeLabel.setFont(new Font(FONT_FAMILY, Font.BOLD + Font.ITALIC, 20));
            employeePanel.add(employeeLabel);
            JPanel listPanel = new JPanel();
            listPanel.setBackground(Color.WHITE);
            employeeModel = new DefaultListModel<String>();
            for (String userName : userInterface.getUserNames(company)) {
                employeeModel.addElement(userName);
            }
            employeeList = new JList<String>(employeeModel);
            JScrollPane employeePane = new JScrollPane(employeeList,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            employeeList.setVisibleRowCount(20);
            employeeList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    changeEmployeeValueInList();
                }
            });

            employeePanel.add(employeePane);
        } catch ( Exception connectException ) {
            employeePanel.add(new JLabel("Sorry! No server = no data!"));
        }

        screenPanel.add(employeePanel, BorderLayout.WEST);
        
        //Create a new editing panel.
        JPanel editingPanel = new JPanel();
        editingPanel.setBackground(Color.WHITE);
        editingPanel.setLayout ( new BoxLayout ( editingPanel, BoxLayout.PAGE_AXIS ) );
        
        //Create a new name editing panel.
        JPanel nameEditingPanel = new JPanel();
        nameEditingPanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesNameMessage());
        nameLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        firstNameField = new JTextField("");
        firstNameField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        firstNameField.setColumns(10);
        lastNameField = new JTextField("");
        lastNameField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        lastNameField.setColumns(10);
        nameEditingPanel.add(nameLabel);
        nameEditingPanel.add(firstNameField);
        nameEditingPanel.add(lastNameField);
        editingPanel.add(nameEditingPanel);
        
        //Create a new userName editing panel.
        JPanel userNameEditingPanel = new JPanel();
        userNameEditingPanel.setBackground(Color.WHITE);
        JLabel userNameLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesUserNameMessage());
        userNameLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        userNameField = new JTextField("");
        userNameField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        userNameField.setColumns(10);
        userNameEditingPanel.add(userNameLabel);
        userNameEditingPanel.add(userNameField);
        editingPanel.add(userNameEditingPanel);

        //Create a new company editing panel.
        JPanel companyEditingPanel = new JPanel();
        companyEditingPanel.setBackground(Color.WHITE);
        JLabel companyLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesCompanyMessage());
        companyLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        companyField = new JTextField("");
        companyField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        companyField.setColumns(10);
        companyField.setText(company);
        companyField.setEditable(false);
        companyEditingPanel.add(companyLabel);
        companyEditingPanel.add(companyField);
        editingPanel.add(companyEditingPanel);
        
        //Create a new position editing panel.
        JPanel positionEditingPanel = new JPanel();
        positionEditingPanel.setBackground(Color.WHITE);
        JLabel positionLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesPositionMessage());
        positionLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        positionField = new JTextField("");
        positionField.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        positionField.setColumns(15);
        positionEditingPanel.add(positionLabel);
        positionEditingPanel.add(positionField);
        editingPanel.add(positionEditingPanel);
        
        //Create a new start date editing panel.
        JPanel startDateEditingPanel = new JPanel();
        startDateEditingPanel.setBackground(Color.WHITE);
        JLabel startDateLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesStartDateMessage());
        startDateLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        startDateField = new JDateChooser();
        startDateEditingPanel.add(startDateLabel);
        startDateEditingPanel.add(startDateField);
        editingPanel.add(startDateEditingPanel);
        
        //Create a new annual leave editing panel.
        JPanel leaveEditingPanel = new JPanel();
        leaveEditingPanel.setBackground(Color.WHITE);
        JLabel annualLeaveLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesAnnualLeaveMessage());
        annualLeaveLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        annualLeaveField = new JSpinner(new SpinnerNumberModel(25,1,100,1));
        leaveEditingPanel.add(annualLeaveLabel);
        leaveEditingPanel.add(annualLeaveField);
        JLabel daysLabel = new JLabel(userInterface.getUserInterfaceMessages().getDaysMessage());
        leaveEditingPanel.add(daysLabel);
        editingPanel.add(leaveEditingPanel);
        
        //Create a new working days editing panel.
        JPanel workingDaysEditingPanel = new JPanel();
        workingDaysEditingPanel.setBackground(Color.WHITE);
        JLabel workingDaysLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesWorkingDaysMessage());
        workingDaysLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        
        workingDaysModel = new DefaultListModel<String>();
        workingDaysModel.addElement(DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        workingDaysModel.addElement(DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()));
        
        workingDaysList = new JList<String>(workingDaysModel);
        workingDaysList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        workingDaysList.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
        workingDaysEditingPanel.add(workingDaysLabel);
        JScrollPane daysPane = new JScrollPane(workingDaysList,
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        workingDaysList.setVisibleRowCount(4);
        workingDaysEditingPanel.add(daysPane);
        editingPanel.add(workingDaysEditingPanel);
        
        //Add editingPanel to screenPanel.
        screenPanel.add(editingPanel, BorderLayout.EAST);
        
        //Create button panel to display add employee and back to welcome screen buttons.
        JPanel buttonPanel = new JPanel(new GridLayout(2,4,5,5));
        buttonPanel.setBackground(Color.WHITE);
        addEmployeeButton = new JButton(userInterface.getUserInterfaceMessages().getEmployeesAddButton());
        addEmployeeButton.addActionListener ( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
            	userInterface.addEmployee(firstNameField.getText(), 
            			lastNameField.getText(), userNameField.getText(), companyField.getText(), (Integer) annualLeaveField.getValue(),
            			getWorkingDays(), positionField.getText(),
            			startDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(UserInterface.DATE_TIME_FORMATTER));
            	employeeModel.addElement(userNameField.getText());
            	firstNameField.setText("");
            	lastNameField.setText("");
            	userNameField.setText("");
            	annualLeaveField.setValue(25);
            	workingDaysList.clearSelection();
            	positionField.setText("");
            	startDateField.setDate(new Date());
            }
        });
        buttonPanel.add(addEmployeeButton);
        removeEmployeeButton = new JButton(userInterface.getUserInterfaceMessages().getEmployeesRemoveButton());
        removeEmployeeButton.setEnabled(false);
        removeEmployeeButton.addActionListener(new ActionListener() {
        	public void actionPerformed ( ActionEvent e ) {
        		removeEmployee();
        	}
        });
        buttonPanel.add(removeEmployeeButton);
        clearButton = new JButton(userInterface.getUserInterfaceMessages().getEmployeesResetButton());
        clearButton.setEnabled(false);
        clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addEmployeeButton.setEnabled(true);
        		clearButton.setEnabled(false);
        		removeEmployeeButton.setEnabled(false);
        		employeeList.clearSelection();
        		firstNameField.setText("");
        		lastNameField.setText("");
        		userNameField.setText("");
        		annualLeaveField.setValue(25);
        		workingDaysList.clearSelection();
        		positionField.setText("");
        		startDateField.setDate(new Date());       		
        	}
        });
        buttonPanel.add(clearButton);
        JButton welcomeScreenButton = new JButton(userInterface.getUserInterfaceMessages().getEmployeesWelcomeButton());
        welcomeScreenButton.addActionListener ( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                new WelcomeScreen(userInterface, company, username);
                dispose();
            }
        });
        buttonPanel.add(welcomeScreenButton);
        //Blank or spacer.
        buttonPanel.add(new JPanel());
        //Add buttonPanel to screenPanel.
        screenPanel.add(buttonPanel, BorderLayout.SOUTH);
         
        container.add(screenPanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(650,500);
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(650,500) );
        
    }
    
    /**
     * Get the selected working days for this employee from the GUI list as a comma-separated String e.g. Saturday,Sunday.
     * @return a <code>String</code> with the working days as a comma-separated String e.g. Saturday,Sunday.
     */
    public String getWorkingDays ( ) {
    	String workingDay = "";
    	List<String> myWorkingDays = workingDaysList.getSelectedValuesList();
    	for ( String myWorkingDay : myWorkingDays ) {
    		workingDay += myWorkingDay + ",";
    	}
    	return workingDay;
    }
    
    /**
     * Set the working days GUI list according to the supplied String contents in comma-separated form e.g. Saturday,Sunday.
     * @param workingDay a <code>String</code> in comma-separated form - the list must contain valid day names in the local locale!
     */
    public void setWorkingDays ( final String workingDay ) {
    	String[] workingDays = workingDay.split(",");
    	int[] indices = new int[workingDays.length];
    	int count = 0;
    	for ( String myFreeDay : workingDays ) {
    		indices[count] = workingDaysModel.indexOf(myFreeDay);
    		count++;
    	}
    	workingDaysList.setSelectedIndices(indices);
    }
    
    /**
     * Update the GUI components based on a new selected employee from the employee list.
     */
    public void changeEmployeeValueInList ( ) {
    	if ( employeeList.getSelectedIndex() != -1 ) {
			UserResponse userResponse = userInterface.getEmployeeByUserName(company, employeeList.getSelectedValue());
			firstNameField.setText(userResponse.getFirstName());
			lastNameField.setText(userResponse.getSurname());
			userNameField.setText(userResponse.getUsername());
			annualLeaveField.setValue(userResponse.getLeaveEntitlementPerYear());
			companyField.setText(userResponse.getCompany());
			setWorkingDays(userResponse.getWorkingDays());
			positionField.setText(userResponse.getPosition());
			startDateField.setDate(Date.from(LocalDate.parse(userResponse.getStartDate(), UserInterface.DATE_TIME_FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			addEmployeeButton.setEnabled(false);
			removeEmployeeButton.setEnabled(true);
			clearButton.setEnabled(true);
		}
    }
    
    /**
     * Reset the GUI components and remove the selected employee from the employee list.
     */
    public void removeEmployee ( ) {
    	if ( employeeList.getSelectedIndex() != -1 ) {
			UserResponse employee = userInterface.getEmployeeByUserName(company, employeeList.getSelectedValue());
			employeeModel.remove(employeeList.getSelectedIndex());
			userInterface.removeEmployee(company, employee.getUsername());
        	firstNameField.setText("");
        	lastNameField.setText("");
        	userNameField.setText("");
        	companyField.setText("");
        	annualLeaveField.setValue(25);
        	workingDaysList.clearSelection();
        	positionField.setText("");
        	startDateField.setDate(new Date());
		}
    }
    
}

