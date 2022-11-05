package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    @Autowired
    VoteService voteService;

    @PostMapping("")
    public ResponseEntity createVote(@RequestBody Vote vote){
        return voteService.createVote(vote);
    }

    @GetMapping("/{id}")
    public ResponseEntity extract(@PathVariable int id){
        return voteService.extract(id);
    }
    @GetMapping("/update")
    public ResponseEntity updateFinal(@PathVariable int id){
        return voteService.extract(id);
    }
    @GetMapping("")
    public ResponseEntity getAllVotes(){
        return ResponseEntity.ok().body(voteService.getAllVotes());
    }

    @GetMapping("/title")
    public ResponseEntity toString(@RequestBody Vote vote){
        return ResponseEntity.ok().body(voteService.toString(vote));
    }


    /*
    @GetMapping("/votes/photos")
    public String test(){
        return "vote-form";
    }
    @PostMapping("/votes")
    public String addFile(@RequestParam MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            String fullPath="/Users/82106/file/"+file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return "vote-form";
    }
*/
}
