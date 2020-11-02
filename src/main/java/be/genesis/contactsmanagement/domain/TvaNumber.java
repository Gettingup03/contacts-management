package be.genesis.contactsmanagement.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TvaNumber {

    @Pattern(regexp = "(BE)?0[0-9]{9}")
    private String tva;

}
