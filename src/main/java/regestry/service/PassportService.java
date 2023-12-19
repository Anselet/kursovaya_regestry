package regestry.service;

import regestry.entities.Passport;

import java.util.Collection;
import java.util.Optional;

public interface PassportService {
    void create(Passport entity);

    Collection<Passport> findAllByNumber(Integer number);

    Optional<Passport> findById(Integer id);
}
