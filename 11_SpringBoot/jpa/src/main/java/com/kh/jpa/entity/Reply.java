package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPLY")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private int replyNo;

    @Column(name = "REPLY_CONTENT", length = 400, nullable = false)
    private String replyContent;

    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    //어떤게시글의 댓글인지 게시글 정보
    //댓글 : 게시글 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REF_BNO")
    private Board board;

    //어떤사람의 댓글인지 게시글 정보
    //댓글 : 작성자 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REPLY_WRITER")
    private Member member;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        if(status == null){
            this.status = CommonEnums.Status.Y;
        }
    }
}
