package com.kh.jpa.controller;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.dto.PageResponse;
import com.kh.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /*
    page 보고자하는 페이지 번호
    size 몇개씩 가지고 올 것인지
    sort 정렬 기준 : 속성, 방향 (boardTitle,desc)
     */
    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<PageResponse<BoardDto.Response>> getBoards(
            @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(boardService.getBoardList(pageable)));
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable("id") Long boardNo) {
        return ResponseEntity.ok(boardService.getBoardDetail(boardNo));
    }
    // 게시판 등록
    @PostMapping
    public ResponseEntity<String> addBoard(@ModelAttribute BoardDto.Create boardDto) throws IOException { //requstBody는 body안에있는 걸 json형태로 받을때 사용
        Long boardNo = boardService.createBoard(boardDto);
        return ResponseEntity.ok(boardNo.toString());
    }

    //게시판 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long boardNo) {
        boardService.deleteBoard(boardNo);
        return ResponseEntity.ok().build(); //어떤 값을 리턴하는게 아니면 ResponseEntity에서 .build 사용
    }

    //게시판 수정
    @PatchMapping("/{id}") //풋보다 패치를 많이 사용
    public ResponseEntity<BoardDto.Response> updateBoard(@PathVariable("id") Long boardNo,
                                                         @ModelAttribute BoardDto.Update updateBoard) throws IOException {

        return ResponseEntity.ok(boardService.updateBoard(boardNo, updateBoard));
    }

}
