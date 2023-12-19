package regestry.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.RelationDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.controllers.dto.RelationUpdateDto;
import regestry.entities.Relations;
import regestry.entities.keys.RelationsKey;
import regestry.service.CitizensService;
import regestry.service.RelationService;
import regestry.service.RelationTypeService;

import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationsController {

    private final RelationService relationService;
    private final RelationTypeService relationTypeService;
    private final CitizensService citizensService;

    @Autowired
    public RelationsController(
            RelationService relationService,
            RelationTypeService relationTypeService,
            CitizensService citizensService){
        this.relationService = relationService;
        this.relationTypeService =relationTypeService;
        this.citizensService = citizensService;
    }

    @GetMapping("/findAll")
    public List<Relations> findAll(){
        return relationService.findAll();
    }

    @GetMapping("/findByParent")
    public  List<Relations> findAllByParent(@RequestParam(name = "parentId")Integer parentId){
        return  relationService.findAllByParent(parentId);
    }

    @GetMapping("/findByChild")
    public  List<Relations> findAllByChild(@RequestParam(name = "childId")Integer childId){
        return  relationService.findAddByChild(childId);
    }


    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody RelationDto dto){
        relationService.create(mapDtoToDomain(dto));
    }

    private Relations mapDtoToDomain(RelationDto dto){
        RelationsKey rk = new RelationsKey();
        rk.setParent(citizensService.findById(dto.parentId()).orElse(null));
        rk.setChild(citizensService.findById(dto.childId()).orElse(null));

        return new Relations()
                .setRelationType(relationTypeService.findById(dto.relationTypeId()).orElse(null))
                .setPk(rk);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody RelationUpdateDto dto){
        relationService.create(mapDtoToDomain(dto));
    }

    private Relations mapDtoToDomain(RelationUpdateDto dto){
        RelationsKey rk = new RelationsKey();
        rk.setParent(citizensService.findById(dto.parent()).orElse(null));
        rk.setChild(citizensService.findById(dto.child()).orElse(null));

        return new Relations()
                .setRelationType(relationTypeService.findById(dto.relationType()).get())
                .setPk(rk);
    }

    @GetMapping("/getDto")
    public RelationDto getDto(){
        return new RelationDto(1,1,1);
    }


}
