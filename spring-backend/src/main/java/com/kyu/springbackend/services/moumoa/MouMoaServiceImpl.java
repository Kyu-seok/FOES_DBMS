package com.kyu.springbackend.services.moumoa;

import com.kyu.springbackend.model.MouMoa;
import com.kyu.springbackend.repositories.MouMoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MouMoaServiceImpl implements MouMoaService{

    private final MouMoaRepository repository;

    public MouMoaServiceImpl(MouMoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MouMoa> findAll() {
        return repository.findAll();
    }

    @Override
    public MouMoa findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public MouMoa save(MouMoa object) {
        return repository.save(object);
    }

    @Override
    public MouMoa update(String id, MouMoa newMouMoa) {
        return repository.findById(id)
                .map(mouMoa -> {
                    mouMoa.update(newMouMoa);
                    return repository.save(mouMoa);
                })
                .orElseGet(() -> {
                    newMouMoa.setId(id);
                    return repository.save(newMouMoa);
                });
    }

    @Override
    public void delete(MouMoa object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
