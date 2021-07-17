package de.davelee.personalman.gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import de.davelee.personalman.UserInterface;

/**
 * Class to display the employee screen for the PersonalMan program.
 * @author Dave Lee
 */
public class AddAbsenceScreen extends PersonalManBaseScreen {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JComboBox<String> employeeBox;
	private final JDateChooser startDateField;
	private final JDateChooser endDateField;
	private final JComboBox<String> reasonBox;
    
    private static final String FONT_FAMILY = "Arial";
    
    /**
     * Create a new add absence screen.
     * @param ui a <code>UserInterface</code> object with the current user interface.
     * @param date a <code>LocalDate</code> object with the date to add an absence for.
	 * @param company a <code>String</code> with the company that the user is associated with.
     * @param username a <code>String</code> containing the username to display absences for.
     */
    public AddAbsenceScreen ( final UserInterface ui, final LocalDate date, final String company, final String username ) {
        
        super(ui);
        
        //Create employee panel.
        JPanel absencePanel = new JPanel();
        absencePanel.setBackground(Color.WHITE);
        absencePanel.setLayout ( new GridLayout ( 4,2,5,5 ) );
        
        /*JLabel employeeLabel = new JLabel(userInterface.getUserInterfaceMessages().getAbsencesEmployeeMessage());
        employeeLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        absencePanel.add(employeeLabel);
        DefaultComboBoxModel<String> employeeModel = new DefaultComboBoxModel<String>();
        for ( String employeeName : userInterface.getUserNames(company) ) {
        	employeeModel.addElement(employeeName);
        }
        employeeBox = new JComboBox<String>(employeeModel);
        absencePanel.add(employeeBox);*/
        
        JLabel startDateLabel = new JLabel(userInterface.getUserInterfaceMessages().getEmployeesStartDateMessage());
        startDateLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        absencePanel.add(startDateLabel);
        startDateField = new JDateChooser(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        absencePanel.add(startDateField);
        
        JLabel endDateLabel = new JLabel(userInterface.getUserInterfaceMessages().getAbsencesEndDateMessage());
        endDateLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        absencePanel.add(endDateLabel);
        endDateField = new JDateChooser(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        absencePanel.add(endDateField);
        
        JLabel reasonLabel = new JLabel(userInterface.getUserInterfaceMessages().getAbsencesReasonMessage());
        reasonLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        absencePanel.add(reasonLabel);
        reasonBox = new JComboBox<>(processReasonsToStringList());
        absencePanel.add(reasonBox);
        
        //Add absencePanel to screenPanel.
        screenPanel.add(absencePanel, BorderLayout.CENTER);
        
        //Create button panel to display add absence and close buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton addAbsenceButton = new JButton(userInterface.getUserInterfaceMessages().getAbsencesAddButton());
        addAbsenceButton.addActionListener ( e -> {
            if ( reasonBox.getSelectedItem() != null ) {
                LocalDate startDate = startDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endDate = endDateField.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                boolean result = userInterface.addAbsence(company, username, startDate.format(UserInterface.DATE_TIME_FORMATTER),
                        endDate.format(UserInterface.DATE_TIME_FORMATTER), reasonBox.getSelectedItem().toString());
                displayErrorOrDispose(result);
            }
        });
        buttonPanel.add(addAbsenceButton);
        JButton closeButton = new JButton(userInterface.getUserInterfaceMessages().getAbsencesCloseButton());
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        //Add buttonPanel to screenPanel.
        screenPanel.add(buttonPanel, BorderLayout.SOUTH);
         
        container.add(screenPanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(650,200);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(650,200) );
        
    }
    
    /**
     * Method to control action after the user has clicked add absence and the operation has been processed.
     * @param result a <code>boolean</code> which is true iff the add operation was completed successfully.
     */
    public void displayErrorOrDispose ( final boolean result ) {
    	if ( result ) {
    		dispose();
    	} else {
    		JOptionPane.showMessageDialog(AddAbsenceScreen.this, userInterface.getUserInterfaceMessages().getAnnualLeaveErrorMessage(), userInterface.getUserInterfaceMessages().getAnnualLeaveErrorTitle(), JOptionPane.ERROR_MESSAGE);
    	}
    }

	/**
	 * Create the list of possible categories as a String array.
	 * @return a <code>Array</code> of <code>String</code> objects.
	 */
	public String[] processReasonsToStringList ( ) {
		List<String> myStringReasons = new ArrayList<>(6);
		myStringReasons.add(userInterface.getReasonNames().getIllness());
		myStringReasons.add(userInterface.getReasonNames().getHoliday());
		myStringReasons.add(userInterface.getReasonNames().getConference());
		myStringReasons.add(userInterface.getReasonNames().getDayInLieu());
		myStringReasons.add(userInterface.getReasonNames().getTrip());
		myStringReasons.add(userInterface.getReasonNames().getPublicHoliday());
		return myStringReasons.toArray(new String[0]);
	}
    
}

