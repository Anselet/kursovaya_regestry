package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.Passport;

import java.util.List;
import java.util.Optional;
@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {
    Optional<Passport> findById(Integer id);
    //Iterable<Passport> findAllByNumberContainingIgnoreCase(Integer number);

    List<Passport> findByNumber(Integer number);
}
