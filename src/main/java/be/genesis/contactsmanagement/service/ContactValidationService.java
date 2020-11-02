package be.genesis.contactsmanagement.service;

import be.genesis.contactsmanagement.domain.contact.ContactNotTvaException;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import be.genesis.contactsmanagement.domain.TvaNumber;

/**
 * Contact validation service to manage validation about contacts.
 */
public interface ContactValidationService {

    /**
     * Validate the TVA contact depending on the contact type.
     * @param type
     * @param tva
     * @throws ContactNotTvaException
     */
    void validateContactTvaDependingContactType(ContactType type, TvaNumber tva) throws ContactNotTvaException;
}
