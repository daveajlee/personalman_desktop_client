package de.davelee.personalman.service;

import java.util.List;

import de.davelee.personalman.api.AbsenceRequest;
import de.davelee.personalman.api.AbsenceResponse;
import de.davelee.personalman.api.AbsencesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Class to provide service operations for absences in the PersonalMan program.
 * @author Dave Lee
 */
@Service
public class AbsenceService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${server.absenceservice.url}")
	private String absenceServiceUrl;

	/**
	 * Retrieve the server url for communicating with the absence server.
	 * @return a <code>String</code> with the server url for communicating with the absence server.
	 */
	public String getAbsenceServiceUrl() {
		return absenceServiceUrl;
	}

	/**
	 * Set the server url for communicating with the absence server.
	 * @param absenceServiceUrl a <code>String</code> with the new server url for communicating with the absence server.
	 */
	public void setAbsenceServiceUrl(final String absenceServiceUrl) {
		this.absenceServiceUrl = absenceServiceUrl;
	}

	/**
	 * Find absences taking place on the specified date.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param date a <code>String</code> with the specified date in format dd-MM-yyyy.
	 * @return a <code>List</code> of <code>AbsenceResponse</code> objects containing all absences for the specified date.
	 */
	public List<AbsenceResponse> findByDate ( final String company, final String date ) {
		return restTemplate.getForObject(absenceServiceUrl + "?company=" + company + "&startDate=" + date + "&endDate=" + date,
				AbsencesResponse.class).getAbsenceResponseList();
	}
	
	/**
	 * Find absences taking place during a particular year for a particular user.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param userName a <code>String</code> with the name of the user to find absences for.
	 * @param year a <code>int</code> with the desired year.
	 * @return a <code>AbsencesResponse</code> object containing all absences for the specified user and year.
	 */
	public AbsencesResponse findByNameAndYear ( final String company, final String userName, final int year ) {
		return restTemplate.getForObject(absenceServiceUrl + "?company=" + company + "&startDate=01-01-" + year + "&endDate=31-12-" + year,
				AbsencesResponse.class);
	}
	
	/**
	 * Count absences taking place during a particular year for a particular user and a particular absence type e.g. Holidays.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param userName a <code>String</code> with the name of the desired user.
	 * @param year a <code>int</code> with the desired year.
	 * @param category a <code>String</code> with the desired absence category e.g. Holiday.
	 * @return a <code>long</code> containing the number of absences for the specified user and year in this category.
	 */
	public long countByNameAndYearAndReason ( final String company, final String userName, final int year, final String category ) {
		AbsencesResponse absencesResponse =  restTemplate.getForObject(absenceServiceUrl + "?company=" + company + "&startDate=01-01-" + year + "&endDate=31-12-" + year
				+ "&username=" + userName + "&category=" + category + "&onlyCount=true",
				AbsencesResponse.class);
		return absencesResponse.getCount();
	}
	
	/**
	 * Save the specified absence request object to the server.
	 * @param absenceRequest a <code>AbsenceRequest</code> object to save to the server.
	 * @return a <code>boolean</code> which is true iff the absence has been successfully saved to the server.
	 */
	public boolean save ( final AbsenceRequest absenceRequest ) {
		ResponseEntity<AbsenceRequest> response = restTemplate.postForEntity(absenceServiceUrl, absenceRequest, AbsenceRequest.class);
		return response.getStatusCode() == HttpStatus.CREATED;
	}
	
	/**
	 * Delete absences matching the specified company, username and date.
	 * @param company a <code>String</code> with the company that the user is associated with.
	 * @param username a <code>String</code> with the name of the user to find absences for.
	 * @param startDate a <code>String</code> with the specified start date in format dd-MM-yyyy.
	 * @param endDate a <code>String</code> with the specified end date in format dd-MM-yyyy.
	 */
	public void delete ( final String company, final String username, final String startDate, final String endDate ) {
		//Username is optional but do not add it as a parameter if it is empty or nothing will be deleted.
		if ( username.contentEquals("")) {
			restTemplate.delete(absenceServiceUrl + "?company=" + company + "&startDate=" + startDate + "&endDate=" + endDate);
		} else {
			restTemplate.delete(absenceServiceUrl + "?company=" + company + "&startDate=" + startDate + "&endDate=" + endDate + "&username=" + username);
		}
	}
	
}
