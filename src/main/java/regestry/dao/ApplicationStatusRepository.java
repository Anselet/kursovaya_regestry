package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.ActType;
import regestry.entities.ApplicationStatus;

@Repository
public interface ApplicationStatusRepository extends CrudRepository<ApplicationStatus, Integer> {
    Iterable<ApplicationStatus> findAllByNameContainingIgnoreCase(String name);
}
