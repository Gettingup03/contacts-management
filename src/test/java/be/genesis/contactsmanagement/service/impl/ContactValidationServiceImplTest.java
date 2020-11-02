package be.genesis.contactsmanagement.service.impl;

import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.ContactNotTvaException;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import be.genesis.contactsmanagement.service.ContactValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContactValidationServiceImplTest {

    @Autowired
    ContactValidationService service;

    @Test(expected = ContactNotTvaException.class)
    public void freelanceWithNoTvaThrowContactNotTvaException() {
        service.validateContactTvaDependingContactType(ContactType.FREELANCE,null);
    }

    @Test
    public void freelanceWithTvaSuccess() {
        TvaNumber tva = TvaNumber.builder().tva("BE07556437234").build();
        service.validateContactTvaDependingContactType(ContactType.FREELANCE,tva);
    }

    @Configuration
    @Import({ ContactValidationServiceImpl.class })
    static class Config {
    }
}
