package be.genesis.contactsmanagement.domain.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(){
        super();
    }

    public ContactNotFoundException(Long id){
        super("Contact " + id + " not found");
    }
}
