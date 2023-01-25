package com.kyu.springbackend.model.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
@Data
public class Authority implements GrantedAuthority {

    @Id
    private String id;
    private String roleCode;
    private String roleDescription;

    @Override
    public String getAuthority() {
        return roleCode;
    }
}
