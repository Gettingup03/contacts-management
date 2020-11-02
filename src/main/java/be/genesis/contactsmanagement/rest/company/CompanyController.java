package be.genesis.contactsmanagement.rest.company;

import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
import be.genesis.contactsmanagement.query.CompanyQueryService;
import be.genesis.contactsmanagement.query.model.CompanyQueryData;
import be.genesis.contactsmanagement.service.CompanyAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/api/companies", produces = APPLICATION_JSON_VALUE)
public class CompanyController {

    private final CompanyAppService companyAppService;
    private final CompanyQueryService companyQueryService;

    @Autowired
    public CompanyController(CompanyAppService companyAppService, CompanyQueryService companyQueryService){
        this.companyAppService = companyAppService;
        this.companyQueryService = companyQueryService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CompanyQueryData>> getCompanies() {
        List<CompanyQueryData> companies = this.companyQueryService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping(value = "/{companyId}")
    public ResponseEntity<CompanyQueryData> getCompany(@PathVariable("companyId") Long companyId) throws CompanyNotFoundException {
        CompanyQueryData company = this.companyQueryService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyQueryData> createCompany(@Valid @RequestBody CreateCompanyCommand command) throws CompanyNotFoundException {
        Long createdCompanyId = this.companyAppService.createCompany(command);
        CompanyQueryData company = this.companyQueryService.getCompany(createdCompanyId);
        return ResponseEntity.ok(company);
    }

    @PutMapping(value = "/{companyId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCompany(@PathVariable("companyId") Long companyId, @Valid @RequestBody UpdateCompanyCommand command) throws CompanyNotFoundException {
        this.companyAppService.updateCompany(companyId,command);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{companyId}/addresses")
    public ResponseEntity<CompanyQueryData> addSatelliteAddressesToCompany(@PathVariable("companyId") Long companyId, @Valid @RequestBody AddSatelliteAddressesToCompanyCommand command) throws CompanyNotFoundException {
        companyAppService.addSatelliteAddressToCompany(companyId,command);
        CompanyQueryData company = this.companyQueryService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }
}
