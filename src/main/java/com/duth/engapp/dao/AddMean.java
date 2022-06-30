package com.duth.engapp.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddMean {
    @NotBlank
    @NotNull
    private String word;
    @NotBlank
    @NotNull
    private String mean;
    @NotBlank
    @NotNull
    private String type;
}
