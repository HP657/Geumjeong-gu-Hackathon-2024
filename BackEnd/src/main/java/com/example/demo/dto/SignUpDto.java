package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String email;
    private String password;
}
