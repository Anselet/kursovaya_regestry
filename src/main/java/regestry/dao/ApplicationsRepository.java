package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Applications;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationsRepository extends CrudRepository<Applications, Integer> {

    @Override
    Optional<Applications> findById(Integer integer);

    List<Applications> findByApplicationType_NameIgnoreCase(String name);

    Applications findByCitizen_Id(@NonNull Integer Id);


}
