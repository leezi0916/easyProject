package com.kh.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;
import lombok.*;

public class NoticeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private String notice_title;
        private String notice_writer;
        private String notice_content;

        public Notice toEntity(Member member) {
            return Notice.builder()
                    .noticeTitle(this.notice_title)
                    .member(member) // ❗ 이거 빠져있으면 NPE 발생 가능
                    .noticeContent(this.notice_content)
                    .build();
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long notice_no;
        private String notice_title;
        private String notice_writer;
        private String notice_content;

        public static Response toDto(Notice notice) {
            return Response.builder()
                    .notice_no(notice.getNoticeNo())
                    .notice_title(notice.getNoticeTitle())
                    .notice_writer(notice.getMember().getUserId())
                    .notice_content(notice.getNoticeContent())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update {
        private String notice_title;
        private String notice_content;
    }
}
