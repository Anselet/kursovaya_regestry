package regestry.service;

import regestry.entities.Citizens;
import regestry.entities.Registration;

import java.util.Collection;
import java.util.Optional;

public interface CitizensService {

    void create(Citizens entity);

    Collection<Citizens> findAllByName(String name);

    Optional<Citizens> findById(Integer id);
}
