package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.MouMoa;
import com.kyu.springbackend.services.moumoa.MouMoaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mou-moas")
public class MouMoaController {

    private final MouMoaService service;

    public MouMoaController(MouMoaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MouMoa> getAllMouMoa() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MouMoa getMouMoaById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public MouMoa postNewMouMoa(@RequestBody MouMoa newMouMoa) {
        return service.save(newMouMoa);
    }

    @PutMapping("/{id}")
    public MouMoa putNewMouMoa(@PathVariable String id, @RequestBody MouMoa newMouMoa) {
        return service.update(id, newMouMoa);
    }

    @DeleteMapping("/{id}")
    public void deleteMouMoa(@PathVariable String id) {
        service.deleteById(id);
    }
}
