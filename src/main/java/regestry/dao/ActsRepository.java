package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import regestry.entities.Acts;
import regestry.entities.Post;

import java.util.Optional;

@Repository
public interface ActsRepository extends CrudRepository<Acts, Integer> {
    Acts findByRegNumber(@NonNull Integer regNumber);

    Acts findByActType_Id(@NonNull Integer id);


}
