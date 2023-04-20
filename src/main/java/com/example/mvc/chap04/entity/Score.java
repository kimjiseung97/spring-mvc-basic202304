package com.example.mvc.chap04.entity;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private String name; //학생 이름

    private int kor, eng, math; // 국 영 수 점수

    private int stuNum; //학번

    private int total; //총점

    private Grade grade; //학점


}
