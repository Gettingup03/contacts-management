package be.genesis.contactsmanagement.query;

import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.query.model.CompanyQueryData;

import java.util.List;

/**
 * A Company Service to manage "reading" operations to be in phase with CQRS Pattern.
 * "Writing" operations are defined in the {@link be.genesis.contactsmanagement.service.CompanyAppService }.
 */
public interface CompanyQueryService {

    /**
     * Get all companies.
     * @return a list of {@link CompanyQueryData}
     */
    List<CompanyQueryData> getCompanies();

    /**
     * Get a company.
     * @param companyId
     * @return a {@link CompanyQueryData}
     */
    CompanyQueryData getCompany(Long companyId) throws CompanyNotFoundException;
}
