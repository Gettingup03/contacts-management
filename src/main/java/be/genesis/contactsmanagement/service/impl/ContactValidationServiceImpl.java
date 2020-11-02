package be.genesis.contactsmanagement.service.impl;

import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.ContactNotTvaException;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import be.genesis.contactsmanagement.service.ContactValidationService;
import org.springframework.stereotype.Service;

@Service
public class ContactValidationServiceImpl implements ContactValidationService {

    @Override
    public void validateContactTvaDependingContactType(ContactType type, TvaNumber tva) throws ContactNotTvaException {
        if(type != null && type.equals(ContactType.FREELANCE) && tva == null) {
            throw new ContactNotTvaException("A Freelance (contact) must have a TVA number.");
        }
    }

}
