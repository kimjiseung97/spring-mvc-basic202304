package com.example.mvc.chap05.dto.response;

import com.example.mvc.chap05.dto.response.BoardListResponseDTO;
import com.example.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString @EqualsAndHashCode
public class BorderDetailResponseDTO {
    private final int boardNo;

    private final String title;

    private final String content;

    private final String date;

    private final String writer;


    public BorderDetailResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = BoardListResponseDTO.makePrettierDateString(board.getRegDateTime());
        this.writer = board.getWriter();
    }
}
