package be.genesis.contactsmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address implements Serializable {

    @NotEmpty
    @Length(max = 50)
    private String streetName;
    @NotEmpty
    private String number;
    private String box;
    @NotEmpty
    private String municipality;
    @NotEmpty
    private String postalCode;

}
