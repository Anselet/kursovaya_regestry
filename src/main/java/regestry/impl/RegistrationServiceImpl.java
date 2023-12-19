package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.RegistrationRepository;
import regestry.entities.Passport;
import regestry.entities.Registration;
import regestry.service.RegistrationService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository){
        this.registrationRepository = registrationRepository;
    }
    @Transactional
    @Override
    public void create(Registration entity){
        registrationRepository.save(entity);
    }

    @Override
    public Optional<Registration> findById(Integer id) {
        return registrationRepository.findById(id);
    }

    @Override
    public Collection<Registration> findAll() {
        return (Collection<Registration>) registrationRepository.findAll();
    }


}
