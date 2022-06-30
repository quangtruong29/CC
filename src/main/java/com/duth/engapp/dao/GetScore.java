package com.duth.engapp.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class GetScore {
    @NotNull
    @Pattern(regexp = "[0-9]+", message = "invalid set id")
    @NotBlank
    String id;
}
