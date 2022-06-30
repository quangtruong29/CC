package com.duth.engapp.payload;

import com.duth.engapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateResponse {
   private User user;
}
