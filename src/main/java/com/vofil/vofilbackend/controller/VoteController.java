package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    @Autowired
    VoteService voteService;

    @PostMapping("")
    public ResponseEntity createVote(@RequestBody Vote vote){
        return voteService.createVote(vote);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity extract(@PathVariable int id){
//        return voteService.extract(id);
//    }
    @GetMapping("/update")
    public ResponseEntity updateFinal(@RequestParam int id){
        return voteService.extract(id);
    }
    @GetMapping("/shrink")
    public ResponseEntity ShrinkCnt(@RequestParam int id){
        return voteService.shrinking(id);
    }
    @GetMapping("")
    public ResponseEntity getAllVotes(){
        return ResponseEntity.ok().body(voteService.getAllVotes());
    }

////    @GetMapping("/title")
//    public ResponseEntity toString(@RequestBody Vote vote){
//        return ResponseEntity.ok().body(voteService.toString(vote));
//    }
    @GetMapping("/result")
    public ResponseEntity showResult(@RequestParam int id,@RequestParam int cnt){
        //updateFinal(id);
        return voteService.showResult(id, cnt);
    }

    @PutMapping(value = "/ttt", params = "voteId")
    public void updateUserTitleAndKeyword(@RequestParam int voteId) {
        voteService.updateUserTitleAndKeyword(voteId);
    }



//    @GetMapping("/photos")
//    public String test(){
//        return "vote-form";
//    }
//    @PostMapping("/photos")
//    public ResponseEntity uploadFile(MultipartFile[] upload, HttpServletRequest request,@PathVariable int id){
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
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
//                int rand=(int)(Math.random()*1000);
//
//                String reName=sdf.format(System.currentTimeMillis())+"_"+rand+ext;
//
//                //string 만들어짐
//                if(cntPic==1){
//                    reName=re1;
//                    cntPic++;
//                }
//                else if(cntPic==2){
//                    reName=re2;
//                    cntPic++;
//                }
//                else if(cntPic==3){
//                    reName=re3;
//                    cntPic++;
//                }
//                else if(cntPic==4){
//                    reName=re4;
//                    cntPic++;
//                }
//
//
//                try {
//                    f.transferTo(new File(saveDir + "/" + reName));
//                }catch(IllegalStateException|IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        return ResponseEntity.ok().body(voteService.FileStore(id,re1,re2,re3,re4));
//    }
//    public String addFile(@RequestParam MultipartFile file) throws IOException{
//        if(!file.isEmpty()){
//            String fullPath="/Users/82106/file/"+file.getOriginalFilename();
//            file.transferTo(new File(fullPath));
//        }
//        return "vote-form";
//    }

}
