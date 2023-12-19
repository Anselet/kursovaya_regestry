package regestry.service;

import regestry.entities.Relations;

import java.util.List;

public interface RelationService {

    void create(Relations entity);

    List<Relations> findAll();

    List<Relations> findAllByParent(Integer parentId);

    List<Relations> findAddByChild(Integer childId);

}
