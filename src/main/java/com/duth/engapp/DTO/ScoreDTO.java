package com.duth.engapp.DTO;

import com.duth.engapp.entity.Score;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
public class ScoreDTO {
    @JsonIgnore
    private Long id;
    private String userid;
    @NotNull
    @Pattern(regexp = "[0-9]+", message = "invalid set id")
    private String setid;
    @NotNull
    @Pattern(regexp = "[0-9.]+", message = "invalid score")
    private String score;

    public ScoreDTO() {
    }

    public ScoreDTO(Score score) {
        this.setId(score.getId());
        this.setUserid(String.valueOf(score.getUserid().getId()));
        this.setSetid(String.valueOf(score.getSetid().getId()));
        this.setScore(String.valueOf(score.getScore()));
    }
}
