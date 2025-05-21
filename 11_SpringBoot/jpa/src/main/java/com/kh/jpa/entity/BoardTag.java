package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD_TAG")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardTag { //N:M 관계는 중계테이블로 따로 빼서 1:N + (중계) + N:1 처럼 만들어서 진행
    @Id
    @Column(name = "BOARD_TAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 단일 기본키 사용
    private Long boardTagId;

    //게시글 : 중계테이블(1 : N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_NO", nullable = false)
    private Board board;

    public void changeBoard(Board board) {
        this.board = board;
        board.getBoardTags().add(this);
    }

    //태그 : 중계테이블(1: N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;

    private LocalDateTime createdAt = LocalDateTime.now();
}
