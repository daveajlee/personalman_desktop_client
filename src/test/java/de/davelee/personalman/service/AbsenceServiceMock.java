package de.davelee.personalman.service;

import de.davelee.personalman.UserInterface;
import de.davelee.personalman.api.AbsenceRequest;
import de.davelee.personalman.api.AbsenceResponse;
import de.davelee.personalman.api.AbsencesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to implement some of the Absence Service functionality without having to run the backend server for JUnit tests.
 * @author Dave Lee
 */
public class AbsenceServiceMock extends AbsenceService {
	
	private static final String EMPLOYEE_NAME = "mmustermann";

	private static final Logger LOG = LoggerFactory.getLogger(AbsenceServiceMock.class);

	/**
	 * Return a fixed set of absences instead of asking backend server.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param date a <code>String</code> with the specified date in format dd-MM-yyyy.
	 * @return a <code>List</code> of <code>AbsenceResponse</code> objects containing all absences for the specified date.
	 */
	public List<AbsenceResponse> findByDate(final String company, final String date) {
		List<AbsenceResponse> absences = new ArrayList<>();
		if (LocalDate.parse(date, UserInterface.DATE_TIME_FORMATTER).isEqual(LocalDate.of(2015, 4, 3)) ||
				LocalDate.parse(date, UserInterface.DATE_TIME_FORMATTER).isEqual(LocalDate.of(2015, 4, 6))) {
					absences.add(new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "03-04-2015",
						"06-04-2015", "Federal Holiday"));
		}
		return absences;
	}

	/**
	 * Return a fixed set of absences for the particular year and user instead of asking backend server.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param name a <code>String</code> with the name of the user to find absences for.
	 * @param year a <code>int</code> with the desired year.
	 * @return a <code>AbsencesResponse</code> object containing all absences for the specified user and year.
	 */
	public AbsencesResponse findByNameAndYear(final String company, final String name, final int year) {
		AbsencesResponse absencesResponse = new AbsencesResponse();
		List<AbsenceResponse> absences = new ArrayList<>();
		if ( name.contentEquals(EMPLOYEE_NAME) && year == 2015) {
			absences.add(new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "03-04-2015", "06-04-2015", "Federal Holiday"));
		} else if ( name.contentEquals(EMPLOYEE_NAME) && year == 2016) {
			AbsenceResponse absenceResponse1 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "03-04-2016", "06-04-2016", "Federal Holiday");
			AbsenceResponse absenceResponse2 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "05-04-2016", "05-04-2016", "Day in Lieu Request");
			AbsenceResponse absenceResponse3 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "11-04-2016", "11-04-2016", "Day in Lieu");
			AbsenceResponse absenceResponse4 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "29-04-2016", "30-04-2016", "Conference");
			AbsenceResponse absenceResponse5 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "03-05-2016", "06-05-2016", "Trip");
			AbsenceResponse absenceResponse6 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "11-05-2016", "11-05-2016", "Holiday");
			AbsenceResponse absenceResponse7 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "14-05-2016", "14-05-2016", "Illness");
			absences.add(absenceResponse1);
			absences.add(absenceResponse2);
			absences.add(absenceResponse3);
			absences.add(absenceResponse4);
			absences.add(absenceResponse5);
			absences.add(absenceResponse6);
			absences.add(absenceResponse7);
		} else if ( name.contentEquals(EMPLOYEE_NAME) && year == 2017) {
			AbsenceResponse absenceResponse8 = new AbsenceResponse("MyCompany", EMPLOYEE_NAME, "05-06-2017", "08-06-2017", "Conference");
			absences.add(absenceResponse8);
		}
		absencesResponse.setAbsenceResponseList(absences);
		HashMap<String, Integer> statisticsMap = new HashMap<>();
		if ( year == 2015 ) {
			statisticsMap.put("Day in Lieu", 0);
			statisticsMap.put("Illness", 0);
			statisticsMap.put("Holiday", 0);
			statisticsMap.put("Trip", 0);
			statisticsMap.put("Conference", 0);
			statisticsMap.put("Federal Holiday", 4);
		} else if ( year == 2016 ) {
			statisticsMap.put("Day in Lieu", 1);
			statisticsMap.put("Illness", 1);
			statisticsMap.put("Holiday", 1);
			statisticsMap.put("Trip", 4);
			statisticsMap.put("Conference", 2);
			statisticsMap.put("Federal Holiday", 4);
		} else if ( year == 2017 ) {
			statisticsMap.put("Day in Lieu", 0);
			statisticsMap.put("Illness", 0);
			statisticsMap.put("Holiday", 0);
			statisticsMap.put("Trip", 0);
			statisticsMap.put("Conference", 4);
			statisticsMap.put("Federal Holiday", 0);
		}
		absencesResponse.setStatisticsMap(statisticsMap);
		return absencesResponse;
	}

	/**
	 * Instead of counting the absences from backend server, return a fixed number for the count.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param employeeName a <code>String</code> with the employee name to find.
	 * @param year a <code>int</code> with the desired year.
	 * @param category a <code>String</code> with the desired absence category e.g. Holiday.
	 * @return a <code>long</code> which returns the number of absences found.
	 */
	public long countByNameAndYearAndReason ( final String company, final String employeeName, final int year, final String category ) {
		if ( company.contentEquals("MyCompany") && employeeName.contentEquals("csmith")) {
			return 1;
		} else if ( company.contentEquals("MyCompany") && employeeName.contentEquals("jmctavish") && category.contentEquals("Day in Lieu Request")) {
			return 2;
		}
		return 0;
	}

	/**
	 * Do not save any absences but instead display a save message.
	 * @param absenceRequest a <code>AbsenceRequest</code> object to save to the server (but do not save it).
	 */
	public boolean save ( AbsenceRequest absenceRequest ) {
		LOG.info("The Mock does not save any data!");
		return true;
	}

	/**
	 * Do not delete any absences but instead display a log message.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the name of the user to find absences for.
	 * @param startDate a <code>String</code> with the specified start date in format dd-MM-yyyy.
	 * @param endDate a <code>String</code> with the specified end date in format dd-MM-yyyy.
	 */
	public void delete ( String company, String username, String startDate, String endDate ) {
		LOG.info("The Mock does not delete any data!");
	}

}
