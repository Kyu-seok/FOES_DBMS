package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.KtpUsr;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KtpUsrRepository extends MongoRepository<KtpUsr, String> {
}
