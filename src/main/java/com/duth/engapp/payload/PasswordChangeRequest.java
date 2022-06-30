package com.duth.engapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class PasswordChangeRequest {
    @NotNull
    @NotBlank
    String password;
}
