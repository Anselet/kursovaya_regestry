package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Relations;
import regestry.entities.keys.RelationsKey;

import java.util.List;

@Repository
public interface RelationsRepository extends CrudRepository<Relations, RelationsKey> {
    List<Relations> findByPk_Parent_Id(@NonNull Integer Id);

    List<Relations> findByPk_Child_Id(@NonNull Integer Id);

    List<Relations> findByRelationType_Id(Integer id);


}
