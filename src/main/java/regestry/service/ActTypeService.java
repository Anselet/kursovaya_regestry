package regestry.service;

import regestry.entities.ActType;

import java.util.Collection;
import java.util.Optional;

public interface ActTypeService {
    void create(ActType entity);
    Collection<ActType> findAllByName(String name);

    Optional<ActType> findById(Integer id);
}
