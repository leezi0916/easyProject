package com.kh.boot.mappers;

import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

@Mapper
public interface PhotoMapper {
    int selectPhotoCount();
    ArrayList<Photo> selectPhotoList(RowBounds rowBounds);
    int insertPhoto(Photo photo);
}
