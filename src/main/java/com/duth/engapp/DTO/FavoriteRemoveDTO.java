package com.duth.engapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class FavoriteRemoveDTO {
    @NotNull
    @Pattern(regexp = "[0-9]+", message = "invalid set id")
    @NotBlank
    String id;
}
