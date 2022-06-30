package com.duth.engapp.DTO;

import com.duth.engapp.entity.SetsOfQuestion;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SetsOfQuestionDTO {
    private Integer id;
    @NotNull
    @NotBlank
    private String name;
    public SetsOfQuestionDTO(SetsOfQuestion setsOfQuestion) {
        this.id = setsOfQuestion.getId();
        this.name = setsOfQuestion.getName();
    }

    public SetsOfQuestionDTO(String name) {
        this.name = name;
    }

    public SetsOfQuestionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SetsOfQuestion setsOfQuestion()
    {
        SetsOfQuestion setsOfQuestion = new SetsOfQuestion();
        if(id != null)
            setsOfQuestion.setId(this.getId());
        setsOfQuestion.setName(this.getName());
        return setsOfQuestion;
    }
}