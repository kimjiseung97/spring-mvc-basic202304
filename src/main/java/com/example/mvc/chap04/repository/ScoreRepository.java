package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;

import java.util.List;

//역할 명세(추상화)
//성적 정보를 잘 저장하고 추가하고 상제하고 수정해야 한다
//그래서 어디에 저장하는건데?
//어디에서 조회? 어디에서 삭제?
public interface ScoreRepository {

    boolean save(Score score);

    //성적 정보 전체 목록 조회
    List<Score> findAll(); //일반 조회

    default List<Score> findAll(String sort){
        return null;
    }//정렬 조회

    //성적정보 등록 처리 요청
    boolean saveScore(Score score);

    //성적 정보 한개 삭제
    boolean deleteScoreByStuNum(int stuNum);

    //성적 정보 개별 조회
    Score findScoreByStuNum(int stuNum);

}
