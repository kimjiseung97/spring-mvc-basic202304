package com.example.mvc.chap05.controller;

import com.example.mvc.chap05.dto.BoardListResponseDTO;
import com.example.mvc.chap05.dto.BoardWriteRequstDTO;
import com.example.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")  //공통url
public class BoardController {

    private final BoardService boardService; //서비스에게 의존함

    @GetMapping("/list") //세부 url getMapping
    public String list(Model model) {
        System.out.println("/board/list : GET");
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList();
        model.addAttribute("bList", responseDTOS);
        return "chap05/list";
    }


    //글 등록 요청 함수
    @PostMapping("/write")
    public String write(BoardWriteRequstDTO dto){
        System.out.println("/board/write : POST");
        boardService.register(dto);
        return "redirect:/board/list";
    }

    // 글쓰기 화면 요청 함수
    @GetMapping("/write")
    public  String write(){
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    //글 번호로 삭제요청 함수
    @GetMapping("/delete")
    public  String delete(int bno){
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    //글 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, Model model){
        System.out.println("/board/detail : GET");
        model.addAttribute("b",boardService.getDetail(bno));

        return "/chap05/detail";
    }















}
