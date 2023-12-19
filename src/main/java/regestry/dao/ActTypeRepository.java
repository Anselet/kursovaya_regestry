package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.ActType;
@Repository
public interface ActTypeRepository extends CrudRepository<ActType, Integer> {
    Iterable<ActType> findAllByNameContainingIgnoreCase(String name);
}
