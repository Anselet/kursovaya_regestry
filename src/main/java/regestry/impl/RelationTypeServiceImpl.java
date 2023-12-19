package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.RelationTypeRepository;
import regestry.entities.RelationType;
import regestry.service.RelationTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
public class RelationTypeServiceImpl implements RelationTypeService {
    private final RelationTypeRepository relationTypeRepository;
    @Autowired
    public RelationTypeServiceImpl(RelationTypeRepository relationTypeRepository){
        this.relationTypeRepository = relationTypeRepository;
    }
    @Transactional
    @Override
    public void create(RelationType entity){

        relationTypeRepository.save(entity);
    }
    @Transactional
    @Override
    public Collection<RelationType> findAllByName(String name){
        return (Collection<RelationType>) Optional.ofNullable(name)
                .map(relationTypeRepository::findAllByNameContainingIgnoreCase)
                .orElse(relationTypeRepository.findAll());
    }

    @Override
    public Optional<RelationType> findById(Integer id) {
        return relationTypeRepository.findById(id);
    }
}
