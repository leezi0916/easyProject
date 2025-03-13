package com.kh.boot.service;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Photo;

import java.util.ArrayList;

public interface PhotoService {
    //총사진게시글 수
    int selectPhotoCount();
    //사진게시글 정보(페이징)
    ArrayList<Photo> selectPhotoList(PageInfo pi);
    //사진게시글 추가하기
    int insertPhoto(Photo photo);

}
