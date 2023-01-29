package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.KtpUsr;
import com.kyu.springbackend.services.ktpusr.KtpUsrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ktp-usrs")
public class KtpUsrController {

    private final KtpUsrService service;

    public KtpUsrController(KtpUsrService service) {
        this.service = service;
    }

    @GetMapping
    public List<KtpUsr> getAllKtpUsr() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KtpUsr getKtpUsrById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public KtpUsr postNewKtpUsr(@RequestBody KtpUsr newKtpUsr) {
        return service.save(newKtpUsr);
    }

    @PutMapping("/{id}")
    public KtpUsr putNewKtpUsr(@PathVariable String id, @RequestBody KtpUsr newKtpUsr) {
        return service.update(id, newKtpUsr);
    }

    @DeleteMapping("/{id}")
    public void deleteKtpUsr(@PathVariable String id) {
        service.deleteById(id);
    }
}
