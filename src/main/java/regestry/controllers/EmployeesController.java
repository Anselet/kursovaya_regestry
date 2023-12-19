package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ContactsDto;
import regestry.controllers.dto.EmployeesDto;
import regestry.controllers.dto.LoginDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Contacts;
import regestry.entities.Employees;
import regestry.entities.Post;
import regestry.service.CitizensService;
import regestry.service.ContactsService;
import regestry.service.EmployeesService;
import regestry.service.PostService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private  final CitizensService citizensService;
    private  final PostService postService;
    private final EmployeesService employeesService;
    @Autowired
    public EmployeesController(EmployeesService employeesService, CitizensService citizensService, PostService postService){
        this.employeesService = employeesService;
        this.citizensService = citizensService;
        this.postService = postService;
    }
    @GetMapping("/findAll")
    public Collection<Employees> findAll() {
        return employeesService.findAll();
    }
    @GetMapping("/findById")
    public Optional<Employees> findById(@RequestParam(value = "id", required = false) Integer id) {
        return employeesService.findById(id);
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public Boolean login(@RequestBody LoginDto credentials) {
        return employeesService.tryLogin(credentials.login(), credentials.password());
    }
    @PostMapping("/create")
    public void create(@RequestBody EmployeesDto entity) {
        employeesService.create(mapDtoToDomain(entity));
    }
    private Employees mapDtoToDomain(EmployeesDto entity) {
        return new Employees()
                .setCitizen(citizensService.findById(entity.citizens_id()).get())
                .setPost(postService.findById(entity.post_id()).get());
    }
    @GetMapping("/getLogin")
    @CrossOrigin(origins = "*")
    public Optional<Employees> findByLogin(@RequestParam String login){
        return employeesService.findByLogin(login);
    }

    @GetMapping("/getDto")
    public EmployeesDto getDto(){
        return new EmployeesDto(1, 1);
    }
}
