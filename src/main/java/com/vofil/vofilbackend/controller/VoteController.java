package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Graph;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.service.UserService;
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
import java.util.List;

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

    // ttt : 칭호 갱신하는 api
    @PutMapping(value = "/ttt", params = "voteId")
    public void updateUserTitleAndKeyword(@RequestParam int voteId) {
        voteService.updateUserTitleAndKeyword(voteId);
    }

    @GetMapping("/confirm")
    public ResponseEntity getVotes(@RequestParam int id){
        return ResponseEntity.ok().body(voteService.getVote(id));
    }

    @GetMapping("/tagGraph")
    public List<Graph> getTagGraph(@RequestParam int id, @RequestParam int cnt){
        return voteService.getTagGraph(id,cnt);
    }
    @GetMapping("/graph")
    public List<Graph> getGraph(@RequestParam int id, @RequestParam int cnt){
        return voteService.getGraph(id,cnt);
    }
    @GetMapping("/graphAge")
    public List<Graph> getAge(@RequestParam int id, @RequestParam int cnt){
        return voteService.getAge(id,cnt);
    }

    @GetMapping("/graphNick")
    public List<Graph> getNick(@RequestParam int id, @RequestParam int cnt,@RequestParam int kind){
        return voteService.getNickName(id,cnt,kind);
    }


    // reraise : user_id로 안끝난 투표 랜덤하게 선택
    //              service 아래단 -> 투표id로 다시 최상단으로 끌올하는 기능 (포인트 사용)

    @Autowired
    UserService userService;
    private final int RERAISE_COST = 50;
    @GetMapping(value = "/reraise", params = "userId")
    public int reraise(@RequestParam String userId) {
        if (userService.minusPointsIfHave(RERAISE_COST, userId)) {
            int randomVoteId = voteService.getRandomOngoingVoteID(userId);
            return voteService.reraise(randomVoteId); // 있으면 그 번호, 없으면 -1
        }
        else {
            return -RERAISE_COST; // 포인트가 부족하면 -50
        }

    }

}
