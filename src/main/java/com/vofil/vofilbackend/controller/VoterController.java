package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voters")
public class VoterController {
    @Autowired
    VoterService voterService;
    @GetMapping("/bool")
    public ResponseEntity confirmBool(@RequestParam String id, int Vid){
        return voterService.confirmBool(id, Vid);
    }
    @PostMapping("")
    public ResponseEntity createVoter(@RequestBody Voter voter){
        return voterService.createVoter(voter);
    }
    @GetMapping("")
    public ResponseEntity getAllVoters(){
        return ResponseEntity.ok().body(voterService.getAllVoters());
    }
}
