package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.ActTypeDto;
import regestry.controllers.dto.ActTypeUpdateDto;
import regestry.controllers.dto.CertificateTypeDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.ApplicationType;
import regestry.entities.CertificateType;
import regestry.service.CertificateTypeService;

import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/certtype")
public class CertificateTypeController {
    private final CertificateTypeService certificateTypeService;
    @Autowired
    public CertificateTypeController(CertificateTypeService certificateTypeService){
        this.certificateTypeService = certificateTypeService;
    }
    @GetMapping("/findAll")
    public Collection<CertificateType> findAll(@RequestParam(value = "name", required = false) String name) {
        return certificateTypeService.findAllByName(name);
    }
    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody CertificateTypeDto entity) {
        certificateTypeService.create(mapDtoToDomain(entity));
    }
    private CertificateType mapDtoToDomain(CertificateTypeDto entity){
        return new CertificateType()
                .setName(entity.name());
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "*")
    public void update(@RequestBody ActTypeUpdateDto entity) {
        certificateTypeService.create(mapDtoToDomain(entity));
    }
    private CertificateType mapDtoToDomain(ActTypeUpdateDto entity){
        return new CertificateType()
                .setName(entity.name())
                .setId(entity.id());
    }
    @GetMapping("/findByID")
    public Optional<CertificateType> findById(@RequestParam(value = "Id", required = false) Integer id) {
        return certificateTypeService.findById(id);
    }

    @GetMapping("/getDto")
    public CertificateTypeDto getDto(){
        return new CertificateTypeDto("name");
    }

}
