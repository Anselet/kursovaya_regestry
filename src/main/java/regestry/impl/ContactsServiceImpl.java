package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ContactsRepository;
import regestry.entities.Contacts;
import regestry.entities.Passport;
import regestry.entities.Registration;
import regestry.service.ContactsService;

import java.util.Collection;
import java.util.Optional;

@Service
public class ContactsServiceImpl implements ContactsService {
    private final ContactsRepository contactsRepository;
    @Autowired
    public ContactsServiceImpl(ContactsRepository contactsRepository){
        this.contactsRepository = contactsRepository;
    }
    @Transactional
    @Override
    public void create(Contacts entity) {

        contactsRepository.save(entity);
    }
    @Override
    public Optional<Contacts> findById(Integer id) {
        return contactsRepository.findById(id);
    }

    @Override
    public Collection<Contacts> findAll() {
        return (Collection<Contacts>) contactsRepository.findAll();
    }


}
