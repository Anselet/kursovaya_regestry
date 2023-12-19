package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ApplicationStatusRepository;
import regestry.entities.ActType;
import regestry.entities.ApplicationStatus;
import regestry.service.ApplicationStatusService;

import java.util.Collection;
import java.util.Optional;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusRepository applicationStatusRepository;
    @Autowired
    public ApplicationStatusServiceImpl(ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusRepository = applicationStatusRepository;
    }
    @Transactional
    @Override
    public void create(ApplicationStatus entity){
        applicationStatusRepository.save(entity);
    }
    @Transactional
    @Override
    public Collection<ApplicationStatus> findAllByName(String name){
        return (Collection<ApplicationStatus>) Optional.ofNullable(name)
                .map(applicationStatusRepository::findAllByNameContainingIgnoreCase)
                .orElse(applicationStatusRepository.findAll());
    }
    @Override
    public Optional<ApplicationStatus> findById(Integer id) {
        return applicationStatusRepository.findById(id);
    }
}
