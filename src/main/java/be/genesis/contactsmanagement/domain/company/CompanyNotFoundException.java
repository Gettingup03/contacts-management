package be.genesis.contactsmanagement.domain.company;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(){
        super();
    }

    public CompanyNotFoundException(Long id){
        super("Company " + id + " not found");
    }
}
