package regestry.service;

import org.springframework.stereotype.Service;
import regestry.entities.Certificates;

import java.util.Collection;
import java.util.Optional;


public interface CertificatesService {
    void create(Certificates entity);
    Collection<Certificates> findAll();
    Collection<Certificates> findByCertificateType_Name(String name);
    Optional<Certificates> findById(Integer id);
}
