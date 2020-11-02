package be.genesis.contactsmanagement.query;

import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
import be.genesis.contactsmanagement.query.model.ContactQueryData;

import java.util.List;

/**
 * A Contact Service to manage "reading" operations to be in phase with CQRS Pattern.
 * "Writing" operations are defined in the {@link be.genesis.contactsmanagement.service.ContactAppService }.
 */
public interface ContactQueryService {

    /**
     * Get all contacts.
     * @return
     */
    List<ContactQueryData> getContacts();

    /**
     * Get a contact.
     * @param contactId
     * @return
     */
    ContactQueryData getContact(Long contactId) throws ContactNotFoundException;
}
