package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@Builder
@AllArgsConstructor
@Table(name = "NOTICE")
@Getter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NO")
    private Long noticeNo;

    @Column(name = "NOTICE_TITLE", nullable = false,length = 30)
    private String noticeTitle;

    @Column(name = "NOTICE_CONTENT", nullable = false, length = 200)
    private String noticeContent;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    //공지 : 작성자(N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_WRITER", nullable = false)
    private Member member;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    public void updateNoticeInfo(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;

    }


}
