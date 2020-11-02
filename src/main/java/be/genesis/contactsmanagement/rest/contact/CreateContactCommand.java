package be.genesis.contactsmanagement.rest.contact;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateContactCommand {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    @Valid
    private Address address;
    @NotNull
    private ContactType type;
    @Valid
    private TvaNumber tva;

}
