package com.duth.engapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    @NotNull
    @NotBlank
    String firstname;
    @NotNull
    @NotBlank
    String lastname;
    String avatar;
}
