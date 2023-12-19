package regestry.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.Citizens;
import regestry.entities.Registration;

import java.util.Optional;

@Repository
public interface CitizensRepository extends CrudRepository<Citizens, Integer> {
    @Override
    Optional<Citizens> findById(Integer id);

    Iterable<Citizens> findAllByNameContainingIgnoreCase(String name);
}
