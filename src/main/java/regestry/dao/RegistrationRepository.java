package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Registration;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {
    Optional<Registration> findById(Integer id);

    Registration findByPassport_Id(@NonNull Integer Id);


}
