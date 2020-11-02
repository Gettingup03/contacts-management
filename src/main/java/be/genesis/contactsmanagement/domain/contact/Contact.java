package be.genesis.contactsmanagement.domain.contact;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.company.Company;
import be.genesis.contactsmanagement.rest.contact.UpdateContactCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @NotNull
    @Column
    private String firstName;
    @NotNull
    @Column
    private String lastName;
    @NotNull
    @Embedded
    private Address address;

    @Column
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Embedded
    private TvaNumber tva;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Company> companies;

    public void update(UpdateContactCommand command) {
       this.firstName = command.getFirstName() != null ? command.getFirstName() : this.firstName;
       this.lastName = command.getLastName() != null ? command.getLastName() : this.lastName ;
       this.address = command.getAddress() != null ? command.getAddress() : this.address ;
       this.type = command.getType() != null ? command.getType() : this.type;
       this.tva = command.getTva() != null ? command.getTva() : this.tva;
        if (command.getType() != null && !command.getType().equals(ContactType.FREELANCE)) {
            this.tva = null;
        }
    }

    public void addCompany(Company company) {
        if(company != null) {
            if(this.companies == null){
                this.companies = new ArrayList<>();
            }
            boolean alreadyPresent = this.companies.stream().anyMatch(c -> c.getId().equals(company.getId()));
            if(!alreadyPresent){
                this.companies.add(company);
            }
        }
    }

}
