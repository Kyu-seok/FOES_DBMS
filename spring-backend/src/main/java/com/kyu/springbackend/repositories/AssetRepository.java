package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset, String> {
}
