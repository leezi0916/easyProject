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
    //사진게시글 조회수 +1
    int increaseCount(int pno);
    //사진게시글 상세보기
    Photo selectPhoto(int pno);
    //사진게시글 수정하기
    int updatePhoto(Photo p);
    //사진게시글 삭제하기
    int deletePhoto(int pno);
}
