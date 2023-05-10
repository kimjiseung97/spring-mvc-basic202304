package com.example.mvc.chap05.dto;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class SignUpRequestDTO {

    private String account;

    private String password;

    private String name;

    private String email;
}
