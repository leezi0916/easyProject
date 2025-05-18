package com.kh.jpa.controller;

import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notices")
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지 등록 API
    @PostMapping
    public ResponseEntity<String> addNotice(@RequestBody NoticeDto.Create noticeDto) {

        Long noticeNo = noticeService.createNotice(noticeDto);

        return ResponseEntity.ok(noticeNo.toString());
    }

    // 공지번호로 조회
    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> getNotice(@PathVariable Long noticeNo) {
        return ResponseEntity.ok(noticeService.findNotice(noticeNo));
    }

    //공지수정
    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> updateNotice(
            @PathVariable Long noticeNo,
            @RequestBody NoticeDto.Update updateDto){
        return ResponseEntity.ok(noticeService.updateNotice(noticeNo, updateDto));

    }


}
