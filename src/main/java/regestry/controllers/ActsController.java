package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActsDto;
import regestry.controllers.dto.ActsUpdateDto;
import regestry.controllers.dto.EmployeesDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Acts;
import regestry.entities.Employees;
import regestry.service.ActTypeService;
import regestry.service.ActsService;
import regestry.service.EmployeesService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/acts")
public class ActsController {
    private final EmployeesService employeesService;
    private final ActsService actsService;
    private final ActTypeService actTypeService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Autowired
    public ActsController(ActsService actsService, ActTypeService actTypeService, EmployeesService employeesService){
        this.actsService = actsService;
        this.actTypeService = actTypeService;
        this.employeesService = employeesService;
    }
    @GetMapping("/findAll")
    public Collection<Acts> findAll() {
        return actsService.findAll();
    }
    @GetMapping("/findById")
    public Optional<Acts> findById(@RequestParam(value = "id", required = false) Integer id) {
        return actsService.findById(id);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ActsDto entity) {

        actsService.create(mapDtoToDomain(entity));
    }
    private Acts mapDtoToDomain(ActsDto entity) {
        return new Acts()
                .setReg_company_name(entity.reg_company_name())
                .setDate_of_registration(LocalDate.parse(entity.date_of_registration(),formatter))
                .setActType(actTypeService.findById(entity.act_type_id()).get())
                .setEmployees(employeesService.findById(entity.employee_id()).get());
    }

    @GetMapping("/getDto")
    public ActsDto getDto(){
        return new ActsDto(LocalDate.now().toString(),
                "reg_company_name",
                1,
                1);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody ActsUpdateDto entity) {

        actsService.create(mapDtoToDomain(entity));
    }
    private Acts mapDtoToDomain(ActsUpdateDto entity) {
        return new Acts()
                .setReg_company_name(entity.reg_company_name())
                .setDate_of_registration(LocalDate.parse(entity.date_of_registration(),formatter))
                .setActType(actTypeService.findById(entity.actType()).get())
                .setEmployees(employeesService.findById(entity.employees()).get())
                .setRegNumber(entity.regNumber());
    }
}
