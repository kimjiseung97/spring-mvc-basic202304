package com.example.mvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class SignUpRequestDTO {

    @NotBlank
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 2, max = 6)
    private String name;

    @NotBlank
    private String email;
}
