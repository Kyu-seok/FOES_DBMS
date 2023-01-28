package com.kyu.springbackend.services.asset;

import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.repositories.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {
    AssetServiceImpl service;

    @Mock
    AssetRepository repository;

    @BeforeEach
    void setUp() {
        service = new AssetServiceImpl(repository);
    }

    @Test
    void getAllAsset() {
        Asset asset1 = new Asset();
        asset1.setId("1");
        Asset asset2 = new Asset();
        asset2.setId("2");
        List<Asset> assets = new LinkedList<>();
        assets.add(asset1);
        assets.add(asset2);

        when(repository.findAll()).thenReturn(assets);
        List<Asset> returnedAssets = service.findAll();

        assertEquals(assets, returnedAssets);
        verify(repository, times(1)).findAll();
        verify(repository, never()).findById(anyString());

    }

    @Test
    void getAssetByIdTest() {
        Asset asset = new Asset();
        asset.setId("1");
        Optional<Asset> assetOptional = Optional.of(asset);

        when(repository.findById(anyString())).thenReturn(assetOptional);
        Asset assetReturned = service.findById("1");

        assertEquals(assetOptional.get(), assetReturned);
        verify(repository, times(1)).findById(anyString());
        verify(repository, never()).findAll();
    }

    @Test
    void saveAsset() {
        Asset asset = new Asset();
        asset.setId("1");

        when(repository.save(any())).thenReturn(asset);
        Asset savedAsset = service.save(asset);

        assertEquals(asset, savedAsset);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateExistingAsset() {
        Asset asset = new Asset();
        asset.setId("1");
        asset.setItem("Chair");
        Optional<Asset> assetOptional = Optional.of(asset);

        Asset newUpdateAsset = new Asset();
        newUpdateAsset.setId("1");
        newUpdateAsset.setItem("Dest");

        when(repository.findById(anyString())).thenReturn(assetOptional);
        when(repository.save(any())).thenReturn(newUpdateAsset);
        Asset returnedAsset = service.update(asset.getId(), newUpdateAsset);

        assertEquals(newUpdateAsset, returnedAsset);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateEmptyAsset() {
        Asset newUpdateAsset = new Asset();
        newUpdateAsset.setId("1");
        newUpdateAsset.setItem("Desk");

        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(newUpdateAsset);
        Asset returnedAsset = service.update(newUpdateAsset.getId(), newUpdateAsset);

        assertEquals(newUpdateAsset, returnedAsset);
        verify(repository, times(1)).findById(anyString());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        Asset asset = new Asset();
        asset.setId("1");
        service.deleteById(asset.getId());

        verify(repository, times(1)).deleteById(anyString());
    }

}