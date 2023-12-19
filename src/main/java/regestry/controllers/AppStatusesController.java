package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActsDto;
import regestry.controllers.dto.AppStatusesDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Acts;
import regestry.entities.ApplicationStatus;
import regestry.entities.ApplicationStatuses;
import regestry.entities.keys.AppStatusesKey;
import regestry.service.AppStatusesService;
import regestry.service.ApplicationStatusService;
import regestry.service.ApplicationsService;
import regestry.service.EmployeesService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
@RestController
@RequestMapping("/appstatuses")
public class AppStatusesController {

    private  final AppStatusesService appStatusesService;
    private final ApplicationsService applicationsService;
    private final ApplicationStatusService applicationStatusService;

    private final EmployeesService employeesService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Autowired
    public AppStatusesController(
            AppStatusesService appStatusesService,
            ApplicationsService applicationsService,
            ApplicationStatusService applicationStatusService,
            EmployeesService employeesService) {
        this.appStatusesService = appStatusesService;
        this.applicationsService = applicationsService;
        this.applicationStatusService =applicationStatusService;
        this.employeesService =employeesService;
    }

    @GetMapping("/findAll")
    public Collection<ApplicationStatuses> findAll() {
        return appStatusesService.findAll();
    }

    @GetMapping("/findByAppId")
    public Collection<ApplicationStatuses> findByAppId(@RequestParam(value = "id", required = false) Integer id) {
        return appStatusesService.findByAppId(id);
    }

    @GetMapping("/findByEmpId")
    public Collection<ApplicationStatuses> findByEmpId(@RequestParam(value = "id", required = false) Integer id) {
        return appStatusesService.findByEmployeeId(id);
    }

    @GetMapping("/findByStatusId")
    public Collection<ApplicationStatuses> findByStatusId(@RequestParam(value = "id", required = false) Integer id) {
        return appStatusesService.findByStatusId(id);
    }

    @GetMapping("/findByPK")
    public Optional<ApplicationStatuses> findByPK(@RequestParam(value="application")Integer appId, @RequestParam(value="status")Integer statusId){
        return  appStatusesService.findByPk(appId,statusId);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody AppStatusesDto entity) {

        appStatusesService.create(mapDtoToDomain(entity));
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody AppStatusesDto entity) {

        appStatusesService.create(mapDtoToDomain(entity));
    }


    private ApplicationStatuses mapDtoToDomain(AppStatusesDto dto) {
        AppStatusesKey pk = new AppStatusesKey();
        pk.setApplication(applicationsService.findById(dto.application()).get());
        pk.setStatus(applicationStatusService.findById(dto.status()).get());

        return new ApplicationStatuses()
                .setPk(pk)
                .setChangeDate(LocalDateTime.parse(dto.changeDate(),formatter))
                .setEmployee(employeesService.findById(dto.employee()).get());
    }

    @GetMapping("/getDto")
    public AppStatusesDto getDto(){
        return new AppStatusesDto(1, 1, 1, LocalDateTime.now().toString());
    }

}
