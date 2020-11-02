package be.genesis.contactsmanagement.query.impl;

import be.genesis.contactsmanagement.domain.contact.Contact;
import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
import be.genesis.contactsmanagement.query.ContactQueryService;
import be.genesis.contactsmanagement.query.model.ContactQueryData;
import be.genesis.contactsmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactQueryServiceImpl implements ContactQueryService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactQueryServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactQueryData> getContacts() {
        return this.contactRepository.findAll().stream().map(c -> new ContactQueryData(c)).collect(Collectors.toList());
    }

    @Override
    public ContactQueryData getContact(Long contactId) throws ContactNotFoundException {
        Optional<Contact> contactById = this.contactRepository.findById(contactId);
        if (contactById.isPresent()) {
            return new ContactQueryData(contactById.get());
        } else {
            throw new ContactNotFoundException(contactId);
        }
    }
}
