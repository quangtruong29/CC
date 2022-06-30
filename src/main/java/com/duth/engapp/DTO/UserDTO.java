package com.duth.engapp.DTO;

import com.duth.engapp.entity.Role;
import com.duth.engapp.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private Boolean active;
    private String avatar;
    private Role roleid;
    //private List<ScoreDTO> scores = new ArrayList<>();
    //private List<DictionaryDTO> dictionaries = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.active = user.getActive();
        this.avatar = user.getAvatar();
        this.roleid = user.getRoleid();
        //user.getScores().forEach(n->{this.scores.add(new ScoreDTO(n));});
        //user.getDictionaries().forEach(n->{this.dictionaries.add(new DictionaryDTO(n));});
    }
}
