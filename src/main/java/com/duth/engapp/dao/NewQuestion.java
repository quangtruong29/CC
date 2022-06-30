package com.duth.engapp.dao;

import com.duth.engapp.entity.Question;
import com.duth.engapp.entity.SetsOfQuestion;
import com.duth.engapp.entity.TypesOfQuestion;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class NewQuestion implements Serializable {
    private Long id;
    @NotNull
    private String questcontent;
    @NotNull
    private String a;
    @NotNull
    private String b;
    @NotNull
    private String c;
    @NotNull
    private String d;
    @NotNull
    private String answer;
    private String image;
    private int typeid;
    private int setid;
    public Question toQuestion()
    {
        return new Question(id, questcontent, a, b, c, d, answer, image, TypesOfQuestion.builder().id(typeid).build(), SetsOfQuestion.builder().id(setid).build());
    }

}