package be.genesis.contactsmanagement.rest.company;

import be.genesis.contactsmanagement.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddSatelliteAddressesToCompanyCommand {

    @NotNull
    private List<@Valid Address> addresses;
}
