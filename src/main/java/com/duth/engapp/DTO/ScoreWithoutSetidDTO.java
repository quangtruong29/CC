package com.duth.engapp.DTO;

import com.duth.engapp.entity.Score;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ScoreWithoutSetidDTO implements Comparable<ScoreWithoutSetidDTO>{
    @JsonIgnore
    private Long id;
    private String userid;
    private String score;
    public ScoreWithoutSetidDTO(Score score) {
        this.setId(score.getId());
        this.setUserid(String.valueOf(score.getUserid().getId()));
        this.setScore(String.valueOf(score.getScore()));
    }
    @Override
    public int compareTo(ScoreWithoutSetidDTO o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        }
        return 0;
    }
}
