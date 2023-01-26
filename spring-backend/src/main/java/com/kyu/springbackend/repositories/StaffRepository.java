package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepository extends MongoRepository<Staff, String> {
}
