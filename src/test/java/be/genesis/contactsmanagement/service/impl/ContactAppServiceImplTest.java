package be.genesis.contactsmanagement.service.impl;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.company.Company;
import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.domain.contact.Contact;
import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import be.genesis.contactsmanagement.query.ContactQueryService;
import be.genesis.contactsmanagement.repository.CompanyRepository;
import be.genesis.contactsmanagement.repository.ContactRepository;
import be.genesis.contactsmanagement.rest.contact.CreateContactCommand;
import be.genesis.contactsmanagement.rest.contact.UpdateContactCommand;
import be.genesis.contactsmanagement.service.ContactAppService;
import be.genesis.contactsmanagement.service.ContactValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContactAppServiceImplTest {

    @MockBean
    private ContactRepository contactRepositoryMock;
    @MockBean
    private ContactQueryService contactQueryServiceMock;
    @MockBean
    private ContactValidationService contactValidationServiceMock;
    @MockBean
    private CompanyRepository companyRepositoryMock;

    @Autowired
    ContactAppService service;

    @Test
    public void createContactSuccess() {
        // Given
        Address address = Address.builder().streetName("Rue des Viaducs").number("190").municipality("Mons").postalCode("7000").build();
        TvaNumber tva = TvaNumber.builder().tva("BE0755234289").build();
        CreateContactCommand command = CreateContactCommand.builder().lastName("Cloquette").firstName("Anthony").address(address).type(ContactType.FREELANCE).tva(tva).build();
        Contact createdContact = Contact.builder().id(1L).build();
        // When
        when(contactRepositoryMock.saveAndFlush(any())).thenReturn(createdContact);
        service.createContact(command);
        // Then Success
        Mockito.verify(contactValidationServiceMock, Mockito.times(1)).validateContactTvaDependingContactType(any(),any());
    }

    @Test
    public void updateContactSuccess() {
        // Given
        UpdateContactCommand command = UpdateContactCommand.builder().lastName("Cloquette").firstName("Anthony").type(ContactType.EMPLOYEE).build();
        Long contactToUpdateId = 1L;
        Contact contact = Contact.builder().build();
        // When
        when(contactRepositoryMock.findById(contactToUpdateId)).thenReturn(Optional.of(contact));
        service.updateContact(contactToUpdateId,command);
        // Then Success
        Mockito.verify(contactValidationServiceMock, Mockito.times(1)).validateContactTvaDependingContactType(any(),any());
    }

    @Test(expected = ContactNotFoundException.class)
    public void updateContactNotFoundThrowContactNotFoundException() {
        // Given
        UpdateContactCommand command = UpdateContactCommand.builder().lastName("Cloquette").firstName("Anthony").type(ContactType.EMPLOYEE).build();
        Long contactToUpdateId = 1L;
        // When
        when(contactRepositoryMock.findById(contactToUpdateId)).thenReturn(Optional.empty());
        service.updateContact(contactToUpdateId,command);
    }

    @Test
    public void deleteContactSuccess() {
        // Given
        Long contactToDeletedId = 1L;
        Contact contact = Contact.builder().build();
        // When
        when(contactRepositoryMock.findById(contactToDeletedId)).thenReturn(Optional.of(contact));
        service.deleteContact(contactToDeletedId);
        // Then Success
    }

    @Test(expected = ContactNotFoundException.class)
    public void deleteContactNotFoundThrowContactNotFoundException() {
        // Given
        Long contactToDeletedId = 1L;
        // When
        when(contactRepositoryMock.findById(contactToDeletedId)).thenReturn(Optional.empty());
        service.deleteContact(contactToDeletedId);
    }

    @Test
    public void addCompanyToContactSuccess() {
        // Given
        Long contactId = 1L, companyId = 1L;
        Contact contact = Contact.builder().build();
        Company company = Company.builder().build();
        // When
        when(contactRepositoryMock.findById(contactId)).thenReturn(Optional.of(contact));
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.of(company));
        service.addCompany(contactId, companyId);
        // Then  Success
    }

    @Test(expected = ContactNotFoundException.class)
    public void addCompanyContactNotFoundThrowContactNotFoundException() {
        // Given
        Long contactId = 1L, companyId = 1L;
        // When
        when(contactRepositoryMock.findById(contactId)).thenReturn(Optional.empty());
        service.addCompany(contactId, companyId);
    }

    @Test(expected = CompanyNotFoundException.class)
    public void addCompanyCompanyNotFoundThrowCompanyNotFoundException() {
        // Given
        Long contactId = 1L, companyId = 1L;
        Contact contact = Contact.builder().build();
        // When
        when(contactRepositoryMock.findById(contactId)).thenReturn(Optional.of(contact));
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.empty());
        service.addCompany(contactId, companyId);
    }

    @Configuration
    @Import({ ContactAppServiceImpl.class })
    static class Config {
    }
}
