package regestry.service;

import regestry.entities.Acts;
import regestry.entities.Contacts;

import java.util.Collection;
import java.util.Optional;

public interface ActsService {
    void create(Acts entity);
    Collection<Acts> findAll();
    Optional<Acts> findById(Integer id);
}
