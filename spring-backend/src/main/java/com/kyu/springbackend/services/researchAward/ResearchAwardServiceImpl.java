package com.kyu.springbackend.services.researchAward;

import com.kyu.springbackend.model.ResearchAward;
import com.kyu.springbackend.repositories.ResearchAwardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearchAwardServiceImpl implements ResearchAwardService {

    private final ResearchAwardRepository repository;

    public ResearchAwardServiceImpl(ResearchAwardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ResearchAward> findAll() {
        return repository.findAll();
    }

    @Override
    public ResearchAward findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public ResearchAward save(ResearchAward object) {
        return repository.save(object);
    }

    @Override
    public ResearchAward update(String id, ResearchAward newResearchAward) {
        return repository.findById(id)
                .map(researchAward -> {
                    researchAward.update(newResearchAward);
                    return repository.save(researchAward);
                })
                .orElseGet(() -> {
                    newResearchAward.setId(id);
                    return repository.save(newResearchAward);
                });
    }

    @Override
    public void delete(ResearchAward object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}