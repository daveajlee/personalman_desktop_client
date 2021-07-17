package de.davelee.personalman.gui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Parameter class for localisation of reason text.
 * @author Dave Lee
 */
@Component
public class ReasonNames {

	@Value("${reason.ill}")
	private String illness;
	@Value("${reason.hol}")
	private String holiday;
	@Value("${reason.trip}")
	private String trip;
	@Value("${reason.conf}")
	private String conference;
	@Value("${reason.lieu}")
	private String dayInLieu;
	@Value("${reason.fedhol}")
	private String publicHoliday;
	
	/**
	 * Get the localisation text for illness.
	 * @return a <code>String</Code> with the localisation text for illness.
	 */
	public String getIllness() {
		return illness;
	}
	
	/**
	 * Get the localisation text for holiday.
	 * @return a <code>String</Code> with the localisation text for holiday.
	 */
	public String getHoliday() {
		return holiday;
	}
	
	/**
	 * Get the localisation text for trip.
	 * @return a <code>String</Code> with the localisation text for trip.
	 */
	public String getTrip() {
		return trip;
	}
	
	/**
	 * Get the localisation text for conference.
	 * @return a <code>String</Code> with the localisation text for conference.
	 */
	public String getConference() {
		return conference;
	}
	
	/**
	 * Get the localisation text for day-in-lieu.
	 * @return a <code>String</Code> with the localisation text for day-in-lieu.
	 */
	public String getDayInLieu() {
		return dayInLieu;
	}
	
	/**
	 * Get the localisation text for public or federal holiday.
	 * @return a <code>String</Code> with the localisation text for public or federal holiday.
	 */
	public String getPublicHoliday() {
		return publicHoliday;
	}

}
