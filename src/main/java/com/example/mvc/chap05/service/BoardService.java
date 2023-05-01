package com.example.mvc.chap05.service;

import com.example.mvc.chap05.dto.BoardListResponseDTO;
import com.example.mvc.chap05.dto.BoardWriteRequstDTO;
import com.example.mvc.chap05.dto.BorderDetailResponseDTO;
import com.example.mvc.chap05.dto.page.Page;
import com.example.mvc.chap05.entity.Board;
import com.example.mvc.chap05.repository.BoardMapper;
import com.example.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BoardService {

    //private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;
    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList(Page page) {

        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList())
                ;
    }


    //글 등록 중간처리
    public boolean register(BoardWriteRequstDTO dto) {
       return boardRepository.save(new Board(dto));
    }


    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    public BorderDetailResponseDTO getDetail(int bno) {
        Board board = boardRepository.findOne(bno);
        //조회수 상승처리
//        board.setViewCount(board.getViewCount() + 1);
        boardRepository.upViewCount(bno);
        return new BorderDetailResponseDTO(board);
    }

    public int getCount() {
        return boardRepository.count();
    }
}
