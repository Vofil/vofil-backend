package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.service.MainpageService;
import com.vofil.vofilbackend.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mainpage")
public class MainpageController {
    @Autowired
    MainpageService mainpageService;

    // 최신 투표 리턴
    @GetMapping(value = "latest", params = "user_id")
    public List<SimpleVoteInformation> getLatestVotes(String user_id){
        return mainpageService.getLatestVotes(user_id);
    }

    // 맞춤 투표 리턴
    @GetMapping(value = "custom", params = "user_id")
    public List<SimpleVoteInformation> getCustomVotes(String user_id){
        return mainpageService.getCustomVotes(user_id);
    }
}
