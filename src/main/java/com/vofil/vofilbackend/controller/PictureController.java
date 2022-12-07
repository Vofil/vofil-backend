package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.service.PictureService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api/pictures")
public class PictureController {
    @Autowired
    PictureService pictureService;

    // 로컬의 사진 경로는 PictureController, PictureRepository 값만 바꾸면 됩니다! 하드코딩 말고 PICTURE_PATH 변수 이용하기!!
    public final String PICTURE_PATH = "/Users/82106/file/";//C:\Users\82106\file


    @PostMapping("")
    public ResponseEntity createPicture(@RequestBody Picture picture){//id만 채우고 나머지는 null로 일단 받아서 객체 만들기
        return pictureService.createPicture(picture);
    }
    //@PutMapping(value = "", params = {"file","id", "cnt"})
    @GetMapping("/clock")
    public ResponseEntity addFile(@RequestParam("file") MultipartFile file, @RequestParam int id, @RequestParam int cnt) throws IOException{
        System.out.println("확인");

        if(!file.isEmpty()){
            String fullPath=PICTURE_PATH+file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return pictureService.update(id,file.getOriginalFilename(),cnt);
    }
    @GetMapping("/checking1")
    public ResponseEntity addFiles(@RequestBody MultipartFile file,@RequestBody Picture picture) throws IOException{
        if(!file.isEmpty()){
            String fullPath= PICTURE_PATH +file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        int id=picture.getId(); int cnt=0;

        String one= picture.getRe1(); String two=picture.getRe2();
        String three=picture.getRe3(); String four= picture.getRe4();
        if(one!=null)
            cnt=1;
        else if(two!=null)
            cnt=2;
        else if(three!=null)
            cnt=3;
        else
            cnt=4;

        return pictureService.update(id,file.getOriginalFilename(),cnt);
    }
    @GetMapping(value="/FullViews", produces= MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getShowed(@RequestParam int id, @RequestParam int cnt){
        byte[] hi=pictureService.showedFile(id,cnt);

        return hi;
    }
    @GetMapping(value="/FullView", produces= MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam int id, @RequestParam int cnt)
            throws IOException{
        String value=pictureService.showFile(id, cnt);
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String fileDir = PICTURE_PATH + value; // 파일경로

        try{
            fis = new FileInputStream(fileDir);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
    @PostMapping("/checking")
    public ResponseEntity addFiles(HttpServletRequest request, @RequestParam(value="file",required = false) MultipartFile file,
                                   @RequestParam(value="id",required = false) int id,
                                   @RequestParam(value="cnt",required = false)int cnt) throws IOException{

        if(!file.isEmpty()){
            String fullPath=PICTURE_PATH+file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }

        return pictureService.update(id,file.getOriginalFilename(),cnt);
    }
    @GetMapping("/showing")
    public ResponseEntity showing(@RequestParam int id, @RequestParam int cnt){
        return pictureService.showing(id,cnt);
    }
    @PostMapping("/add")
    public ResponseEntity addFile(@RequestParam MultipartHttpServletRequest multipartRequest, @RequestParam int id, @RequestParam int cnt) throws IOException{

        MultipartFile file=multipartRequest.getFile("blob");

        return pictureService.update(id,file.getOriginalFilename(),cnt);
    }
    @GetMapping("/checked")
    public ResponseEntity addFile(@RequestParam String file, @RequestParam int id, @RequestParam int cnt) throws IOException{

        //MultipartFile file=multipartRequest.getFile("blob");

        return pictureService.update(id,file,cnt);
    }
    @GetMapping("/show")
    public String showFile(@RequestParam int id, @RequestParam int cnt){
        return pictureService.showFile(id, cnt);
    }
    @GetMapping("/confirm")
    public ResponseEntity getPictures(@RequestParam int id){
        return ResponseEntity.ok().body(pictureService.getPicture(id));
    }
    @GetMapping("")
    public ResponseEntity getAllPictures(){
        return ResponseEntity.ok().body(pictureService.getAllPicture());
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
