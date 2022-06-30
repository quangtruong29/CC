package com.duth.engapp.DTO;

import com.duth.engapp.entity.SetsOfQuestion;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SetsOfQuestionCreateDTO {
    @NotNull
    private Integer id;
    @NotNull
    @NotBlank
    private String name;

    public SetsOfQuestion setsOfQuestion()
    {
        SetsOfQuestion setsOfQuestion = new SetsOfQuestion();
        setsOfQuestion.setId(this.getId());
        setsOfQuestion.setName(this.getName());
        return setsOfQuestion;
    }
}