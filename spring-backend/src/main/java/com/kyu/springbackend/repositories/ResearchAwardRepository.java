package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.ResearchAward;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResearchAwardRepository extends MongoRepository<ResearchAward, String> {
}
