package regestry.service;

import regestry.entities.Applications;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ApplicationsService {
    void create(Applications entity);
    Optional<Applications> findById(Integer integer);
    Collection<Applications> findAll();
    List<Applications> findByApplicationType_NameIgnoreCase(String name);
}
