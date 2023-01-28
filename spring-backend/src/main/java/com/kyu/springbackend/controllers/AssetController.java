package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.services.asset.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Asset> getAllAsset() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Asset postNewAsset(@RequestBody Asset newAsset) {
        return service.save(newAsset);
    }

    @PutMapping("/{id}")
    public Asset putNewAsset(@PathVariable String id, @RequestBody Asset newAsset) {
        return service.update(id, newAsset);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable String id) {
        service.deleteById(id);
    }
}
