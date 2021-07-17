package de.davelee.personalman.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;

import javax.swing.*;

import de.davelee.personalman.api.AbsenceResponse;

import de.davelee.personalman.UserInterface;

/**
 * Class to provide click functionality for each of the dates on the month panel.
 * @author Dave Lee
 */
public class ClickDateMouseListener implements MouseListener {
	
	private final UserInterface userInterface;
	private final LocalDate localDate;
	private final String company;
	private final String username;
	private final MonthPanel monthPanel;
	
	/**
	 * Create a new ClickDateMouseListener object.
	 * @param userInterface a <code>UserInterface</code> object which currently manages the user interface.
	 * @param localDate a <code>LocalDate</code> object representing the clickable date.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the username which should be used when viewing or adding absences.
	 * @param monthPanel a <code>MonthPanel</code> object representing the displayed month panel.
	 */
	public ClickDateMouseListener ( final UserInterface userInterface, final LocalDate localDate, final String company, final String username, final MonthPanel monthPanel ) {
		this.userInterface = userInterface;
		this.localDate = localDate;
		this.company = company;
		this.username = username;
		this.monthPanel = monthPanel;
	}
	
	/**
	 * Perform actions when the mouse is clicked.
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
	public void mouseClicked(final MouseEvent event) {
		List<AbsenceResponse> absenceResponses = userInterface.getAbsences(company, localDate.format(UserInterface.DATE_TIME_FORMATTER));
		if ( absenceResponses == null ) {
			JOptionPane.showMessageDialog(null, "Could not reach PersonalMan server so no data can be displayed for this date!",
					"Passwords do not match", JOptionPane.ERROR_MESSAGE, new ImageIcon(UserInterface.class.getResource("/images/personalmanlogo-icon.png")));
		} else {
			StringBuilder textBuilder = new StringBuilder();
			for (AbsenceResponse absenceResponse : absenceResponses) {
				textBuilder.append(absenceResponse.getUsername());
				textBuilder.append(" ");
				textBuilder.append(absenceResponse.getCategory());
				textBuilder.append("\n");
			}
			String text = textBuilder.toString();
			if (text.contentEquals("")) {
				text = userInterface.getUserInterfaceMessages().getAbsencesNoneMessage();
			}
			int result = showOptionDialog(text);
			if (result == JOptionPane.YES_OPTION) {
				createAddAbsenceScreen();
			} else if (result == JOptionPane.NO_OPTION) {
				userInterface.deleteAbsences(company, "", localDate.format(UserInterface.DATE_TIME_FORMATTER), localDate.format(UserInterface.DATE_TIME_FORMATTER));
			}
		}
	}
	
	/**
	 * Show an option dialog with the specified text and return the selected option from the user.
	 * @param text a <code>String</code> with the specified text.
	 * @return a <code>int</code> with the user-selected option.
	 */
	public int showOptionDialog ( final String text ) {
		//If text is empty, then only show add and close buttons.
		if ( text.contentEquals(userInterface.getUserInterfaceMessages().getAbsencesNoneMessage()) ) {
			return JOptionPane.showOptionDialog(monthPanel, text,
					localDate.getMonth().getDisplayName(TextStyle.FULL, userInterface.getMyLocale()) + ", " + localDate.getDayOfMonth() + " " + localDate.getYear(),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{userInterface.getUserInterfaceMessages().getAbsencesAddButton(),
							userInterface.getUserInterfaceMessages().getAbsencesCloseButton()}, userInterface.getUserInterfaceMessages().getAbsencesCloseButton() );
		}
		//Otherwise show add, remove and close buttons.
		return JOptionPane.showOptionDialog(monthPanel, text,
				localDate.getMonth().getDisplayName(TextStyle.FULL, userInterface.getMyLocale()) + ", " + localDate.getDayOfMonth() + " " + localDate.getYear(), 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{userInterface.getUserInterfaceMessages().getAbsencesAddButton(),
			userInterface.getUserInterfaceMessages().getAbsencesRemoveButton(), userInterface.getUserInterfaceMessages().getAbsencesCloseButton()}, userInterface.getUserInterfaceMessages().getAbsencesCloseButton() );
	}
	
	/**
	 * Create a new add absence screen as a dialog.
	 */
	public void createAddAbsenceScreen ( ) {
		new JDialog(new AddAbsenceScreen(userInterface, localDate, company, username), true);
	}
	
	/**
	 * Perform actions when the mouse is exited from the clickable area (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
	public void mouseExited(final MouseEvent event) {
		// mouseExited method for monthPanel is not implemented!
	}
	
	/**
	 * Perform actions when the mouse enters the clickable area (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
	public void mouseEntered(final MouseEvent event) {
		// mouseEntered method for monthPanel is not implemented!
	}
	
	/**
	 * Perform actions when the user releases the mouse button (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
	public void mouseReleased(final MouseEvent event) {
		// mouseReleased method for monthPanel is not implemented!
	}
	
	/**
	 * Perform actions when the user presses the mouse button (not currently implemented).
	 * @param event a <code>MouseEvent</code> representing the mouse event.
	 */
	public void mousePressed(final MouseEvent event) {
		// mousePressed method for monthPanel is not implemented!
	}

}
