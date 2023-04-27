package com.example.mvc.mybatis;

import com.example.mvc.chap04.dto.ScoreRequestDTO;
import com.example.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper scoreMapper;

    @Test
    @DisplayName("score객체를 전달하면 db에 정보가 저장되어야한다")
    void insertTest() {
        Score score = new Score(ScoreRequestDTO.builder().name("고길동").eng(80).kor(70).math(80).build());

        boolean save = scoreMapper.save(score);

        assertTrue(save);
    }
}