package com.duth.engapp.DTO;

import com.duth.engapp.entity.SetsOfQuestion;
import lombok.Data;

@Data
public class SetOfQuestionUpdateDTO {
    private String name;
    public SetsOfQuestion setsOfQuestion()
    {
        SetsOfQuestion setsOfQuestion = new SetsOfQuestion();
        setsOfQuestion.setName(this.getName());
        return setsOfQuestion;
    }
}