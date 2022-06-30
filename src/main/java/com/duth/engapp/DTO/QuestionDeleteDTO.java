package com.duth.engapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class QuestionDeleteDTO implements Serializable {
    @NotNull
    private Long id;
}