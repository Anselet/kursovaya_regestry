package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.ActsRepository;
import regestry.entities.Acts;
import regestry.entities.Citizens;
import regestry.service.ActsService;

import java.util.Collection;
import java.util.Optional;

@Service
public class ActsServiceImpl implements ActsService {
    private final ActsRepository actsRepository;
    @Autowired
    public ActsServiceImpl(ActsRepository actsRepository){
        this.actsRepository = actsRepository;
    }
    @Transactional
    @Override
    public void create(Acts entity) {

        actsRepository.save(entity);
    }
    @Override
    public Optional<Acts> findById(Integer id) {

        return Optional.ofNullable(actsRepository.findByRegNumber(id));
    }
    @Override
    public Collection<Acts> findAll(){
        return (Collection<Acts>) actsRepository.findAll();
    }
}
