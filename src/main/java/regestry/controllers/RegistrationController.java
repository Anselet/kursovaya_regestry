package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.PassportDto;
import regestry.controllers.dto.RegistrationDto;
import regestry.controllers.dto.RegistrationUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Passport;
import regestry.entities.Registration;
import regestry.service.PassportService;
import regestry.service.RegistrationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final PassportService passportService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    public RegistrationController(RegistrationService registrationService, PassportService passportService) {
        this.registrationService = registrationService;
        this.passportService = passportService;
    }

    @GetMapping("/findAll")
    public Collection<Registration> findAll() {
        return registrationService.findAll();
    }

    @GetMapping("/findById")
    public Optional<Registration> findById(@RequestParam(value = "id", required = false) Integer id) {
        return registrationService.findById(id);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody RegistrationDto entity) {

        registrationService.create(mapDtoToDomain(entity));
    }

    private Registration mapDtoToDomain(RegistrationDto entity) {
        return new Registration()
                .setDivision_name(entity.division_name())
                .setDivision_code(entity.division_code())
                .setPassport(passportService.findById(entity.passport_id()).get())
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setFlat_number(entity.flat_number())
                .setHouse_number(entity.house_number())
                .setState(entity.state())
                .setRegions(entity.regions())
                .setSubjects(entity.subjects())
                .setDistricts(entity.districts())
                .setPopulation_center(entity.population_center())
                .setStreets(entity.streets())
                .setCancellation_date(LocalDate.parse(entity.cancellation_date(),formatter));
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody RegistrationUpdateDto entity) {

        registrationService.create(mapDtoToDomain(entity));
    }

    private Registration mapDtoToDomain(RegistrationUpdateDto entity) {
        return new Registration()
                .setDivision_name(entity.division_name())
                .setDivision_code(entity.division_code())
                .setPassport(passportService.findById(entity.passport()).get())
                .setDate(LocalDate.parse(entity.date(),formatter))
                .setFlat_number(entity.flat_number())
                .setHouse_number(entity.house_number())
                .setState(entity.state())
                .setRegions(entity.regions())
                .setSubjects(entity.subjects())
                .setDistricts(entity.districts())
                .setPopulation_center(entity.population_center())
                .setStreets(entity.streets())
                .setCancellation_date(LocalDate.parse(entity.cancellation_date(),formatter))
                .setId(entity.id());
    }

    @GetMapping("/getDto")
    public RegistrationDto getDto(){
        return new RegistrationDto(
                LocalDate.now().toString(),
                "devision_code",
                "devision_name",
                "house_number",
                1,
                1,
                "state",
                "regions",
                "subject",
                "district",
                "population_center",
                "street",
                null
        );
    }
}
