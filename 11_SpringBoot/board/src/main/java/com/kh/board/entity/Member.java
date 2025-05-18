package com.kh.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity //테이블과 1대1로 매핑되는 개체를 ENTITY
@Table(name = "MEMBER")
@Getter
public class Member {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100) //최대길이 100자
    private String nickname;

    @CreationTimestamp //엔티티가 처음저장될 때 자동으로 현재 시간 저장
    @Column(name = "create_at", updatable = false) //최초생성시간은 변경이 불가하도록
    private LocalDateTime createAt;

    @UpdateTimestamp //엔티티가 수정될때마다 자동으로 업데이트 시간을 넣어줌
    @Column(name =  "update_at")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    //Member : Board = 1: N 관계 (한명의 회원은 여러 게시글을 작성 할 수 있음)
    // mappedBy = "member" -> Board 엔티티의 'member'필드가 외래키 주인임을 의미
    // cascade = CascadeTyp.ALL -> 회원 삭제시 관련 게시글 모두 삭제
    private List<Board> boards = new ArrayList<>(); //리스트로 담아옴 1대다 니까  여러개를 담아야함
}
