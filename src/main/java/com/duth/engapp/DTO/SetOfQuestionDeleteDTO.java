package com.duth.engapp.DTO;

import com.duth.engapp.entity.SetsOfQuestion;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SetOfQuestionDeleteDTO {
    @NotNull
    private Integer id;

    public SetsOfQuestion setsOfQuestion()
    {
        SetsOfQuestion setsOfQuestion = new SetsOfQuestion();
        setsOfQuestion.setId(id);
        return setsOfQuestion;
    }
}