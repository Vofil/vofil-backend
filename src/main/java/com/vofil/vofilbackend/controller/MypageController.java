package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.MypageUserInformation;
import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.service.MainpageService;
import com.vofil.vofilbackend.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
public class MypageController {
    @Autowired
    MypageService mypageService;

    // 내가만든 투표 리턴 (t:Vote)
    @GetMapping(value = "vote", params = "user_id")
    public List<SimpleVoteInformation> getUserVotes(String user_id){
        return mypageService.getUserVotes(user_id);
    }

    // 내가해준 투표 리턴 (t:Voter)
    @GetMapping(value = "voter", params = "user_id")
    public List<SimpleVoteInformation> getUserVoters(String user_id){
        return mypageService.getUserVoters(user_id);
    }

    // 유저정보 리턴
    @GetMapping(value = "user/information", params = "user_id")
    public MypageUserInformation getUserInformation(String user_id) {
        return mypageService.getUserInformation(user_id);
    }
    
    // 유저 칭호만 리턴
    @GetMapping(value = "user/title", params = "user_id")
    public String getUserTitle(String user_id) {
        return mypageService.getUserTitle(user_id);
    }

}
