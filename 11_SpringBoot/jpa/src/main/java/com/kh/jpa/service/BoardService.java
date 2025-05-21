package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Page<BoardDto.Response> getBoardList(Pageable pageable);
    BoardDto.Response getBoardDetail(Long boardNo);
    Long createBoard(BoardDto.Create boardDto) throws IOException;
    Void deleteBoard(Long boardNo);
    BoardDto.Response updateBoard(Long boardNO, BoardDto.Update boardDto) throws IOException;
}
