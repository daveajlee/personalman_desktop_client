package de.davelee.personalman.service;

import de.davelee.personalman.api.CompanyResponse;
import de.davelee.personalman.api.RegisterCompanyRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class to provide service operations for companies in the PersonalMan program.
 * @author Dave Lee
 */
@Service
@Getter
@Setter
public class CompanyService {

    /**
     * REST Template to call REST services.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * URL to company service server.
     */
    @Value("${server.companyservice.url}")
    private String companyServiceUrl;

    /**
     * Register a company by saving the request to the server. The company is successfully registered if true is returned.
     * @param registerCompanyRequest a <code>RegisterCompanyRequest</code> object containing all of the data needed to store the company.
     * @return a <code>boolean</code> whoch is true iff the company is registered i.e. saved successfully.
     */
    public boolean registerCompany (final RegisterCompanyRequest registerCompanyRequest ) {
        ResponseEntity<RegisterCompanyRequest> response = restTemplate.postForEntity(companyServiceUrl, registerCompanyRequest, RegisterCompanyRequest.class);
        return response.getStatusCode() == HttpStatus.CREATED;
    }

    /**
     * Retrieve all companies from the server.
     * @return a <code>List</code> of <code>String</code> objects with the name of companies stored on the server.
     */
    public List<String> getCompanies ( ) {
        return (List<String>) restTemplate.getForObject(companyServiceUrl.replace("company", "companies"), List.class);
    }

    /**
     * Retrieve the company information from the server for a particular company based on its name.
     * @param name a <code>String</code> containing the name of the company.
     */
    public CompanyResponse getCompany (final String name ) {
        return restTemplate.getForObject(companyServiceUrl + "?name=" + name, CompanyResponse.class );
    }

}