package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.ActType;
import regestry.entities.ApplicationType;

@Repository
public interface ApplicationTypeRepository extends CrudRepository<ApplicationType, Integer> {
    Iterable<ApplicationType> findAllByNameContainingIgnoreCase(String name);
}
