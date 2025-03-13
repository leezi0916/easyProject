package com.kh.boot.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Photo {
    private int photoNo;
    private String photoTitle;
    private String photoWriter;
    private String originName;
    private String changeName;
    private int count;
    private String createDate;
    private String status;

}
