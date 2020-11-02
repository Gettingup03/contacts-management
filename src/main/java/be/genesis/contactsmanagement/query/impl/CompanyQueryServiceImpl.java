package be.genesis.contactsmanagement.query.impl;

import be.genesis.contactsmanagement.domain.company.Company;
import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.query.CompanyQueryService;
import be.genesis.contactsmanagement.query.model.CompanyQueryData;
import be.genesis.contactsmanagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyQueryServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyQueryData> getCompanies() {
        List<Company> all = this.companyRepository.findAll();
        return all.stream().map(CompanyQueryData::new).collect(Collectors.toList());
    }

    @Override
    public CompanyQueryData getCompany(Long companyId) throws CompanyNotFoundException {
        Optional<Company> companyById = this.companyRepository.findById(companyId);
        if (companyById.isPresent()) {
            return new CompanyQueryData(companyById.get());
        } else {
            throw new CompanyNotFoundException(companyId);
        }
    }
}
