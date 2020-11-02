package be.genesis.contactsmanagement.rest.contact;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Data
@Builder
public class UpdateContactCommand {

    private String firstName;
    private String lastName;
    private Address address;
    private ContactType type;
    @Valid
    private TvaNumber tva;

}
