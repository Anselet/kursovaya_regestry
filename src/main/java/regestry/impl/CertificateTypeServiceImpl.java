package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.CertificateTypeRepository;
import regestry.entities.ApplicationStatus;
import regestry.entities.CertificateType;
import regestry.service.CertificateTypeService;

import java.util.Collection;
import java.util.Optional;

@Service
public class CertificateTypeServiceImpl  implements CertificateTypeService {
    private final CertificateTypeRepository certificateTypeRepository;
    @Autowired
    public CertificateTypeServiceImpl(CertificateTypeRepository certificateTypeRepository) {
        this.certificateTypeRepository = certificateTypeRepository;
    }
    @Transactional
    @Override
    public void create(CertificateType entity){
        certificateTypeRepository.save(entity);
    }
    @Transactional
    @Override
    public Collection<CertificateType> findAllByName(String name){
        return (Collection<CertificateType>) Optional.ofNullable(name)
                .map(certificateTypeRepository::findAllByNameContainingIgnoreCase)
                .orElse(certificateTypeRepository.findAll());
    }

    @Override
    public Optional<CertificateType> findById(Integer id) {
        return certificateTypeRepository.findById(id);
    }

}
