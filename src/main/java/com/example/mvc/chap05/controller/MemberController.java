package com.example.mvc.chap05.controller;

import com.example.mvc.chap05.dto.LoginRequestDTO;
import com.example.mvc.chap05.dto.SignUpRequestDTO;
import com.example.mvc.chap05.service.LoginResult;
import com.example.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.mvc.chap05.service.LoginResult.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    //회원 가입 요청

    //회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp(){
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }

    //회원가입 처리 요청
    @PostMapping("/sign-up")
    public void signUp(SignUpRequestDTO dto){
        log.info("/members/sign-up POST ! -{}", dto);
        boolean flag = memberService.join(dto);

    }

    //아이디 ,이메일 중복검사
    //비동기 요청 처리
    @ResponseBody //레스트 컨트롤러 처리를 위해 선언
    @GetMapping("/check")
    public ResponseEntity<?> check(String type, String keyword){
        log.info("/members/check?type={}&keyword={} ASYNC GET!",type, keyword);
        boolean flag = memberService.checkSingUpValue(type, keyword);
        return ResponseEntity.ok().body(flag);
    }

    //로그인 화면 요청
    @GetMapping("sign-in")
    public String signIn(){
        log.info("/members/sign-up GET - forwarding to jsp");
        return "members/sign-in";
    }
    //로그인 검증 요청
    @PostMapping("sign-in")
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra) {
        //리다이렉션시 두번째 응답에 데이터를 보내기 위함
        log.info("/members/sign-in POST ! :"+dto);

        LoginResult result = memberService.authenticate(dto);

        //로그인 성공시
        if(result== SUCCESS){
            return "redirect:/";
        }
        //1회용으로 쓰고 버릴 데이터
        ra.addFlashAttribute("msg",result);
        //로그인 실패시
        //redirect를하면 model에 실린데이터가 jsp로 전달이되지않음
        return "redirect:/members/sign-in";
    }





}
