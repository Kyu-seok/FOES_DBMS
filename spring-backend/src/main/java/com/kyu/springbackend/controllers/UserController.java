package com.kyu.springbackend.controllers;

import com.kyu.springbackend.model.user.Authority;
import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.services.user.UserDetailServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDetailServiceImpl service;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserDetailServiceImpl service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    private Authority createAuthority(String roleCode, String roleDescription) {
        Authority authority = new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }

    @GetMapping
    public List<User> getAllUser() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public User postNewUser(@RequestBody User newUser) {

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(createAuthority("USER", "User role"));

        newUser.setAuthorities(authorityList);
        newUser.setEnabled(true);

        return service.save(newUser);
    }

    @PutMapping("/{id}")
    public User putNewUser(@PathVariable String id, @RequestBody User newUser) {
        return service.update(id, newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        service.deleteById(id);

    }

}