package be.genesis.contactsmanagement.service;

import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.rest.company.AddSatelliteAddressesToCompanyCommand;
import be.genesis.contactsmanagement.rest.company.CreateCompanyCommand;
import be.genesis.contactsmanagement.rest.company.UpdateCompanyCommand;

/**
 * A Company Service to manage "writing" operations to be in phase with CQRS Pattern.
 * "Reading" operations are defined in the {@link be.genesis.contactsmanagement.query.ContactQueryService }.
 */
public interface CompanyAppService {

    /**
     * Create a company.
     * @param command
     * @return the id of created company.
     */
    Long createCompany(CreateCompanyCommand command);

    /**
     * Update a company.
     * @param companyId
     * @param command
     */
    void updateCompany(Long companyId, UpdateCompanyCommand command) throws CompanyNotFoundException;

    /**
     * Add satellite addresses to a company.
     * @param companyId
     * @param command
     */
    void addSatelliteAddressToCompany(Long companyId, AddSatelliteAddressesToCompanyCommand command) throws CompanyNotFoundException;

}
