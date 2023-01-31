package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.MouMoa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MouMoaRepository extends MongoRepository<MouMoa, String> {
}
