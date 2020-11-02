package be.genesis.contactsmanagement.query.model;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.Contact;
import be.genesis.contactsmanagement.domain.contact.ContactType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContactQueryData {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private ContactType type;
    @JsonUnwrapped
    private TvaNumber tva;
    private List<CompanyQueryData> companies;

    public ContactQueryData(Contact contact){
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.address = contact.getAddress();
        this.type = contact.getType();
        this.tva = contact.getTva();
        if(!CollectionUtils.isEmpty(contact.getCompanies())){
            this.companies = contact.getCompanies().stream().map(company -> new CompanyQueryData(company)).collect(Collectors.toList());
        }
    }

}
