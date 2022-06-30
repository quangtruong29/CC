package com.duth.engapp.DTO;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Meaning;
import com.duth.engapp.entity.TypesOfWord;
import lombok.Data;

@Data
public class MeaningDTO {
    private Long id;
    private String mean;
    private TypesOfWordDTO typeid;
    public MeaningDTO(Dictionary dictionary, String mean, TypesOfWord typesOfWord)
    {
        this.mean = mean;
        this.typeid = new TypesOfWordDTO(typesOfWord);
    }
    public MeaningDTO(Meaning meaning)
    {
        this.id = meaning.getId();
        this.mean = meaning.getMean();
        this.typeid = new TypesOfWordDTO(meaning.getTypeid());
    }
}