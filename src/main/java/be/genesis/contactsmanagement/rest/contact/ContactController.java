package be.genesis.contactsmanagement.rest.contact;

import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
import be.genesis.contactsmanagement.query.ContactQueryService;
import be.genesis.contactsmanagement.query.model.ContactQueryData;
import be.genesis.contactsmanagement.service.ContactAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(value = "/api/contacts", produces = APPLICATION_JSON_VALUE)
public class ContactController {

    private final ContactAppService contactAppService;
    private final ContactQueryService contactQueryService;

    @Autowired
    public ContactController(ContactAppService contactAppService, ContactQueryService contactQueryService){
        this.contactAppService = contactAppService;
        this.contactQueryService = contactQueryService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ContactQueryData>> getContacts() {
        List<ContactQueryData> contacts = this.contactQueryService.getContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(value = "/{contactId}")
    public ResponseEntity<ContactQueryData> getContact(@PathVariable("contactId") Long contactId) throws ContactNotFoundException {
        ContactQueryData contact = this.contactQueryService.getContact(contactId);
        return ResponseEntity.ok(contact);
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactQueryData> createContact(@Valid @RequestBody CreateContactCommand command) throws ContactNotFoundException {
        Long createdContactId = this.contactAppService.createContact(command);
        ContactQueryData contact = this.contactQueryService.getContact(createdContactId);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Object> updateContact(@PathVariable("contactId") Long contactId, @Valid @RequestBody UpdateContactCommand command) throws ContactNotFoundException {
        this.contactAppService.updateContact(contactId, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Object> deleteContact(@PathVariable("contactId") Long contactId) {
        this.contactAppService.deleteContact(contactId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{contactId}/company/{companyId}")
    public ResponseEntity<ContactQueryData> addCompanyToContact(@PathVariable("contactId") Long contactId, @PathVariable("companyId") Long companyId) throws ContactNotFoundException {
        contactAppService.addCompany(contactId,companyId);
        ContactQueryData contact = contactQueryService.getContact(contactId);
        return ResponseEntity.ok(contact);
    }


}
