package com.kh.board.controller.dto.response;

import com.kh.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

public class BoardResponse {
    @Getter
    @Setter
    @Builder //아래에대한 빌더 패턴 메서드 생성됨
    public static class SimpleDTO{
        private Long board_id;
        private String member_email;
        private String title;
        private LocalDateTime create_at;


        public static SimpleDTO fromEntity(Board board) {
           return SimpleDTO.builder() //SimpleDTO에 넣고싶은 데이터를 넣어서 반환
                    .board_id(board.getBoardId())
                    .member_email(board.getMember().getEmail())
                    .title(board.getTitle())
                    .create_at(board.getCreateAt())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class DetailDTO {
        private Long board_id;
        private String member_email;
        private String title;
        private String contents;
        private String file_name;
        private LocalDateTime create_at;
        private LocalDateTime update_at;

        public static DetailDTO fromEntity(Board board) {
            return DetailDTO.builder()
                    .board_id(board.getBoardId())
                    .member_email(board.getMember().getEmail())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .file_name(board.getFileName())
                    .create_at(board.getCreateAt())
                    .update_at(board.getUpdateAt())
                    .build();
        }
    }

}
