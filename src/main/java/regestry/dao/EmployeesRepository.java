package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Employees;
import regestry.entities.Passport;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
    Optional<Employees> findById(Integer id);

    Employees findByCitizen_Id(@NonNull Integer Id);

    Employees findByPost_Id(@NonNull Integer id);

    Optional<Employees> findByLogin(@NonNull String login);


}
