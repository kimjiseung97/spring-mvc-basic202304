package com.example.mvc.chap05.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class LoginUserResponseDTO {

    private String account;

    private String nickName;

    private String email;
}
