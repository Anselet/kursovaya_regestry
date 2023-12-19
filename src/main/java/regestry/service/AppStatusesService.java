package regestry.service;

import regestry.entities.Acts;
import regestry.entities.ApplicationStatuses;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface AppStatusesService {

    void create(ApplicationStatuses entity);

    Collection<ApplicationStatuses> findAll();


    Collection<ApplicationStatuses> findByEmployeeId(Integer empId);

    Collection<ApplicationStatuses> findByStatusId(Integer statusId);


    Collection<ApplicationStatuses> findByAppId(Integer appId);

    Optional<ApplicationStatuses> findByPk(Integer appId, Integer statusId);


}
