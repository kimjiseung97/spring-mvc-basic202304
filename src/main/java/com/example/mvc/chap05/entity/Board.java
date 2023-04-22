package com.example.mvc.chap05.entity;

import com.example.mvc.chap05.dto.BoardWriteRequstDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {

    private int boardNo; //게시글번호

    private String title; //제목

    private String content; //내용

    private int viewCount; //조회수

    private LocalDateTime regDateTime; // 작성일자시간


    public Board(int boardNo, String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.regDateTime = LocalDateTime.now();
    }

    public Board(BoardWriteRequstDTO dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.regDateTime = LocalDateTime.now();
    }



}