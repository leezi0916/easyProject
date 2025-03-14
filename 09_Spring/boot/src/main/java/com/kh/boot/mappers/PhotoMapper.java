package com.kh.boot.mappers;

import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Mapper
public interface PhotoMapper {
    // 사진게시글 갯수
    int selectPhotoCount();
    // 사진게시글 리스트(페이지까지)
    ArrayList<Photo> selectPhotoList(RowBounds rowBounds);
    // 사진게시글 등록하기
    int insertPhoto(Photo photo);
    // 사진게시글 조회수+1
    int increaseCount(@Param("pno") int pno);
    // 사진게시글 상세보기
    Photo selectPhoto(@Param("pno") int pno);
    // 사진게시글 수정하기
    int updatePhoto(Photo p);
    // 사진게시글 삭제하기
    int deletePhoto(@Param("pno") int pno);
}
