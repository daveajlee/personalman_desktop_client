package de.davelee.personalman.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.davelee.personalman.UserInterface;

/**
 * Class to display the view month-by-month in PersonalMan.
 * See http://stackoverflow.com/questions/17232038/calendar-display-using-java-swing
 * @author Dave Lee (with examples from the above mentioned reference)
 */

public class MonthPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5114040713758979186L;
	protected Month month;
	protected int year;
	private final String company;
	private final String username;
	private final UserInterface userInterface;
	
	/**
	 * Create a new month panel.
	 * @param month a <code>Month</code> with the month to be displayed.
	 * @param year a <code>int</code> with the year to be displayed.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> containing the username to display absences for.
	 * @param userInterface a <code>UserInterface</code> object representing the current user interface.
	 */
	public MonthPanel(final Month month, final int year, final String company, final String username, final UserInterface userInterface) {
		this.userInterface = userInterface;
		this.company = company;
		this.username = username;
		this.add(createMonthComponent(month, year));
	}
	
	/**
	 * Create the month component to display to the user.
	 * @param month a <code>Month</code> with the month to be displayed.
	 * @param year a <code>int</code> with the year to be displayed.
	 * @return a <code>JPanel</code> containing the month component with all elements ready to display to the user.
	 */
	protected JPanel createMonthComponent(final Month month, final int year) {
		this.month = month;
		this.year = year;
		JPanel monthPanel = new JPanel(true);
		monthPanel.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
		monthPanel.setLayout(new BorderLayout());
		monthPanel.setBackground(Color.WHITE);
		monthPanel.setForeground(Color.BLACK);
		monthPanel.add(createTitleComponent(), BorderLayout.NORTH);
		monthPanel.add(createCalendarComponent(), BorderLayout.CENTER);
		
		return monthPanel;
	}
	
	/**
	 * Create the title part of the month component.
	 * @return a <code>JPanel</code> containing all elements to display in the title part of the month component.
	 */
	protected JPanel createTitleComponent() {
		JPanel titlePanel = new JPanel(true);
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setForeground(Color.BLACK);
		
		JLabel label = new JLabel(month.getDisplayName(TextStyle.FULL, userInterface.getMyLocale()) + " " + year );
		
		titlePanel.add(label);
		
		return titlePanel;
	}
	
	/**
	 * Create the calendar part of the month component.
	 * @return a <code>JPanel</code> containing all elements to display in the calendar part of the month component.
	 */
	protected JPanel createCalendarComponent() {
		JPanel dayPanel = new JPanel(true);
		dayPanel.setLayout(new GridLayout(0, 7));
		
		LocalDate today = LocalDate.now();
		
		LocalDate calendar = LocalDate.of(year, month, 1);
		
		int count = 0;
		int limit = 42;
		
		for ( int i = 0; i < 7; i++ ) {
			dayPanel.add(createDayPanel());
		}
		
		while (calendar.getMonth()!=getNextMonth(month) ) {
			
			JPanel dPanel = new JPanel(true);
			dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			final JLabel dayLabel = new JLabel();
			
			if (calendar.getMonth() == month && calendar.getYear() == year) {
				dayLabel.setText(Integer.toString(calendar.getDayOfMonth()));
				dayLabel.addMouseListener(new ClickDateMouseListener(userInterface, LocalDate.of(year, month, Integer.parseInt(dayLabel.getText())), company, username,this));
				if ( calendar.isEqual(today) ) {
					dPanel.setBackground(Color.ORANGE);
				} else {
					dPanel.setBackground(Color.WHITE);
				}
			} else {
				dayLabel.setText(" ");
				dPanel.setBackground(Color.WHITE);
			}
			dPanel.add(dayLabel);
			dayPanel.add(dPanel);
			calendar = calendar.plusDays(1);
			count++;
		}
		
		for ( int i = count; i < limit; i++ ) {
			dayPanel.add(createDayPanel());
		}
		
		return dayPanel;
		
	}

	/**
	 * Private helper to return a panel per day.
	 * @return a <code>JPanel</code> object.
	 */
	private JPanel createDayPanel ( ) {
		JPanel dPanel = new JPanel(true);
		dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel dayLabel = new JLabel();
		dayLabel.setText("");
		dPanel.setBackground(Color.WHITE);
		dPanel.add(dayLabel);
		return dPanel;
	}
	
	/**
	 * Return the next month after the supplied month e.g. return December when the supplied month is November.
	 * @param month a <code>Month</code> object with the current month.
	 * @return a <code>Month</code> object representing the next month after the current month.
	 */
	public Month getNextMonth ( final Month month ) {
		if ( month.getValue() == 12 ) {
			return Month.JANUARY;
		} else {
			return month.plus(1);
		}
	}
	
	/**
	 * Return the year currently displayed to the user.
	 * @return a <code>int</code> representing the current year.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Change the month component view to the supplied month and year.
	 * @param month a <code>Month</code> object representing the new month.
	 * @param year a <code>int</code> representing the new year.
	 */
	public void refreshNewMonth ( final Month month, final int year ) {
		this.removeAll();
		this.add(createMonthComponent(month, year));
		this.revalidate();
		this.repaint();
	}
	
}
