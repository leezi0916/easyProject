package com.kh.boot.controller;
import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.domain.vo.Photo;
import com.kh.boot.service.PhotoService;
import com.kh.boot.utils.Template;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("list.ph")
    public  String selectPhotoList(@RequestParam(defaultValue = "1") int cpage, Model model) {
        int photoCount = photoService.selectPhotoCount();

        PageInfo pi = new PageInfo(photoCount, cpage, 10, 5);

        ArrayList<Photo> list = photoService.selectPhotoList(pi);
        
        model.addAttribute("list", list);
        model.addAttribute("pi", pi);
        return "photo/photoListView";
    }

    @GetMapping("/enrollForm.ph")
    public String enrollForm() {

        return "photo/photoEnrollForm"; // 사진 업로드 페이지
    }

    @PostMapping("insert.ph")
    public String insertPhoto(@ModelAttribute Photo photo, MultipartFile upfile, HttpSession session, Model model) {
        System.out.println(photo);
        System.out.println(upfile);


        if(!upfile.getOriginalFilename().equals("")){
            String changeName = Template.saveFile(upfile, session, "/resources/uploadfile/");
            photo.setChangeName("/resources/uploadfile/" + changeName);
            photo.setOriginName(upfile.getOriginalFilename());
        }
        int result = photoService.insertPhoto(photo);

        if (result > 0) {
            return "redirect:/list.ph"; // 성공 시 리스트 페이지로 이동
        } else {
            model.addAttribute("errorMsg", "사진 등록에 실패했습니다.");
            return "common/errorPage"; // 에러 페이지로 이동
        }
    }
}
