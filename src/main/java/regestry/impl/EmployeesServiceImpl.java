package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.EmployeesRepository;
import regestry.entities.Contacts;
import regestry.entities.Employees;
import regestry.entities.Passport;
import regestry.service.EmployeesService;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeesRepository employeesRepository;
    @Autowired
    public EmployeesServiceImpl(EmployeesRepository employeesRepository){
        this.employeesRepository = employeesRepository;
    }

    @Transactional
    @Override
    public void create(Employees entity){

        employeesRepository.save(entity);
    }

    @Override
    public Optional<Employees> findById(Integer id) {

        return employeesRepository.findById(id);
    }

    @Override
    public Employees findByCitizen_Id(Integer Id) {
        return null;
    }

    @Override
    public Collection<Employees> findAll() {

        return (Collection<Employees>) employeesRepository.findAll();
    }

    @Override
    public Employees findByPost_Id(Integer id) {
        return null;
    }

    @Override
    public Boolean tryLogin(String login, String password) {
        Employees employees = employeesRepository.findByLogin(login).orElse(null);
        if (employees == null)
            return  false;
        return (password.equals(employees.getPassword()));
    }

    @Override
    public Optional<Employees> findByLogin(String login) {
        return employeesRepository.findByLogin(login);
    }

}
