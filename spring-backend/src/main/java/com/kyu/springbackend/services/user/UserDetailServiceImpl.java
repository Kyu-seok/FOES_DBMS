package com.kyu.springbackend.services.user;

import com.kyu.springbackend.model.user.Authority;
import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.repositories.UserDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService, UserService {

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

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public User save(User object) {
        return repository.save(object);
    }

    @Override
    public User update(String id, User newUser) {
        return repository.findById(id)
                .map(user -> {
                    user.update(newUser);
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @Override
    public void delete(User object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
