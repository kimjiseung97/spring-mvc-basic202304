package com.example.mvc.chap05.service;


import com.example.mvc.chap05.dto.SignUpRequestDTO;
import com.example.mvc.chap05.entity.Member;
import com.example.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;
    private final PasswordEncoder encoder;
    //회원가입 처리 서비스
    public boolean join(final SignUpRequestDTO dto){

        //dto를 entity로 변환
        Member member = Member.builder().account(dto.getAccount()).email(dto.getEmail()).name(dto.getName()).password(encoder.encode(dto.getPassword())).build();



        //mapper에게 회워정보 전달하고 저장명령 내리기
        return memberMapper.save(member);
    }
}
