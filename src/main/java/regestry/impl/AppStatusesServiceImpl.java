package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import regestry.dao.ApplicationsStatusesRepository;
import regestry.entities.ApplicationStatuses;
import regestry.service.AppStatusesService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class AppStatusesServiceImpl implements AppStatusesService {

    private final ApplicationsStatusesRepository applicationsStatusesRepository;
    @Autowired
    public AppStatusesServiceImpl(ApplicationsStatusesRepository applicationsStatusesRepository) {
        this.applicationsStatusesRepository = applicationsStatusesRepository;
    }

    @Override
    public void create(ApplicationStatuses entity) {
        applicationsStatusesRepository.save(entity);
    }

    @Override
    public Collection<ApplicationStatuses> findAll() {
        return (Collection<ApplicationStatuses>) applicationsStatusesRepository.findAll();
    }

    @Override
    public Collection<ApplicationStatuses> findByEmployeeId(Integer empId) {
        return applicationsStatusesRepository.findByEmployee_Id(empId);
    }

    @Override
    public Collection<ApplicationStatuses> findByStatusId(Integer statusId) {
        return applicationsStatusesRepository.findByPk_Status_Id(statusId);
    }


    @Override
    public Collection<ApplicationStatuses> findByAppId(Integer appId) {
        return applicationsStatusesRepository.findByPk_Application_Id(appId);
    }

    @Override
    public Optional<ApplicationStatuses> findByPk(Integer appId, Integer statusId) {
        return applicationsStatusesRepository.findByPk_Application_IdAndPk_Status_Id(appId, statusId);
    }
}
