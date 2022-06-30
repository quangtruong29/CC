package com.duth.engapp.DTO;

import com.duth.engapp.entity.Dictionary;
import com.duth.engapp.entity.Meaning;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DictionaryDTO {
    private Long id;
    private String word;
    private String ipa;
    private List<MeaningDTO> meanings = new ArrayList<>();
    public DictionaryDTO(Dictionary dictionary) {
        this.id = dictionary.getId();
        this.word = dictionary.getWord();
        this.ipa = dictionary.getIpa();
        for (Meaning m : dictionary.getMeanings())
        {
            this.meanings.add(new MeaningDTO(m));
        }
    }
}