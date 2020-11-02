package be.genesis.contactsmanagement.service.impl;


import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.company.Company;
import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.query.CompanyQueryService;
import be.genesis.contactsmanagement.repository.CompanyRepository;
import be.genesis.contactsmanagement.rest.company.AddSatelliteAddressesToCompanyCommand;
import be.genesis.contactsmanagement.rest.company.UpdateCompanyCommand;
import be.genesis.contactsmanagement.service.CompanyAppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CompanyAppServiceImplTest {

    @MockBean
    private CompanyRepository companyRepositoryMock;
    @MockBean
    private CompanyQueryService companyQueryServiceMock;

    @Autowired
    private CompanyAppService service;

    @Test(expected = CompanyNotFoundException.class)
    public void updateCompanyNotFoundThrowCompanyNotFoundException() {
        // Given
        UpdateCompanyCommand command = UpdateCompanyCommand.builder().name("Soft-AC Consulting").build();
        Long companyToUpdateId = 1L;
        // When
        when(companyRepositoryMock.findById(companyToUpdateId)).thenReturn(Optional.empty());
        service.updateCompany(companyToUpdateId,command);
    }

    @Test
    public void updateCompanySuccess() {
        // Given
        UpdateCompanyCommand command = UpdateCompanyCommand.builder().name("New Company Soft").build();
        Long companyToUpdateId = 1L;
        Company company = Company.builder().build();
        // When
        when(companyRepositoryMock.findById(companyToUpdateId)).thenReturn(Optional.of(company));
        service.updateCompany(companyToUpdateId,command);
        // Then Success
    }

    @Test(expected = CompanyNotFoundException.class)
    public void addSatelliteAddressToCompanyThrowCompanyNotFoundException() {
        // Given
        AddSatelliteAddressesToCompanyCommand command = AddSatelliteAddressesToCompanyCommand.builder().build();
        Long companyId = 1L;
        // When
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.empty());
        service.addSatelliteAddressToCompany(companyId,command);
        // Then Success
    }

    @Test
    public void addSatelliteAddressToCompanySuccess() {
        // Given
        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder().streetName("Rue des Viaducs").number("190").municipality("Mons").postalCode("7000").build());
        AddSatelliteAddressesToCompanyCommand command = AddSatelliteAddressesToCompanyCommand.builder().addresses(addresses).build();
        Long companyId = 1L;
        Company company = Company.builder().build();
        // When
        when(companyRepositoryMock.findById(companyId)).thenReturn(Optional.of(company));
        service.addSatelliteAddressToCompany(companyId,command);
        // Then Success
    }

    @Configuration
    @Import({CompanyAppServiceImpl.class })
    static class Config {
    }
}
