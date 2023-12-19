package regestry.service;

import org.springframework.lang.NonNull;
import regestry.entities.Contacts;
import regestry.entities.Employees;

import java.util.Collection;
import java.util.Optional;

public interface EmployeesService {
    void create(Employees entity);
    Optional<Employees> findById(Integer id);

    Employees findByCitizen_Id(@NonNull Integer Id);
    Collection<Employees> findAll();

    Employees findByPost_Id(@NonNull Integer id);
    Boolean tryLogin(String login, String password);

    Optional<Employees> findByLogin(String login);
}
