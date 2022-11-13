package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {
    @Autowired
    PictureService pictureService;

    @PostMapping("")
    public ResponseEntity createPicture(@RequestBody Picture picture){//id만 채우고 나머지는 null로 일단 받아서 객체 만들기
        return pictureService.createPicture(picture);
    }

    @GetMapping("")
    public ResponseEntity addFile(@RequestParam MultipartFile file, @RequestParam int id, @RequestParam int cnt) throws IOException{

        if(!file.isEmpty()){
            String fullPath="/Users/82106/file/"+file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return pictureService.update(id,file.getOriginalFilename(),cnt);
    }
    @GetMapping("/show")
    public ResponseEntity showFile(@RequestParam int id, @RequestParam int cnt){
        return pictureService.showFile(id, cnt);
    }
    @PostMapping("/photo")
    public ResponseEntity uploadPic(@RequestParam MultipartFile upload, @RequestParam HttpServletRequest request,@RequestParam int id,@RequestParam int cnt){
        String saveDir=request.getSession().getServletContext().getRealPath("/resources/upload/file");
        File dir=new File(saveDir);
        if(!dir.exists()){
            dir.mkdir();
        }
        String re="0";
        String orifileName=upload.getOriginalFilename();

        try {
            upload.transferTo(new File(saveDir + "/" + orifileName));
        }catch(IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return pictureService.update(id, orifileName,cnt);
    }
//
//    @PostMapping("/photos")
//    public ResponseEntity uploadFile(@RequestParam MultipartFile[] upload, @RequestParam HttpServletRequest request,@RequestParam int id){
//        String saveDir=request.getSession().getServletContext().getRealPath("/resources/upload/file");
//        File dir=new File(saveDir);
//        if(!dir.exists()){
//            dir.mkdir();
//        }
//        int cntPic=1;
//        String re1="0"; String re2="0"; String re3="0"; String re4="0";
//
//        for(MultipartFile f: upload){
//            if(!f.isEmpty()){
//                String orifileName=f.getOriginalFilename();
//                String ext=orifileName.substring(orifileName.lastIndexOf("."));
//
//
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
//                int rand=(int)(Math.random()*1000);
//                String reName=orifileName;
//                String reName1=sdf.format(System.currentTimeMillis())+"_"+rand+ext;
//
//                //string 만들어짐
//                if(cntPic==1){
//                    reName=re1;cntPic++;
//                }
//                else if(cntPic==2){
//                    reName=re2;cntPic++;
//                }
//                else if(cntPic==3){
//                    reName=re3;cntPic++;
//                }
//                else if(cntPic==4){
//                    reName=re4;cntPic++;
//                }
//
//                try {
//                    f.transferTo(new File(saveDir + "/" + reName));
//                }catch(IllegalStateException | IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        //return ResponseEntity.ok().body(pictureService.createPic(id, re1,re2,re3,re4));
//        return ResponseEntity.ok().body(id);
//    }

//    @PostMapping("")
//    public ResponseEntity createPicArr(@RequestParam int id, @RequestParam int cnt, @RequestParam String s){
//        return pictureService.updateArr(id,cnt,s);
//    }
//
//    @GetMapping("/photos")
//    public String test(){
//        return "vote-form";
//    }


}
