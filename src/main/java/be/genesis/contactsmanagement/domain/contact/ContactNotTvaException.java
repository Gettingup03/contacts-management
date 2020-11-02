package be.genesis.contactsmanagement.domain.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ContactNotTvaException extends RuntimeException {

    public ContactNotTvaException(){
        super();
    }

    public ContactNotTvaException(String message){
        super(message);
    }
}
