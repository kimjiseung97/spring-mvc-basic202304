package com.example.mvc.chap05.repository;

import com.example.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    //회원가입기능
    boolean save(Member member);
    //회원 정보조회
    Member findMember(String account);
    //중복체크(account/email) 기능
    //이메일을할건지 계정을검사할건지 타입과 키워드를 정해주어야한다
    int isDuplicate(@Param("type") String type, @Param("keyword") String keyword);




}
