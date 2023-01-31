package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.ResearchAward;
import com.kyu.springbackend.services.researchAward.ResearchAwardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/research-awards")
public class ResearchAwardController {

    private final ResearchAwardService service;

    public ResearchAwardController(ResearchAwardService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResearchAward> getAllResearchAward() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResearchAward getResearchAwardById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResearchAward postNewResearchAward(@RequestBody ResearchAward newResearchAward) {
        return service.save(newResearchAward);
    }

    @PutMapping("/{id}")
    public ResearchAward putNewResearchAward(@PathVariable String id, @RequestBody ResearchAward newResearchAward) {
        return service.update(id, newResearchAward);
    }

    @DeleteMapping("/{id}")
    public void deleteResearchAward(@PathVariable String id) {
        service.deleteById(id);

    }

}