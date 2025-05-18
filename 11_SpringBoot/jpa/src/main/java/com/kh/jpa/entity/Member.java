package com.kh.jpa.entity;


import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@Builder
@AllArgsConstructor
@Table(name = "MEMBER")
@Getter
@DynamicInsert //insert시에 null이 아닌 필드만 쿼리에 포함, default값 활용
@DynamicUpdate //변경된 필드만 update문에 포함
public class Member {

    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PWD", nullable = false, length = 100)
    private String userPwd;

    @Column(name = "USER_NAME", nullable = false, length = 15)
    private String userName;

    @Column(name = "EMAIL", length = 254)
    private String email;

    @Column(name = "GENDER", length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "AGE")
    private Integer age; //Integer로 사용하는 이유는 나중에 null체킹을 할 수 있기 때문
                          //int로 설정하면 기본값이 0으로 됨

    @Column(name = "PHONE", length = 13)
    private String phone;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "ENROLL_DATE")
    private LocalDateTime enrollDate; //표준시간

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;


    //1: N 연관관계 주인 = Board
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Board> boards = new ArrayList<>();

    //회원 : 프로필 (1 : 1)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID", unique = true) //외래키 있는 컬럼에 유니크 속성 부여
    private Profile profile;

    //1: N 연관관계 주인 = Notice
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notice> notices = new ArrayList<>();


    public enum Gender { //체크 제약 비슷
        M, F
    }

    public void updateMemberInfo(String userName,String email,Gender gender,String phone,String address, Integer age) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.age = age;

    }

    //엔티티가 영속성 컨테스트에 저장되기 전(em.persist())에 실행되는 메서드
    //초기설정을 해두는 용도로 사용
    @PrePersist
    public void prePersist() { //default 값 설정
        this.enrollDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        if(this.status == null){
            this.status = CommonEnums.Status.Y;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now();
    }
}
