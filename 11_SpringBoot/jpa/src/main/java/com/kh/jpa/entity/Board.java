package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOARD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NO")
    private Long boardNo;

    @Column(name = "BOARD_TITLE", length = 100,nullable = false)
    private String boardTitle;

    //@LOB : 대용량 데이터 매핑
    @Column(name = "BOARD_CONTENT", nullable = false)
    @Lob
    private String boardContent;

    @Column(name = "ORIGIN_NAME", length = 100)
    private String originName;

    @Column(name = "CHANGE_NAME", length = 100)
    private String changeName;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @Column(name = "COUNT")
    private Integer count;

    //Board : Member (N : 1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOARD_WRITER")
    private Member member;

    public void changeMember(Member member) { //편의 메서드 (dto에 member를 작성하지 말고)
        this.member = member;
        if(member.getBoards().contains(this)) {
            member.getBoards().add(this);
        }
    }

    //Reply : Board (N : 1)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 빌더 패턴 사용해서 생성할때 이 아래 있는 애를 건들지마!!
    private List<Reply> replies = new ArrayList<>(); //여기 값이 안들어간다.

    //BoardTag : Board (N : 1)
    //CascadeType : 관련된 애들도 영속성에서 같이 관리해줄려면 사용
    //orphanRemoval = true N:1 또는 1:N 연관관계에서 자식 생명주기를 부모가 완전히 통제하겠다.
    //부모 엔티티에서 자식과의 관계가 제거되면, 자식도 자동으로 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL ,orphanRemoval = true)
    @Builder.Default
    private List<BoardTag> boardTags = new ArrayList<>();

    public void changeFile(String originName, String changeName) {
        this.originName = originName;
        this.changeName = changeName;
    }

    public void changeContent(String boardContent) {
        if(boardContent != null && !boardContent.isEmpty()) { //?
            this.boardContent = boardContent;
        }
    }

    public void changeTitle(String boardTitle) {
        if(boardTitle != null && !boardTitle.isEmpty()) {
            this.boardTitle = boardTitle;
        }
    }

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.count = 0;
        if(status == null){
            this.status = CommonEnums.Status.Y;
        }
    }



}
