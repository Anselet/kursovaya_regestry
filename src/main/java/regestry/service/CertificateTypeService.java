package regestry.service;

import regestry.entities.CertificateType;

import java.util.Collection;
import java.util.Optional;

public interface CertificateTypeService {
    void create(CertificateType entity);
    Collection<CertificateType> findAllByName(String name);
    Optional<CertificateType> findById(Integer id);
}
