package com.kyu.springbackend.services.user;

import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.repositories.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDetailsRepository repository;

    public UserDetailServiceImpl(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUserName(username);

        if (null == user) {
            throw new UsernameNotFoundException("User Not Found with userName " + username);
        }

        return user;
    }
}
