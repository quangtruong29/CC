package com.duth.engapp.DTO;

import com.duth.engapp.entity.TypesOfQuestion;
import lombok.Data;

@Data
public class TypesOfQuestionDTO {
    private Integer id;
    private String name;

    public TypesOfQuestionDTO(TypesOfQuestion type) {
        this.id = type.getId();
        this.name = type.getName();
    }
}