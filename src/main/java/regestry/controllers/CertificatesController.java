package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActsDto;
import regestry.controllers.dto.CertificatesDto;
import regestry.controllers.dto.CertificatesUpdateDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Acts;
import regestry.entities.CertificateType;
import regestry.entities.Certificates;
import regestry.service.ActsService;
import regestry.service.CertificateTypeService;
import regestry.service.CertificatesService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/certificates")
public class CertificatesController {
    private final ActsService actsService;
    private final CertificatesService certificatesService;
    private final CertificateTypeService certificateTypeService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    public CertificatesController(ActsService actsService, CertificatesService certificatesService, CertificateTypeService certificateTypeService){
        this.actsService = actsService;
        this.certificatesService = certificatesService;
        this.certificateTypeService = certificateTypeService;
    }
    @GetMapping("/findAll")
    public Collection<Certificates> findAll() {
        return certificatesService.findAll();
    }
    @GetMapping("/findById")
    public Optional<Certificates> findById(@RequestParam(value = "id", required = false) Integer id) {
        return certificatesService.findById(id);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody CertificatesDto entity) {

        certificatesService.create(mapDtoToDomain(entity));
    }
    private Certificates mapDtoToDomain(CertificatesDto entity) {
        return new Certificates()
                .setDate_of_issue(LocalDate.parse(entity.date_of_issue(),formatter))
                .setSubnumber(entity.subnumber())
                .setNumber(entity.number())
                .setActs(actsService.findById(entity.reg_number()).get())
                .setCertificateType(certificateTypeService.findById(entity.cert_type_id()).get());
    }

    @GetMapping("/getDto")
    public CertificatesDto getDto(){
        return new CertificatesDto(LocalDate.now().toString(),
                1,
                1,
                1,
                1);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody CertificatesUpdateDto entity) {

        certificatesService.create(mapDtoToDomain(entity));
    }
    private Certificates mapDtoToDomain(CertificatesUpdateDto entity) {
        return new Certificates()
                .setDate_of_issue(LocalDate.parse(entity.date_of_issue(),formatter))
                .setSubnumber(entity.subnumber())
                .setNumber(entity.number())
                .setActs(actsService.findById(entity.acts()).get())
                .setCertificateType(certificateTypeService.findById(entity.certificateType()).get())
                .setId(entity.id());
    }
}
