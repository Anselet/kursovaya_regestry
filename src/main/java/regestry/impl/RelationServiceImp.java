package regestry.impl;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import regestry.dao.RelationTypeRepository;
import regestry.dao.RelationsRepository;
import regestry.entities.Relations;
import regestry.service.RelationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationServiceImp implements RelationService {

    private  final RelationsRepository relationsRepository;

    @Autowired
    public RelationServiceImp(RelationsRepository relationsRepository) {
        this.relationsRepository = relationsRepository;
    }

    @Override
    public void create(Relations entity) {
        relationsRepository.save(entity);
    }

    @Override
    public List<Relations> findAll() {
        return (List<Relations>) relationsRepository.findAll();
    }

    @Override
    public List<Relations> findAllByParent(Integer parentId) {

        return relationsRepository.findByPk_Parent_Id(parentId);
    }

    @Override
    public List<Relations> findAddByChild(Integer childId) {
        return relationsRepository.findByPk_Child_Id(childId);
    }
}
