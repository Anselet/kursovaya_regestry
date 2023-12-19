package regestry.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.PassportRepository;
import regestry.entities.Passport;
import regestry.service.PassportService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository){

        this.passportRepository = passportRepository;
    }
    @Transactional
    @Override
    public void create(Passport entity) {

        passportRepository.save(entity);
    }
    @Override
    public Collection<Passport> findAllByNumber(Integer number) {
        return  (Collection<Passport>) Optional.ofNullable(number).
                map(passportRepository::findByNumber)
                .orElse((List<Passport>) passportRepository.findAll());
    }
    @Override
    public Optional<Passport> findById(Integer id) {

        return passportRepository.findById(id);
    }

}
