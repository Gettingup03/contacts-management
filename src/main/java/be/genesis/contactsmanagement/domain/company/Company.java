package be.genesis.contactsmanagement.domain.company;

import be.genesis.contactsmanagement.domain.Address;
import be.genesis.contactsmanagement.domain.TvaNumber;
import be.genesis.contactsmanagement.domain.contact.Contact;
import be.genesis.contactsmanagement.rest.company.UpdateCompanyCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "company")
@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Embedded
    private TvaNumber tvaNumber;

    @Embedded
    private Address headOffice;

    @ElementCollection
    private List<Address> satellitesAddresses;

    @ManyToOne
    private Contact contact;

    public void update(UpdateCompanyCommand command) {
        this.name = command.getName() != null ? command.getName() : this.name;
        this.tvaNumber = command.getTva() != null ? command.getTva() : this.tvaNumber;
        this.headOffice = command.getHeadOffice() != null ? command.getHeadOffice() : this.headOffice;
    }

    public void addSatelliteAddresses(List<Address> addresses){
        if(!CollectionUtils.isEmpty(addresses)){
            if(this.satellitesAddresses == null){
                this.satellitesAddresses = new ArrayList<>();
            }
            addresses.forEach(address -> this.satellitesAddresses.add(address));
        }
    }
}
