package com.example.mvc.chap05.repository;

import com.example.mvc.chap05.dto.page.Search;
import com.example.mvc.chap05.entity.Board;
import com.example.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {
    @Autowired
    BoardMapper boardMapper;

    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물 300개를 등록하고 각게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야한다")
    void bulkInsertTest(){
        for (int i = 1; i <=300 ; i++) {
            Board b = Board.builder().title("재밌는 게시물" + i).content("노잼 게시물 내용" + i).build();

            boardMapper.save(b);
        }
        assertEquals(300, boardMapper.count(new Search()));

        for (int i = 1; i <=1000 ; i++) {
            Reply r = Reply.builder().replyWriter("잼민이" + i).replyText("말똥이~~~~" +i).boardNo((long)(Math.random()*300+1)).build();

            replyMapper.save(r);
        }

    }
    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번개시물의 총 댓글수는 5개여야한다")
    @Transactional
    @Rollback // 끝난 이후 롤백해라
    void saveTest(){
        //given
        long BoardNo = 3L;
        Reply newReply = Reply.builder().replyText("세이브세이브").replyWriter("긴또깡").boardNo(BoardNo).build();

        //when
        boolean flag = replyMapper.save(newReply);

        assertTrue(flag); //세이브가 성공했을거라고 단언한다.

        assertEquals(2,replyMapper.count(BoardNo));
    }


}