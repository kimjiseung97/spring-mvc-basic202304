package com.example.mvc.chap05.dto.response;

import com.example.mvc.chap05.dto.page.PageMaker;
import com.example.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListResponseDTO {

    private int count; //총 댓글 수
    private PageMaker pageInfo; // 페이지정보
    private List<ReplyDetailResponseDTO> replies; //댓글 리스트

}
