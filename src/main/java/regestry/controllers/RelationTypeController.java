package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActTypeDto;
import regestry.controllers.dto.ActTypeUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ActType;
import regestry.entities.RelationType;
import regestry.service.RelationTypeService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/reltype")
public class RelationTypeController {
    private final RelationTypeService relationTypeService;
    @Autowired
    public RelationTypeController(RelationTypeService relationTypeService){
        this.relationTypeService = relationTypeService;
    }
    @GetMapping("/findAll")
    public Collection<RelationType> findAll(@RequestParam(value = "name", required = false) String name){
        return relationTypeService.findAllByName(name);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody RelationTypeDto entity) {

        relationTypeService.create(mapDtoToDomain(entity));
    }
    private RelationType mapDtoToDomain(RelationTypeDto entity) {
        return new RelationType()
                .setName(entity.name());

    }


    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody ActTypeUpdateDto entity) {

        relationTypeService.create(mapDtoToDomain(entity));
    }
    private RelationType mapDtoToDomain(ActTypeUpdateDto entity) {
        return new RelationType()
                .setName(entity.name())
                .setId(entity.id());

    }
    @GetMapping("/findByID")
    public Optional<RelationType> findById(@RequestParam(value = "Id", required = false) Integer id){
        return relationTypeService.findById(id);
    }

    @GetMapping("/getDto")
    public RelationTypeDto getDto(){
        return new RelationTypeDto("name");
    }
}
