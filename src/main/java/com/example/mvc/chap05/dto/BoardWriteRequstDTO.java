package com.example.mvc.chap05.dto;


import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardWriteRequstDTO {

    private String title;

    private String content;
}
