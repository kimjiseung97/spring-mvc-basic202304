package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//역할 명세(추상화)
//성적 정보를 잘 저장하고 추가하고 상제하고 수정해야 한다
//그래서 어디에 저장하는건데?
//어디에서 조회? 어디에서 삭제?
@Mapper
public interface ScoreMapper {

    boolean save(Score score);

    //정렬 조회

    List<Score> findAll(String sort);

    //성적정보 등록 처리 요청

    //성적 정보 한개 삭제
    boolean deleteByStuNum(int stuNum);

    //성적 정보 개별 조회
    Score findByStuNum(int stuNum);

}
