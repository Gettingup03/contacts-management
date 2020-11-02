package be.genesis.contactsmanagement.query.model;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.company.Company;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

@Data
public class CompanyQueryData {

    private Long id;
    private String name;
    @JsonUnwrapped
    private TvaNumber tva;
    private Address headOffice;
    private List<Address> satelliteAddresses;

    public CompanyQueryData(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.tva = company.getTvaNumber();
        this.headOffice = company.getHeadOffice();
        this.satelliteAddresses = company.getSatellitesAddresses();
    }

}
