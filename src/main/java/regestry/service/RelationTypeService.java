package regestry.service;

import regestry.entities.RelationType;

import java.util.Collection;
import java.util.Optional;

public interface RelationTypeService {
    void create(RelationType entity);
    Collection<RelationType> findAllByName(String name);

    Optional<RelationType> findById(Integer id);
}
