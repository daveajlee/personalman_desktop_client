package de.davelee.personalman.gui;

import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;

import de.davelee.personalman.UserInterface;

/**
 * Class to display the employee screen for the PersonalMan program.
 * @author Dave Lee
 */
public class AbsenceScreen extends PersonalManBaseScreen {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final MonthPanel monthPanel;
    
    private LocalDate date;
    private final String company;
    private final String username;
    
    /**
     * Create a new absence screen.
     * @param ui a <code>UserInterface</code> object with the current user interface.
     * @param date a <code>LocalDate</code> object with the date to display absences for.
     * @param company a <code>String</code> with the company that the user is associated with.
     * @param username a <code>String</code> containing the username to display absences for.
     */
    public AbsenceScreen ( final UserInterface ui, final LocalDate date, final String company, final String username ) {
        
        super(ui);
        this.date = date;
        this.company = company;
        this.username = username;

        //Create label for heading.
        JLabel headingLabel = new JLabel("Absences for user: " + username, JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        screenPanel.setBackground(Color.WHITE);
        screenPanel.add(headingLabel, BorderLayout.NORTH);
        
        //Create month Panel.
        JPanel monthJPanel = new JPanel();
        monthJPanel.setBackground(Color.WHITE);
        monthJPanel.setLayout( new BoxLayout ( monthJPanel, BoxLayout.PAGE_AXIS ) );
        monthPanel = new MonthPanel(date.getMonth(), date.getYear(), company, username, userInterface);
        monthJPanel.setBackground(Color.WHITE);
        monthJPanel.add(monthPanel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton previousMonthButton = new JButton("<<");
        previousMonthButton.addActionListener(e -> processPreviousMonthButton());
        JButton nextMonthButton = new JButton(">>");
        nextMonthButton.addActionListener(e -> processNextMonthButton());
        buttonPanel.add(previousMonthButton);
        buttonPanel.add(nextMonthButton);
        monthJPanel.add(buttonPanel);
        screenPanel.add(monthJPanel);

        JPanel bottomButtonPanel = new JPanel(new GridLayout(2,2,5,5));
        bottomButtonPanel.setBackground(Color.WHITE);
        JButton viewStatisticsButton = new JButton(userInterface.getUserInterfaceMessages().getAbsencesStatsButton());
        viewStatisticsButton.addActionListener( e -> processViewStatisticsButton());
        bottomButtonPanel.add(viewStatisticsButton);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener ( e -> {
            new LoginScreen(userInterface);
            dispose();
        });
        bottomButtonPanel.add(logoutButton);
        //employeePanel.add(bottomButtonPanel);
        screenPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        
        container.add(screenPanel, BorderLayout.CENTER);
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(650,450);
        this.setLocation ( (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));
        
        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(650,450) );
        
    }
    
    /**
     * Perform action for previous month button - deduct one month and refresh panel.
     * @return a <code>LocalDate</code> with the new date (including the new month).
     */
    public LocalDate processPreviousMonthButton ( ) {
		date = date.minusMonths(1);
		monthPanel.refreshNewMonth(date.getMonth(), date.getYear());
		return date;
    }
    
    /**
     * Perform action for next month button - add one month and refresh panel.
     * @return a <code>LocalDate</code> with the new date (including the new month).
     */
    public LocalDate processNextMonthButton ( ) {
		date = date.plusMonths(1);
		monthPanel.refreshNewMonth(date.getMonth(), date.getYear());
		return date;
    }
    
    /**
     * Display a JOptionPane Dialog with the statistics information. This is implemented in a separate
     * method to allow overwrite by JUnit tests.
     */
    public void processViewStatisticsButton ( ) {
    	JOptionPane.showMessageDialog(AbsenceScreen.this, 
				userInterface.getStatistics(company, username, monthPanel.getYear()),
				userInterface.getUserInterfaceMessages().getStatisticsTitleMessage() + username + " - " + monthPanel.getYear()
				, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(UserInterface.class.getResource("/images/personalmanlogo-icon.png")) );
    }
    
}

