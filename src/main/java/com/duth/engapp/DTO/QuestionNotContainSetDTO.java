package com.duth.engapp.DTO;

import com.duth.engapp.entity.Question;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class QuestionNotContainSetDTO implements Serializable {
    private Long id;
    @NotNull
    @NotBlank
    private String questcontent;
    @NotNull
    @NotBlank
    private String a;
    @NotNull
    @NotBlank
    private String b;
    @NotNull
    @NotBlank
    private String c;
    @NotNull
    @NotBlank
    private String d;
    @NotNull
    @NotBlank
    private String answer;
    private String image;
    @NotNull
    private TypesOfQuestionDTO typeid;

    private int setid;

    public QuestionNotContainSetDTO(Question question) {
        this.id = question.getId();
        this.questcontent = question.getQuestcontent();
        this.a = question.getA();
        this.b = question.getB();
        this.c = question.getC();
        this.d = question.getD();
        this.answer = question.getAnswer();
        this.image = question.getImage();
        this.typeid =  new TypesOfQuestionDTO(question.getTypeid());
        this.setid = question.getSetid().getId();
    }
}