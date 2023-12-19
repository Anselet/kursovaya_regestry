package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActTypeDto;
import regestry.controllers.dto.ApplicationStatusDto;
import regestry.controllers.dto.ApplicationStatusUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ActType;
import regestry.entities.ApplicationStatus;
import regestry.service.ApplicationStatusService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/appstatus")
public class ApplicationStatusController {
    private final ApplicationStatusService applicationStatusService;
    @Autowired
    public ApplicationStatusController(ApplicationStatusService applicationStatusService){
        this.applicationStatusService = applicationStatusService;
    }
    @GetMapping("/findAll")
    public Collection<ApplicationStatus> findAll(@RequestParam(value = "name", required = false) String name) {
        return applicationStatusService.findAllByName(name);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ApplicationStatusDto entity) {
        applicationStatusService.create(mapDtoToDomain(entity));
    }
    private ApplicationStatus mapDtoToDomain(ApplicationStatusDto entity) {
        return new ApplicationStatus()
                .setName(entity.name());

    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody ApplicationStatusUpdateDto entity) {
        applicationStatusService.create(mapDtoToDomain(entity));
    }
    private ApplicationStatus mapDtoToDomain(ApplicationStatusUpdateDto entity) {
        return new ApplicationStatus()
                .setName(entity.name())
                .setId(entity.id());

    }
    @GetMapping("/findByID")
    public Optional<ApplicationStatus> findById(@RequestParam(value = "Id", required = false) Integer id) {
        return applicationStatusService.findById(id);
    }

    @GetMapping("/getDto")
    public ApplicationStatusDto getDto(){
        return new ApplicationStatusDto("name");
    }
}
