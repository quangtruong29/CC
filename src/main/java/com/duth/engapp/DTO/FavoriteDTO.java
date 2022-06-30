package com.duth.engapp.DTO;

import com.duth.engapp.entity.Favorite;
import lombok.Data;

@Data
public class FavoriteDTO {
//    @JsonIgnore
//    private Long id;
    private DictionaryDTO dictionary;

    public FavoriteDTO(Favorite favorite)
    {
//        this.id = favorite.getId();
        this.dictionary = new DictionaryDTO(favorite.getDictionaryid());
    }
}
