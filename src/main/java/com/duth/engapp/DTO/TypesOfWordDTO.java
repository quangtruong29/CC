package com.duth.engapp.DTO;

import com.duth.engapp.entity.TypesOfWord;
import lombok.Data;

@Data
public class TypesOfWordDTO {
    private Integer id;
    private String shortcut;
    private String name;
    public TypesOfWordDTO(TypesOfWord type) {
        this.id = type.getId();
        this.shortcut = type.getShortcut();
        this.name = type.getName();
    }
}