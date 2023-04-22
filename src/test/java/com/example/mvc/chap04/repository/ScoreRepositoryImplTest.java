package com.example.mvc.chap04.repository;

import com.example.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository scoreRepository = new ScoreRepositoryImpl();

    //단위 테스트(Unit test)
    //테스트 시나리오
    // -단언(Assertion) 기법
    @Test
    @DisplayName("저장소에서 findAll을 호출하면 " +
            "그 반환된 리스트에는 성적정보가 3개 있어야 한다.")
    void findAllTest() {
        // given-when-then 패턴
        // given: 테스트를 위해 주어질 데이터 (ex: parameter)

        // when: 테스트 실제 상황
        List<Score> scoreList = scoreRepository.findAll();

        // then: 테스트 결과 확인
        System.out.println(scoreList.size() == 3);

        // 나는 스코어리스트의 사이즈가 3인 것이 참이라고 단언한다.
//        assertTrue(scoreList.size() == 3);
        assertEquals(3, scoreList.size());

        // 나는 리스트의 첫번째 객체의 이름이 뽀로로라고 단언한다.
        assertEquals("뽀로로", scoreList.get(0).getName());
    }
    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여" + "학번이 2인 학생을 조회하면 "+"그 학생의 국어점수가 33점이고" + "이름이  춘식이어야한다")

    void findOneTest(){
        //given
        int stuNum = 2;
        //when
        Score score = scoreRepository.findScoreByStuNum(stuNum);
        //then

        assertEquals(33,score.getKor());
        assertEquals("춘식이",score.getName());
    }


    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여" + "학번이 99인 학생을 조회하면 null이 리턴될 것이다")

    void findOneTest2(){
        //given
        int stuNum = 99;
        //when
        Score score = scoreRepository.findScoreByStuNum(stuNum);
        //then
        assertNull(score);
    }







    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" + "목록의 개수가 4개여야한다.")

    void saveTest(){
        //given
        Score score = new Score("뽀로로", 100, 50, 70, ++sequence, 0, 0, A);
        score.setName("언년이");
        score.setKor(100);
        score.setEng(50);
        score.setEng(0);
        //when

        boolean flag = scoreRepository.saveScore(score);
        List<Score> scoreList = scoreRepository.findAll();
        //then
        assertEquals(4, scoreList.get(scoreList.size() - 1).getStuNum());
        assertTrue(flag);
    }


}