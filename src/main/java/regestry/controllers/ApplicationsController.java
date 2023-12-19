package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ApplicationTypeDto;
import regestry.controllers.dto.ApplicationUpdateDto;
import regestry.controllers.dto.ApplicationsDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ApplicationType;
import regestry.entities.Applications;
import regestry.service.ActsService;
import regestry.service.ApplicationTypeService;
import regestry.service.ApplicationsService;
import regestry.service.CitizensService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {
    private final CitizensService citizensService;
    private final ActsService actsService;
    private final ApplicationTypeService applicationTypeService;
    private final ApplicationsService applicationsService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Autowired
    public ApplicationsController(ApplicationTypeService applicationTypeService, ActsService actsService, CitizensService citizensService, ApplicationsService applicationsService){
        this.actsService = actsService;
        this.applicationTypeService = applicationTypeService;
        this.citizensService = citizensService;
        this.applicationsService = applicationsService;
    }
    @GetMapping("/findByID")
    public Optional<Applications> findById(@RequestParam(value = "Id", required = false) Integer id) {
        return applicationsService.findById(id);
    }
    @GetMapping("/findAll")
    public Collection<Applications> findAll(){
        var tmp =applicationsService.findAll();
        return tmp;
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ApplicationsDto entity) {
        applicationsService.create(mapDtoToDomain(entity));
    }
    private Applications mapDtoToDomain(ApplicationsDto entity) {
        return new Applications()
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setApplicationType(applicationTypeService.findById(entity.application_type_id()).get())
                .setActs(actsService.findById(entity.reg_num()).orElse(null))
                .setCitizen(citizensService.findById(entity.creator_id()).get());
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ApplicationUpdateDto entity) {
        applicationsService.create(mapDtoToDomain(entity));
    }
    private Applications mapDtoToDomain(ApplicationUpdateDto entity) {
        return new Applications()
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setApplicationType(applicationTypeService.findById(entity.applicationType()).get())
                .setActs(actsService.findById(entity.acts()).orElse(null))
                .setCitizen(citizensService.findById(entity.citizen()).get())
                .setId(entity.id());
    }

    @GetMapping("/getDto")
    public ApplicationsDto getDto(){
        return new ApplicationsDto(LocalDate.now().toString(),
                1,
                1,
                1);
    }
}
