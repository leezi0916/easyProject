package com.kh.board.service;

import com.kh.board.controller.dto.request.BoardRequest;
import com.kh.board.entity.Board;
import com.kh.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;


    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public Board findOne(Long boardId) {
        return boardMapper.findOne(boardId);
    }

    @Override
    public int save(Board board) {
        // DB에 게시글 저장
       return boardMapper.save(board);
    }

    @Override
    public int delete(Long boardId) {
        return boardMapper.delete(boardId);
    }

    @Override
    public Long update(Board board) {
        boardMapper.update(board);
        return board.getBoardId();
    }


}
