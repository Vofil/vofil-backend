package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.MainpageRepository;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class MainpageService {
    MainpageRepository mainpageRepository;
    public MainpageService(MainpageRepository mainpageRepository) {
        this.mainpageRepository=mainpageRepository;
    }

    // 최신 투표 리턴
    public List<SimpleVoteInformation> getLatestVotes(String user_id){
        List<Vote> votes = mainpageRepository.getLatestVotes(user_id);
        return mainpageRepository.getSimpleVoteInformationList(votes);
    }
}
