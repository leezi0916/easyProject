package com.kh.jpa.dto;

import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Reply;
import com.kh.jpa.enums.CommonEnums;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Long board_no;
        private String board_title;
        private String board_writer;
        private String user_name;
        private String board_content;
        private String origin_name;
        private String change_name;
        private LocalDateTime create_date;
        private Integer count;
        private List<String> tags; //굳이 중계테이블인 보드태그를 안갖고오고 string으로 갖고 온다


        public static Response toDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .board_writer(board.getMember().getUserId())
                    .user_name(board.getMember().getUserName())
                    .board_content(board.getBoardContent())
                    .origin_name(board.getOriginName())
                    .change_name(board.getChangeName())
                    .create_date(board.getCreateDate())
                    .count(board.getCount())
                    .tags(board.getBoardTags()
                            .stream()
                            .map(boardTag -> boardTag.getTag().getTagName())
                            .toList())
                    .build();

            //boardTag x  boardTags가 여러개이기 때문에 하나마다 전부 -> tag추출
        }

        public static Response toSimpleDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .board_writer(board.getMember().getUserId())
                    .board_content(board.getBoardContent())
                    .origin_name(board.getOriginName())
                    .create_date(board.getCreateDate())
                    .count(board.getCount())
                    .build();
        }


    }


    @Getter
    @AllArgsConstructor
    public static class Create {
        private String board_title;
        private String board_writer;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Update {
        private String board_title;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;


        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }

}
