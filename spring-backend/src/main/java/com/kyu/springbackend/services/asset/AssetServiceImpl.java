package com.kyu.springbackend.services.asset;

import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.repositories.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Asset> findAll() {
        return repository.findAll();
    }

    @Override
    public Asset findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public Asset save(Asset object) {
        return repository.save(object);
    }

    @Override
    public Asset update(String id, Asset newAsset) {
        return repository.findById(id)
                .map(asset -> {
                    asset.update(newAsset);
                    return repository.save(asset);
                })
                .orElseGet(() -> {
                    newAsset.setId(id);
                    return repository.save(newAsset);
                });
    }

    @Override
    public void delete(Asset object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
