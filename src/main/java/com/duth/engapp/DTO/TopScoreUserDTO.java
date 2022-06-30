package com.duth.engapp.DTO;

import com.duth.engapp.model.UserModel;
import lombok.Data;

@Data
public class TopScoreUserDTO {
    private Long id;
    private String email;
    private String name;
    private String avatar;
    private double score;

    public TopScoreUserDTO(UserModel user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getFirstname() + " " + user.getLastname();
        this.avatar = user.getAvatar();
        this.score = user.getScore();
    }
}
