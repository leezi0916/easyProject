package com.kh.boot.domain.vo;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private int formNo;
    private String formTitle;
    private String formWriter;
    private String formResponseUrl;
    private String formDashBoardUrl;
    private String createDate;
    private String status;
}
