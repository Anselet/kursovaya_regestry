package regestry.service;

import regestry.entities.Contacts;
import regestry.entities.Registration;

import java.util.Collection;
import java.util.Optional;

public interface ContactsService {
    void create(Contacts entity);
    Optional<Contacts> findById(Integer id);
    Collection<Contacts> findAll();
}
