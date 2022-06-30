package com.duth.engapp.payload;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Role;
import com.duth.engapp.entity.Score;
import com.duth.engapp.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String firstname;

    private String lastname;

    private Boolean active;

    private String avatar;

    private Role roleid;

    private Set<Score> scores = new LinkedHashSet<>();

    private Set<Dictionary> dictionaries = new LinkedHashSet<>();
    public UserResponse(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.active = user.getActive();
        this.avatar = user.getAvatar();
        this.roleid = user.getRoleid();
        this.setScores(user.getScores());
        this.setDictionaries(user.getDictionaries());
    }
}