package com.example.mvc.chap05.repository;

import com.example.mvc.chap05.dto.page.Search;
import com.example.mvc.chap05.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.invoke.SerializedLambda;
import java.util.List;

@SpringBootTest
class BoardMapperTest {
    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("게시물 300개를 작성해야 한다.")
    void bulkInsertTest(){
        //given
        for (int i=1; i<=300; i++) {
            Board board = Board.builder().title("테스트제목 "+ i).content("테스트 내용 "+i).build();

            mapper.save(board);
        }

    }

    @Test
    @DisplayName("제목으로 검색한 내용이 나와야한다")
    void SearchTest(){
        Search search = new Search();
        search.setType("title");
        search.setKeyword("안녕");
        List<Board> all = mapper.findAll(search);
        System.out.println(all.toString());
    }


}