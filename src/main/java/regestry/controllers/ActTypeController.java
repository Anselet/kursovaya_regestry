package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActTypeDto;
import regestry.controllers.dto.ActTypeUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ActType;
import regestry.service.ActTypeService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/acttype")
public class ActTypeController {
    private final ActTypeService actTypeService;
    @Autowired
    public ActTypeController(ActTypeService actTypeService){

        this.actTypeService = actTypeService;
    }
    @GetMapping("/findAll")
    public Collection<ActType> findAll(@RequestParam(value = "name", required = false) String name){
        return actTypeService.findAllByName(name);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ActTypeDto entity) {

        actTypeService.create(mapDtoToDomain(entity));
    }
    private ActType mapDtoToDomain(ActTypeDto entity) {
        return new ActType()
                .setName(entity.name());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/update")
    public void update(@RequestBody ActTypeUpdateDto entity) {

        actTypeService.create(mapDtoToDomain(entity));
    }
    private ActType mapDtoToDomain(ActTypeUpdateDto entity) {
        return new ActType()
                .setName(entity.name())
                .setId(entity.id());

    }

    @GetMapping("/findByID")
    public Optional<ActType> findById(@RequestParam(value = "Id", required = false) Integer id){
        return actTypeService.findById(id);
    }

    @GetMapping("/getDto")
    public ActTypeDto getDto(){
        return new ActTypeDto("name");
    }

}
