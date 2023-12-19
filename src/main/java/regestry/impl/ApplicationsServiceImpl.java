package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ApplicationsRepository;
import regestry.entities.Applications;
import regestry.entities.Contacts;
import regestry.service.ApplicationsService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {
    private final ApplicationsRepository applicationsRepository;
    @Autowired
    public ApplicationsServiceImpl(ApplicationsRepository applicationsRepository){
        this.applicationsRepository = applicationsRepository;
    }
    @Transactional
    //@Override
    public void create(Applications entity) {
        applicationsRepository.save(entity);
    }

    @Override
    public Optional<Applications> findById(Integer id) {
        return applicationsRepository.findById(id);
    }

    @Override
    public Collection<Applications> findAll() {
        return (Collection<Applications>) applicationsRepository.findAll();
    }

    @Override
    public List<Applications> findByApplicationType_NameIgnoreCase(String name) {
        return (List<Applications>) applicationsRepository.findByApplicationType_NameIgnoreCase(name);
    }



}
