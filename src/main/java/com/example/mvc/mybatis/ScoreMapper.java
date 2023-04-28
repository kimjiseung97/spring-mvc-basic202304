package com.example.mvc.mybatis;

import com.example.mvc.chap04.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {

    //CRUD에 대한 기능 명세

    //사람 정보 저장
    boolean save(Score s);
    //사람 정보 수정
    boolean change(Score s);

    //사람 정보 삭제
    boolean remove(int stuNum);

    //전체조회
    List<Score> findAll();

    //개별 조회
    Score findOne(int stuNum);
}
