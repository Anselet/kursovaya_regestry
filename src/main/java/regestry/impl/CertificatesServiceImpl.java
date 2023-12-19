package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.CertificatesRepository;
import regestry.entities.Certificates;
import regestry.entities.Passport;
import regestry.service.CertificatesService;

import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Optional;

@Service
public class CertificatesServiceImpl implements CertificatesService {
    private final CertificatesRepository certificatesRepository;
    @Autowired
    public CertificatesServiceImpl(CertificatesRepository certificatesRepository){
        this.certificatesRepository = certificatesRepository;
    }

    @Transactional
    @Override
    public void create(Certificates entity) {
        certificatesRepository.save(entity);

    }

    @Override
    public Collection<Certificates> findAll() {
        return (Collection<Certificates>) certificatesRepository.findAll();
    }

    @Override
    public Collection<Certificates> findByCertificateType_Name(String name) {
        return (Collection<Certificates>) certificatesRepository.findByCertificateType_NameIgnoreCase(name);
    }

    @Override
    public Optional<Certificates> findById(Integer id) {
        return certificatesRepository.findById(id);
    }




}
