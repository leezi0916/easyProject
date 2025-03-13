package com.kh.boot.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
    @JsonProperty("reply_no") //Json변환시 key명 설정
    private int replyNo;

    @JsonProperty("reply_content")
    private String replyContent;

    @JsonIgnore // 이 필드는 json으로 변환할 때 포함 x
    private int refBno;

    @JsonProperty("user_id")
    private String replyWriter;

    @JsonProperty("create_date")
    private String createDate;

    @JsonIgnore
    private String status;
}