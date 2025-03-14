package com.kh.boot.service;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Photo;
import com.kh.boot.mappers.PhotoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoMapper photoMapper;


    @Override
    public int selectPhotoCount() {
        return photoMapper.selectPhotoCount();
    }

    @Override
    public ArrayList<Photo> selectPhotoList(PageInfo pi) {
        int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
        return photoMapper.selectPhotoList(rowBounds);
    }

    @Override
    public int insertPhoto(Photo photo) {
        return photoMapper.insertPhoto(photo);
    }

    @Override
    public int increaseCount(int pno) {
        return photoMapper.increaseCount(pno);
    }

    @Override
    public Photo selectPhoto(int pno) {
        return photoMapper.selectPhoto(pno);
    }

    @Override
    public int updatePhoto(Photo p) {
        return photoMapper.updatePhoto(p);
    }

    @Override
    public int deletePhoto(int pno) {
        return photoMapper.deletePhoto(pno);
    }
}
