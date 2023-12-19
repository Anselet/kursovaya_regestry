package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.ApplicationStatuses;
import regestry.entities.keys.AppStatusesKey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationsStatusesRepository extends CrudRepository<ApplicationStatuses, AppStatusesKey> {

    List<ApplicationStatuses> findByPk_Application_Id(@NonNull Integer id);

    List<ApplicationStatuses> findByPk_Status_Id(@NonNull Integer id);

    List<ApplicationStatuses> findByEmployee_Id(@NonNull Integer Id);

    Optional<ApplicationStatuses> findByPk_Application_IdAndPk_Status_Id(@NonNull Integer id, @NonNull Integer id1);



}
