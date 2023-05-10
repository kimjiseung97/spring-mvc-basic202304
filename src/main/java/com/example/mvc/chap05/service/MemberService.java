package com.example.mvc.chap05.service;


import com.example.mvc.chap05.dto.LoginRequestDTO;
import com.example.mvc.chap05.dto.SignUpRequestDTO;
import com.example.mvc.chap05.entity.Member;
import com.example.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.mvc.chap05.service.LoginResult.*;

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

    //중복검사 서비스처리
    public boolean checkSingUpValue(String type, String keyword){
        int flagNum = memberMapper.isDuplicate(type, keyword);

        return flagNum==1;
    }

    public LoginResult authenticate(LoginRequestDTO dto) {

        Member foundmember = memberMapper.findMember(dto.getAccount());
        //회원가입 여부 확인
        if(foundmember==null){
            log.info("{} - 회원가입 안했음",dto.getAccount());
            return NO_ACC;
        }
        //비밀번호 일치 확인
        if(!encoder.matches(dto.getPassword(),foundmember.getPassword())){
            log.info("비밀번호 불 일치!");
            return NO_PW;
        }

        return SUCCESS;
    }
}
