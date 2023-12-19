package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Contacts;

import java.util.Optional;

@Repository
public interface ContactsRepository extends CrudRepository<Contacts, Integer> {
    Optional<Contacts> findById(Integer id);

    Contacts findByCitizen_Id(@NonNull Integer Id);

}
