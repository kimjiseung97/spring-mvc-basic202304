package com.example.mvc.chap05.api;

import com.example.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.example.mvc.chap05.dto.request.ReplyModifyRequestDTO;
import com.example.mvc.chap05.dto.request.ReplyPostRequestDTO;
import com.example.mvc.chap05.dto.page.Page;
import com.example.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
//클라이언트의 접근을 app에서만 허용할 것인가

@CrossOrigin(origins = {"http://127.0.0.1:5500"})
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

    @PostMapping
    //댓글 등록 요청
    public ResponseEntity<?> create(
            //요청 메시지 바디에 json으로 보내주세요
            //요청데이터 검증하겠다 @Validated
            @Validated @RequestBody ReplyPostRequestDTO dto, BindingResult result //검증결과를 가진객체
    ) {
        //입력값 검증에 걸리면 4xx 상태코드 리턴
        if(result.hasErrors()){
             return ResponseEntity.badRequest().body(result.toString());
        }
        log.info("/api/v1/replies : POST!");
        log.info("param :{}", dto);
        //서비스에 비즈니스 오직 처리 위임
        ReplyListResponseDTO responseDTO;
        try {
            responseDTO = replyService.register(dto);
        } catch (Exception e) {
            //문제 발생상황을 클라이언트에게 응답
            log.warn("500 Status code response!! caused by : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        //성공시클라이언트에 응답하기
        return ResponseEntity.ok(responseDTO);
    }

    //댓글 삭제 요청
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(@PathVariable(
            required = false) Long replyNo){
        if(replyNo==null){
            return ResponseEntity.badRequest().body("댓글 번호를 보내주세요!");
        }
        log.info("/api/v1/replies/{}DELETE!",replyNo);
        try {
            ReplyListResponseDTO responseDTO = replyService.delete(replyNo);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    //댓글 수정 요청
    @RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH})
    public ResponseEntity<?> modify( @Validated @RequestBody ReplyModifyRequestDTO dto,
                                     BindingResult result)
    {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.toString());
        }
        log.info("/api/v1/replies PUT!!");
        try{
            ReplyListResponseDTO responseDTO = replyService.modify(dto);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            log.warn("500 status code caused by : {}",e.getMessage());;
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }





}
