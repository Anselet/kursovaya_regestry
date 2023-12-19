package regestry.service;

import regestry.entities.ActType;
import regestry.entities.ApplicationStatus;

import java.util.Collection;
import java.util.Optional;

public interface ApplicationStatusService {
    void create(ApplicationStatus entity);
    Collection<ApplicationStatus> findAllByName(String name);

    Optional<ApplicationStatus> findById(Integer id);
}
