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

import java.io.File;
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

    @GetMapping("detail.ph")
    public String selectPhotoDetail(int pno, Model model) {
        int result = photoService.increaseCount(pno);

        if(result > 0){
            Photo p = photoService.selectPhoto(pno);
            model.addAttribute("p", p);
            return "photo/photoDetailView";
        } else{
            model.addAttribute("errorMsg","사진게시글 조회 실패");
            return "common/errorPage";
        }

    }

    @GetMapping("updateForm.ph")
    public String updatePhoto(@RequestParam(value = "pno") int pno, Model model) {
        model.addAttribute("p",photoService.selectPhoto(pno));
        return "photo/photoUpdateForm";
    }

    @PostMapping("update.ph")
    public String updatePhoto(@ModelAttribute Photo p, MultipartFile reupfile, HttpSession session, Model model) {
        //새로운 첨부파일 있다면 저장 후 p객체에 파일명 수정
        //p객체 전달받은 값으로 수정

        //새로운 첨부파일이 있는가?
        if(!reupfile.getOriginalFilename().equals("")){
            //기존첨부파일 삭제
            if(p.getChangeName() != null && !p.getChangeName().equals("")){
                new File(session.getServletContext().getRealPath(p.getChangeName())).delete();
            }

            String changeName = Template.saveFile(reupfile, session, "/resources/uploadfile/");

            p.setChangeName("/resources/uploadfile/" + changeName);
            p.setOriginName(reupfile.getOriginalFilename());
        }

        int result = photoService.updatePhoto(p);

        if(result > 0){
            session.setAttribute("alertMsg", "사진게시글 수정 성공");
            return "redirect:/detail.ph?pno=" + p.getPhotoNo();
        } else {
            model.addAttribute("errorMsg", "사진게시글 수정 실패");
            return "common/errorPage";
        }
    }

    @GetMapping("delete.ph")
    public String deletePhoto(@RequestParam(value = "pno") int pno, HttpSession session, Model model) {
        int result = photoService.deletePhoto(pno);

        if(result > 0){
            session.removeAttribute("p"); //여기
            session.setAttribute("alertMsg", "사진게시글 삭제 성공");
            return "redirect:/list.ph";
        } else {
            model.addAttribute("errorMsg", "사진게시글 삭제 실패");
            return "common/errorPage";
        }
    }

}
