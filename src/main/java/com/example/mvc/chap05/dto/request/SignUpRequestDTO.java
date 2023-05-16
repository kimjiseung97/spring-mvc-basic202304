package com.example.mvc.chap05.dto.request;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
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
    @Email
    private String email;

    //프로필 이미지 필드
    @Nullable
    private MultipartFile profileImage;
}
