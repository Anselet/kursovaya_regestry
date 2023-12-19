package regestry.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.*;
import regestry.entities.Acts;
import regestry.entities.Citizens;
import regestry.entities.Post;
import regestry.service.CitizensService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/citizens")
public class CitizensController {
    private final CitizensService citizensService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    public CitizensController(CitizensService citizensService){

        this.citizensService = citizensService;
    }

    @GetMapping("/findAll")
    public Collection<Citizens> findAll(@RequestParam(value = "name", required = false)String name){
        return citizensService.findAllByName(name);
    }

    @GetMapping("/findById")
    public Optional<Citizens> findById(@RequestParam(value = "id", required = false) Integer id){
        return citizensService.findById(id);
    }


    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody CitizenDto entity) {

        citizensService.create(mapDtoToDomain(entity));
    }

    private Citizens mapDtoToDomain(CitizenDto entity){
        return new Citizens()
                .setName(entity.name())
                .setSurname(entity.surname())
                .setBirthDay(LocalDate.parse(entity.birthDay(),formatter))
                .setPatronymic(entity.patronymic())
                .setBirthPlace(entity.birthPlace());
    }

    @GetMapping("/getDto")
    public CitizenDto getDto(){
        return new CitizenDto("name",
                "surname",
                "patronymic",
                LocalDate.now().toString(),
                "birthPlace");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/update")
    public void update(@RequestBody CitizenUpdateDto entity) {

        citizensService.create(mapDtoToDomain(entity));
    }
    private Citizens mapDtoToDomain(CitizenUpdateDto entity) {

        return new Citizens()
                .setName(entity.name())
                .setSurname(entity.surname())
                .setPatronymic(entity.patronymic())
                .setBirthPlace(entity.birthPlace())
                .setBirthDay(LocalDate.parse(entity.birthDay(),formatter))
                .setId(entity.id());
    }

    @PostMapping("/error")
    public String update(@RequestBody String error) {

        return error;
    }
}
