package be.genesis.contactsmanagement.service.impl;

import be.genesis.contactsmanagement.domain.company.Company;
import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.repository.CompanyRepository;
import be.genesis.contactsmanagement.rest.company.AddSatelliteAddressesToCompanyCommand;
import be.genesis.contactsmanagement.rest.company.CreateCompanyCommand;
import be.genesis.contactsmanagement.rest.company.UpdateCompanyCommand;
import be.genesis.contactsmanagement.service.CompanyAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompanyAppServiceImpl implements CompanyAppService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyAppServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Long createCompany(CreateCompanyCommand command) {
        Company companyToCreate = Company.builder().name(command.getName()).tvaNumber(command.getTva()).headOffice(command.getHeadOffice()).satellitesAddresses(new ArrayList<>()).build();
        Company company = companyRepository.saveAndFlush(companyToCreate);
        return company.getId();
    }

    @Override
    public void updateCompany(Long companyId, UpdateCompanyCommand command) throws CompanyNotFoundException {
        Optional<Company> companyFindById = companyRepository.findById(companyId);
        if (companyFindById.isPresent()) {
            Company company = companyFindById.get();
            company.update(command);
            companyRepository.saveAndFlush(company);
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }

    @Override
    public void addSatelliteAddressToCompany(Long companyId, AddSatelliteAddressesToCompanyCommand command) throws CompanyNotFoundException {
        Optional<Company> companyFindById = companyRepository.findById(companyId);
        if (companyFindById.isPresent()) {
            Company company = companyFindById.get();
            company.addSatelliteAddresses(command.getAddresses());
            companyRepository.saveAndFlush(company);
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }
}
