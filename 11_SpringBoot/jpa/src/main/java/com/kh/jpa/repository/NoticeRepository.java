package com.kh.jpa.repository;

import com.kh.jpa.entity.Notice;

import java.util.Optional;

public interface NoticeRepository {
    void save(Notice notice);
    Optional<Notice> findOne(Long noticeNo);

}
