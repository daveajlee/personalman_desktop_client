package de.davelee.personalman.gui;

import org.springframework.beans.factory.annotation.Value;

/**
 * Parameter class for localisation of reason text.
 * @author Dave Lee
 */
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
	 * Set the localisation text for illness.
	 * @param illness a <code>String</code> with the new localisation text for illness.
	 */
	public void setIllness(final String illness) {
		this.illness = illness;
	}
	
	/**
	 * Get the localisation text for holiday.
	 * @return a <code>String</Code> with the localisation text for holiday.
	 */
	public String getHoliday() {
		return holiday;
	}
	
	/**
	 * Set the localisation text for holiday.
	 * @param holiday a <code>String</code> with the new localisation text for holiday.
	 */
	public void setHoliday(final String holiday) {
		this.holiday = holiday;
	}
	
	/**
	 * Get the localisation text for trip.
	 * @return a <code>String</Code> with the localisation text for trip.
	 */
	public String getTrip() {
		return trip;
	}
	
	/**
	 * Set the localisation text for trip.
	 * @param trip a <code>String</code> with the new localisation text for trip.
	 */
	public void setTrip(final String trip) {
		this.trip = trip;
	}
	
	/**
	 * Get the localisation text for conference.
	 * @return a <code>String</Code> with the localisation text for conference.
	 */
	public String getConference() {
		return conference;
	}
	
	/**
	 * Set the localisation text for conference.
	 * @param conference a <code>String</code> with the new localisation text for conference.
	 */
	public void setConference(final String conference) {
		this.conference = conference;
	}
	
	/**
	 * Get the localisation text for day-in-lieu.
	 * @return a <code>String</Code> with the localisation text for day-in-lieu.
	 */
	public String getDayInLieu() {
		return dayInLieu;
	}
	
	/**
	 * Set the localisation text for day-in-lieu.
	 * @param dayInLieu a <code>String</code> with the new localisation text for day-in-lieu.
	 */
	public void setDayInLieu(final String dayInLieu) {
		this.dayInLieu = dayInLieu;
	}
	
	/**
	 * Get the localisation text for public or federal holiday.
	 * @return a <code>String</Code> with the localisation text for public or federal holiday.
	 */
	public String getPublicHoliday() {
		return publicHoliday;
	}
	
	/**
	 * Set the localisation text for public or federal holiday.
	 * @param publicHoliday a <code>String</code> with the new localisation text for public or federal holiday.
	 */
	public void setPublicHoliday(final String publicHoliday) {
		this.publicHoliday = publicHoliday;
	}

}
