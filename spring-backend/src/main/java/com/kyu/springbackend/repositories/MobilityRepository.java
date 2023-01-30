package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.Asset;
import com.kyu.springbackend.model.Mobility;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MobilityRepository extends MongoRepository<Mobility, String> {
}
