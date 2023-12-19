package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ApplicationUpdateDto;
import regestry.controllers.dto.ContactsDto;
import regestry.controllers.dto.ContactsUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Applications;
import regestry.entities.Citizens;
import regestry.entities.Contacts;
import regestry.entities.Registration;
import regestry.service.CitizensService;
import regestry.service.ContactsService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactsService contactsService;
    private  final CitizensService citizensService;
    @Autowired
    public ContactsController(ContactsService contactsService, CitizensService citizensService){
        this.contactsService = contactsService;
        this.citizensService = citizensService;
    }
    @GetMapping("/findAll")
    public Collection<Contacts> findAll() {
        return contactsService.findAll();
    }
    @GetMapping("/findById")
    public Optional<Contacts> findById(@RequestParam(value = "id", required = false) Integer id) {
        return contactsService.findById(id);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ContactsDto entity) {

        contactsService.create(mapDtoToDomain(entity));
    }
    private Contacts mapDtoToDomain(ContactsDto entity) {
        return new Contacts()
                .setPhone_number(entity.phone_number())
                .setEmail(entity.email())
                .setReal_address(entity.real_address())
                .setCitizen(citizensService.findById(entity.citizens_id()).get());
    }

    @GetMapping("/getDto")
    public ContactsDto getDto(){
        return new ContactsDto(1l,
                "email",
                "real_address",
                1);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody ContactsUpdateDto entity) {
        contactsService.create(mapDtoToDomain(entity));
    }
    private Contacts mapDtoToDomain(ContactsUpdateDto entity) {
        return new Contacts()
                .setPhone_number(entity.phone_number())
                .setEmail(entity.email())
                .setReal_address(entity.real_address())
                .setCitizen(citizensService.findById(entity.citizen()).get())
                .setId(entity.id());
    }
}
