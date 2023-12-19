package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ActTypeRepository;
import regestry.entities.ActType;
import regestry.service.ActTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
public class ActTypeServiceImpl implements ActTypeService {
    private final ActTypeRepository actTypeRepository;
    @Autowired
    public ActTypeServiceImpl(ActTypeRepository actTypeRepository){
        this.actTypeRepository = actTypeRepository;
    }
    @Transactional
    @Override
    public void create(ActType entity){
        actTypeRepository.save(entity);
    }
    @Transactional
    @Override
    public Collection<ActType> findAllByName(String name){
        return (Collection<ActType>) Optional.ofNullable(name)
                .map(actTypeRepository::findAllByNameContainingIgnoreCase)
                .orElse(actTypeRepository.findAll());
    }

    @Override
    public Optional<ActType> findById(Integer id) {
        return actTypeRepository.findById(id);
    }


}
