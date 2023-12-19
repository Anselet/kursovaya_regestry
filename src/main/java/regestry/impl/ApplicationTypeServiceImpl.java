package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ActTypeRepository;
import regestry.dao.ApplicationTypeRepository;
import regestry.entities.ActType;
import regestry.entities.ApplicationType;
import regestry.service.ApplicationTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
public class ApplicationTypeServiceImpl implements ApplicationTypeService {
    private final ApplicationTypeRepository applicationTypeRepository;
    @Autowired
    public ApplicationTypeServiceImpl(ApplicationTypeRepository applicationTypeRepository){
        this.applicationTypeRepository = applicationTypeRepository;
    }
    @Transactional
    @Override
    public void create(ApplicationType entity){ applicationTypeRepository.save(entity); }
    @Transactional
    @Override
    public Collection<ApplicationType> findAllByName(String name){
        return (Collection<ApplicationType>) Optional.ofNullable(name)
                .map(applicationTypeRepository::findAllByNameContainingIgnoreCase)
                .orElse(applicationTypeRepository.findAll());
    }

    @Override
    public Optional<ApplicationType> findById(Integer id) {
        return applicationTypeRepository.findById(id);
    }
}
