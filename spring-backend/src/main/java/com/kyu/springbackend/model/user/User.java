package com.kyu.springbackend.model.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean enabled;
    private List<Authority> authorities;

    public User() {

    }

    public User(String userName, String password, String firstName, String lastName, String email, String phoneNumber, boolean enabled, List<Authority> authorities) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void update(User obj) {
        this.id = obj.getId();
        this.userName = obj.getUsername();
        this.password = obj.getPassword();
        // this.createdAt = obj.getCreatedAt();
        // this.updateAt = obj.getUpdateAt();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.email = obj.getEmail();
        this.phoneNumber = obj.getPhoneNumber();
        this.enabled = obj.isEnabled();
    }
}
