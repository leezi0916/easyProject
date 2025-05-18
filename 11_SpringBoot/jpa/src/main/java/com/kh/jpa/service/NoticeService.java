package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;

public interface NoticeService {
    Long createNotice(NoticeDto.Create noticeDto);
    NoticeDto.Response findNotice(Long noticeNo);
    NoticeDto.Response updateNotice(Long noticeNo, NoticeDto.Update updateDto);
    void deleteNotice(Long noticeNo);
}
