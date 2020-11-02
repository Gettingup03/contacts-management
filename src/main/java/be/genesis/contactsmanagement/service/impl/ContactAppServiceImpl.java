    package be.genesis.contactsmanagement.service.impl;

    import be.genesis.contactsmanagement.domain.company.Company;
    import be.genesis.contactsmanagement.domain.company.CompanyNotFoundException;
    import be.genesis.contactsmanagement.domain.contact.Contact;
    import be.genesis.contactsmanagement.domain.contact.ContactNotFoundException;
    import be.genesis.contactsmanagement.domain.contact.ContactType;
    import be.genesis.contactsmanagement.repository.CompanyRepository;
    import be.genesis.contactsmanagement.repository.ContactRepository;
    import be.genesis.contactsmanagement.rest.contact.CreateContactCommand;
    import be.genesis.contactsmanagement.rest.contact.UpdateContactCommand;
    import be.genesis.contactsmanagement.service.ContactAppService;
    import be.genesis.contactsmanagement.service.ContactValidationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    public class ContactAppServiceImpl implements ContactAppService {

        private final ContactRepository contactRepository;
        private final ContactValidationService contactValidationService;
        private final CompanyRepository companyRepository;

        @Autowired
        public ContactAppServiceImpl(ContactRepository contactRepository, ContactValidationService contactValidationService, CompanyRepository companyRepository) {
            this.contactRepository = contactRepository;
            this.contactValidationService = contactValidationService;
            this.companyRepository = companyRepository;
        }

        @Override
        public Long createContact(CreateContactCommand command) {
            contactValidationService.validateContactTvaDependingContactType(command.getType(), command.getTva());
            Contact contactToCreate = Contact.builder().lastName(command.getLastName()).firstName(command.getFirstName()).address(command.getAddress()).type(command.getType()).tva(command.getTva()).build();
            if (!command.getType().equals(ContactType.FREELANCE)) {
                contactToCreate.setTva(null);
            }
            Contact contact = contactRepository.saveAndFlush(contactToCreate);
            return contact.getId();
        }

        @Override
        public void updateContact(Long contactId, UpdateContactCommand command) throws ContactNotFoundException  {
            contactValidationService.validateContactTvaDependingContactType(command.getType(), command.getTva());
            Optional<Contact> contactFindById = contactRepository.findById(contactId);
            if (contactFindById.isPresent()) {
                Contact contact = contactFindById.get();
                contact.update(command);
                contactRepository.saveAndFlush(contact);
            } else {
                throw new ContactNotFoundException(contactId);
            }
        }

        @Override
        public void deleteContact(Long contactId) throws ContactNotFoundException {
            Optional<Contact> contactFindById = contactRepository.findById(contactId);
            if (contactFindById.isPresent()) {
                this.contactRepository.deleteById(contactId);
            } else {
                throw new ContactNotFoundException(contactId);
            }
        }

        @Override
        public void addCompany(Long contactId, Long companyId) throws CompanyNotFoundException, ContactNotFoundException {
            Optional<Contact> contactFindById = contactRepository.findById(contactId);
            Optional<Company> companyFindById = companyRepository.findById(companyId);
            if (contactFindById.isPresent()) {
                Contact contact = contactFindById.get();
                if (companyFindById.isPresent()) {
                    contact.addCompany(companyFindById.get());
                    contactRepository.saveAndFlush(contact);
                } else {
                    throw new CompanyNotFoundException(companyId);
                }
            } else {
                throw new ContactNotFoundException(contactId);
            }
        }
    }
