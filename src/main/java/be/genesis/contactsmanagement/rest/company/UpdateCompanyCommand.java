package be.genesis.contactsmanagement.rest.company;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Builder
public class UpdateCompanyCommand {

    private String name;
    @Valid
    private TvaNumber tva;
    @Valid
    private Address headOffice;
}
