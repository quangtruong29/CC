package com.duth.engapp.model;

import com.duth.engapp.entity.Role;
import com.duth.engapp.entity.User;
import lombok.Data;

@Data
public class UserModel implements Comparable<UserModel> {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private Boolean active;
    private String avatar;
    private Role roleid;
    private double score;

    public UserModel(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.active = user.getActive();
        this.avatar = user.getAvatar();
        this.roleid = user.getRoleid();
        this.score = 0;
        user.getScores().forEach(n->{
            score += n.getScore();
        });
    }

    @Override
    public int compareTo(UserModel o) {
        return Double.compare(o.getScore(),this.score);
    }
}
