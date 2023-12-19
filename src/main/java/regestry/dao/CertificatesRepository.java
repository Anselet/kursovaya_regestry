package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import regestry.entities.Certificates;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificatesRepository extends CrudRepository<Certificates, Integer> {

    @Override
    Optional<Certificates> findById(Integer id);

    List<Certificates> findByCertificateType_NameIgnoreCase(@NonNull String name);


}
