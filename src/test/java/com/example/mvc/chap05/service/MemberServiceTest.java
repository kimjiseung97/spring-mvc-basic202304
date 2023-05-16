package com.example.mvc.chap05.service;

import com.example.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("SignUpDTO를 전달하면 회원가입에 성공해야한다")
    void joinTest(){
        //given
        SignUpRequestDTO dto = new SignUpRequestDTO();
        dto.setAccount("kukukaka");
        dto.setPassword("lalala12343");
        dto.setName("루피");
        dto.setEmail("ABCD@bbb.com");

        //when
        memberService.join(dto, savePath);
    }

//    @Test
//    @DisplayName("계정명이 abcd1234인 회원이 로그인시도시 결과검증을 상황별로 수행해야한다")

//    void loginTest(){
//        //given
//        LoginRequestDTO dto = new LoginRequestDTO();
//        dto.setAccount("kukukaka");
//        dto.setPassword("lalala12343");
//        //when
//        LoginResult result = memberService.authenticate(dto);
//        //then
//        assertEquals(LoginResult.SUCCESS,result);
//    }
}