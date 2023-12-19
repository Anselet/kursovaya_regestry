package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.RelationType;

@Repository
public interface RelationTypeRepository extends CrudRepository<RelationType, Integer> {
    Iterable<RelationType> findAllByNameContainingIgnoreCase(String name);
}
