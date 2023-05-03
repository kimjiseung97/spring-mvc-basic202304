package com.example.mvc.chap05.api;

import com.example.mvc.chap05.dto.ReplyListResponseDTO;
import com.example.mvc.chap05.dto.page.Page;
import com.example.mvc.chap05.entity.Reply;
import com.example.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
public class ReplyController {

    private final ReplyService replyService;
    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/3/page/1
    //              3번 게시물 댓글목록 1페이지 내놔
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
    ){
        log.info("/api/v1/replies/{}/page/{} : GET!!", boardNo,pageNo);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);
        ReplyListResponseDTO list = replyService.getList(boardNo,page);
        return ResponseEntity.ok().body(list);
    }


}