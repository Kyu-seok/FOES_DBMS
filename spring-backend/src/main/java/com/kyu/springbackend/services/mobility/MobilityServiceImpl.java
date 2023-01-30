package com.kyu.springbackend.services.mobility;

import com.kyu.springbackend.model.Mobility;
import com.kyu.springbackend.repositories.MobilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobilityServiceImpl implements MobilityService {

    private final MobilityRepository repository;

    public MobilityServiceImpl(MobilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mobility> findAll() {
        return repository.findAll();
    }

    @Override
    public Mobility findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public Mobility save(Mobility object) {
        return repository.save(object);
    }

    @Override
    public Mobility update(String id, Mobility newMobility) {
        return repository.findById(id)
                .map(mobility -> {
                    mobility.update(newMobility);
                    return repository.save(mobility);
                })
                .orElseGet(() -> {
                    newMobility.setId(id);
                    return repository.save(newMobility);
                });
    }

    @Override
    public void delete(Mobility object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
