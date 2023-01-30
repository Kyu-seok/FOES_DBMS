package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.Mobility;
import com.kyu.springbackend.services.mobility.MobilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mobilities")
public class MobilityController {

    private final MobilityService service;

    public MobilityController(MobilityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Mobility> getAllMobility() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mobility getMobilityById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mobility postNewMobility(@RequestBody Mobility newMobility) {
        return service.save(newMobility);
    }

    @PutMapping("/{id}")
    public Mobility putNewMobility(@PathVariable String id, @RequestBody Mobility newMobility) {
        return service.update(id, newMobility);
    }

    @DeleteMapping("/{id}")
    public void deleteMobility(@PathVariable String id) {
        service.deleteById(id);
    }
}
