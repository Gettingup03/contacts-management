package be.genesis.contactsmanagement.service;

import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
import be.genesis.contactsmanagement.rest.contact.CreateContactCommand;
import be.genesis.contactsmanagement.rest.contact.UpdateContactCommand;

/**
 * A Contact Service to manage "writing" operations to be in phase with CQRS Pattern.
 * "Reading" operations are defined in the {@link be.genesis.contactsmanagement.query.ContactQueryService }.
 */
public interface ContactAppService {

    /**
     * Create a contact.
     * @param command
     * @return the id of the created contact.
     */
    Long createContact(CreateContactCommand command) ;

    /**
     * Update a contact.
     * @param contactId
     * @param command
     */
    void updateContact(Long contactId, UpdateContactCommand command) throws ContactNotFoundException;

    /**
     * Delete a contact.
     * @param contactId
     */
    void deleteContact(Long contactId) throws ContactNotFoundException;

    /**
     * Add a company where the contact works.
     * @param contactId
     * @param companyId
     */
    void addCompany(Long contactId, Long companyId) throws CompanyNotFoundException, ContactNotFoundException;
}
