package regestry.service;

import regestry.entities.ActType;
import regestry.entities.ApplicationStatus;
import regestry.entities.ApplicationType;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationTypeService {
    void create(ApplicationType entity);
    Collection<ApplicationType> findAllByName(String name);

    Optional<ApplicationType> findById(Integer id);
}
