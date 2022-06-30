package com.duth.engapp.payload;

import lombok.Data;

@Data
public class RegisterResponse {
    int code;
    String message;
    public RegisterResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
