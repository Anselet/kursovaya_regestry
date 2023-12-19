package regestry.service;

import regestry.entities.Passport;
import regestry.entities.Registration;

import java.util.Collection;
import java.util.Optional;

public interface RegistrationService {
    void create(Registration entity);
    Optional<Registration> findById(Integer id);
    Collection<Registration> findAll();
}
