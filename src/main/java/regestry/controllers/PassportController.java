package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.CitizenDto;
import regestry.controllers.dto.PassportDto;
import regestry.controllers.dto.PassportUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Citizens;
import regestry.entities.Passport;
import regestry.service.CitizensService;
import regestry.service.PassportService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;
    private final CitizensService citizensService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Autowired
    public PassportController(PassportService passportService, CitizensService citizensService){
        this.passportService = passportService;
        this.citizensService = citizensService;
    }
    @GetMapping("/findAll")
    public Collection<Passport> findAll(@RequestParam(value = "number", required = false)Integer number){
        return passportService.findAllByNumber(number);
    }
    @GetMapping("/findById")
    public Optional<Passport> findById(@RequestParam(value = "id", required = false) Integer id){
        return passportService.findById(id);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody PassportDto entity) {

        passportService.create(mapDtoToDomain(entity));
    }
    private Passport mapDtoToDomain(PassportDto entity){
        return new Passport()
                .setNumber(entity.number())
                .setSubnumber(entity.subnumber())
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setDivision_code(entity.division_code())
                .setDivision_name(entity.division_name())
                .setCitizen(citizensService.findById(entity.citizens_id()).get());
    }

    @GetMapping("/getDto")
    public PassportDto getDto(){
        return new PassportDto(1,
                1,
                LocalDate.now().toString(),
                "division_code",
                "division_name",
                1);
    }


    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody PassportUpdateDto entity) {

        passportService.create(mapDtoToDomain(entity));
    }
    private Passport mapDtoToDomain(PassportUpdateDto entity){
        return new Passport()
                .setNumber(entity.number())
                .setSubnumber(entity.subnumber())
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setDivision_code(entity.division_code())
                .setDivision_name(entity.division_name())
                .setCitizen(citizensService.findById(entity.citizen()).get());
    }
}
