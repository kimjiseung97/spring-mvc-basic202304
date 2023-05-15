package com.example.mvc.chap05.controller;

import com.example.mvc.chap05.dto.response.BoardListResponseDTO;
import com.example.mvc.chap05.dto.request.BoardWriteRequstDTO;
import com.example.mvc.chap05.dto.response.BorderDetailResponseDTO;
import com.example.mvc.chap05.dto.page.PageMaker;
import com.example.mvc.chap05.dto.page.Search;
import com.example.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")  //공통url
@Slf4j
public class BoardController {

    private final BoardService boardService; //서비스에게 의존함

    @GetMapping("/list") //세부 url getMapping
    public String list(Model model, Search page, HttpServletRequest request) {
//        boolean flag = false;
//        //세션을 확인
//        Object login = request.getSession().getAttribute("login");
//
//        if(login!=null) flag=true;

////쿠키를 확인
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals("login")){
//                flag= true;
//                break;
//            }
//        }
//        if(!flag) return "redirect:/members/sign-in";
        log.info("/board/list : GET");
        log.info("page : {}",page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);

        //페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page,boardService.getCount(page));
        model.addAttribute("bList", responseDTOS);
        model.addAttribute("maker",maker);
        model.addAttribute("s",page);
        return "chap05/list";
    }


    //글 등록 요청 함수
    @PostMapping("/write")
    public String write(BoardWriteRequstDTO dto,HttpSession session){
        System.out.println("/board/write : POST");
        boardService.register(dto,session);
        return "redirect:/board/list";
    }

    // 글쓰기 화면 요청 함수
    @GetMapping("/write")
    public  String write(HttpSession session){

//        if(!LoginUtil.isLogin(session)){
//            return "redirect:/members/sign-in";
//        }
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
    public String detail(int bno, @ModelAttribute("s") Search search, Model model){
        System.out.println("/board/detail : GET");
        BorderDetailResponseDTO detail = boardService.getDetail(bno);
        model.addAttribute("b",detail);
        return "chap05/detail";
    }

















}
