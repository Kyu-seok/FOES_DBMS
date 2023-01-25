package com.kyu.springbackend.repositories;

import com.kyu.springbackend.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);

}
