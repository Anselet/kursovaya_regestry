package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActTypeUpdateDto;
import regestry.controllers.dto.ApplicationTypeDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ApplicationType;
import regestry.service.ApplicationTypeService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/apptype")
public class ApplicationTypeController {
    private final ApplicationTypeService applicationTypeService;
    @Autowired
    public ApplicationTypeController(ApplicationTypeService applicationTypeService){
        this.applicationTypeService = applicationTypeService;
    }
    @GetMapping("/findAll")
    public Collection<ApplicationType> findAll(@RequestParam(value = "name", required = false) String name) {
        return applicationTypeService.findAllByName(name);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ApplicationTypeDto entity) {
        applicationTypeService.create(mapDtoToDomain(entity));
    }
    private ApplicationType mapDtoToDomain(ApplicationTypeDto entity) {
        return new ApplicationType()
                .setName(entity.name());
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody ActTypeUpdateDto entity) {
        applicationTypeService.create(mapDtoToDomain(entity));
    }
    private ApplicationType mapDtoToDomain(ActTypeUpdateDto entity) {
        return new ApplicationType()
                .setName(entity.name())
                .setId(entity.id());
    }
    @GetMapping("/findByID")
    public Optional<ApplicationType> findById(@RequestParam(value = "Id", required = false) Integer id) {
        return applicationTypeService.findById(id);
    }

    @GetMapping("/getDto")
    public ApplicationTypeDto getDto(){
        return new ApplicationTypeDto("name");
    }
}
