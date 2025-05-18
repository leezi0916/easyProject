package com.kh.board.controller;


import com.kh.board.controller.dto.request.BoardRequest;
import com.kh.board.controller.dto.response.BoardResponse;
import com.kh.board.entity.Board;
import com.kh.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//    //게시글목록을 가져오는 api
//    @GetMapping
//    public ResponseEntity<List<BoardResponse.SimpleDTO>> getBoardList() {
//        List<Board> boardList = boardService.findAll();
//
//        List<BoardResponse.SimpleDTO> result = new ArrayList<>();
//        for(Board board : boardList) {
//            result.add(BoardResponse.SimpleDTO.fromEntity(board));
//        }
//        System.out.println(result);
//        return new ResponseEntity<>(result, HttpStatus.OK); //응답용 객체
//    }
//
//    // 게시글 상세보기
//    @GetMapping("/{boardId}")
//    public ResponseEntity<BoardResponse.DetailDTO> getBoard(@PathVariable Long boardId) {
//        Board board = boardService.findOne(boardId);
//        BoardResponse.DetailDTO result = BoardResponse.DetailDTO.fromEntity(board);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    //게시글등록
//    @PostMapping
//    public ResponseEntity<String> createBoard(BoardRequest.CreateDTO request, MultipartFile upfile) throws IOException {
//
//        if(request == null || request.getUser_id() == null){
//            throw new RuntimeException("check value"); // 서버에서 실행중인 에러 던지기 런타임오류
//        }
//
//        if(!upfile.isEmpty()){
//            File file = new File("/Users/easymook/workspace/11_SpringBoot/board/src/main/resources/uploads", upfile.getOriginalFilename()); // 원래는 서버에 이미지 경로가 올라가는 ip를 쓰면 됨
//            upfile.transferTo(file);
//
//            request.setFile_name("/uploads/" + upfile.getOriginalFilename());
//        }
//
//        Board board = request.toEntity();
//
//        // 서비스 계층으로 Board 엔티티 전달
//        int result = boardService.save(board);
//
//        if(result > 0){
//            return new ResponseEntity<>("게시글 등록 성공", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("게시글 등록 실패", HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    //해당 게시글 삭제
//    @DeleteMapping("/{boardId}")
//    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
//        int result = boardService.delete(boardId);
//
//        if(result > 0) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    //해당 게시글 수정
//    @PutMapping//(경로 필요없음)
//    public ResponseEntity<Long> updateBoard(BoardRequest.UpdateDTO request, MultipartFile upfile) throws IOException {
//
//        // 파일 업로드 처리
//        if (upfile != null && !upfile.isEmpty()) {
//            File file = new File("/Users/easymook/workspace/11_SpringBoot/board/src/main/resources/uploads", upfile.getOriginalFilename());
//            upfile.transferTo(file);
//            request.setOrigin_file("/uploads/" + upfile.getOriginalFilename());
//        }
//
//        Board board = request.toEntity();
//
//        Long boardId = boardService.update(board);
//        return new ResponseEntity<>(boardId, HttpStatus.OK);
//    }

}
