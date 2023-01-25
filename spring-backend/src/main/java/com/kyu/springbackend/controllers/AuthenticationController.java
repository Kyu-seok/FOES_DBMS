package com.kyu.springbackend.controllers;

import com.kyu.springbackend.config.JwtTokenHelper;
import com.kyu.springbackend.model.user.User;
import com.kyu.springbackend.requests.AuthenticationRequest;
import com.kyu.springbackend.responds.LoginResponse;
import com.kyu.springbackend.responds.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user) {
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getFirstName());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setRoles(userObj.getAuthorities().toArray());

        return ResponseEntity.ok(userInfo);
    }
}
