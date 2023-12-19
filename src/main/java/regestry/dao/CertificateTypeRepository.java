package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import regestry.entities.CertificateType;

@Repository
public interface CertificateTypeRepository extends CrudRepository<CertificateType, Integer> {
    Iterable<CertificateType> findAllByNameContainingIgnoreCase(String name);
}
