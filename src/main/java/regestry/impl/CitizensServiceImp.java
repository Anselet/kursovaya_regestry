package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.CitizensRepository;
import regestry.entities.Citizens;
import regestry.entities.Registration;
import regestry.service.CitizensService;

import java.util.Collection;
import java.util.Optional;


@Service
public class CitizensServiceImp implements CitizensService {

    private  final CitizensRepository citizensRepository;

    @Autowired
    public CitizensServiceImp(CitizensRepository citizensRepository){
        this.citizensRepository = citizensRepository;
    }

    @Transactional
    @Override
    public void create(Citizens entity) {
        citizensRepository.save(entity);
    }

    @Override
    public Collection<Citizens> findAllByName(String name) {
        return  (Collection<Citizens>) Optional.ofNullable(name).
                map(citizensRepository::findAllByNameContainingIgnoreCase)
                .orElse(citizensRepository.findAll());
    }

    @Override
    public Optional<Citizens> findById(Integer id) {

        return Optional.ofNullable(citizensRepository.findById(id).orElse(null));
    }
}
