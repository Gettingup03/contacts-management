package be.genesis.contactsmanagement.rest.company;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCompanyCommand {

    @NotEmpty
    private String name;
    @NotNull
    @Valid
    private TvaNumber tva;
    @NotNull
    @Valid
    private Address headOffice;
}
