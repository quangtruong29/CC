package com.duth.engapp.DTO;

import com.duth.engapp.entity.SetsOfQuestion;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class SetsOfQuestionUserDTO {
    private Integer id;
    private String name;
    private String score;

    public SetsOfQuestionUserDTO(SetsOfQuestion setsOfQuestion) {
        this.id = setsOfQuestion.getId();
        this.name = setsOfQuestion.getName();
        List<ScoreWithoutSetidDTO> scores = setsOfQuestion.getScores().stream().map(ScoreWithoutSetidDTO::new).toList();
        this.score = scores.size() <= 0 ? null : Collections.max(scores).getScore();
    }
}