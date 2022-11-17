package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.MainpageRepository;
import com.vofil.vofilbackend.repository.UserRepository;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Transactional
public class MainpageService {
    MainpageRepository mainpageRepository;
    @Autowired
    private UserRepository userRepository;

    public MainpageService(MainpageRepository mainpageRepository) {
        this.mainpageRepository=mainpageRepository;
    }
    public final int MAIN_SHOW_NUMBER = 5; // mainpage, mypage 에서 한번에 보여주는 사진의 개수

    // 최신 투표 리턴
    public List<SimpleVoteInformation> getLatestVotes(String user_id){
        List<Vote> allList = mainpageRepository.getLatestVotes(user_id);

        List<Vote> lastList = new ArrayList<>();
        for (int i = 1; i<MAIN_SHOW_NUMBER+1; i++){
            if (allList.size()-i <0) break;
            lastList.add(allList.get(allList.size()-i));
        }
        return mainpageRepository.getSimpleVoteInformationList(lastList);
    }

    // 맞춤 투표 리턴 - 대표키워드 우선, 그뒤에는 shuffle
    public List<SimpleVoteInformation> getCustomVotes(String user_id){
        List<Vote> votes = mainpageRepository.getLatestVotes(user_id);
        List<Vote> customVotes = new ArrayList<>();

        String userKeyword = userRepository.findById(user_id).get().getKeyword();
        for (int i = votes.size()-1 ; i>=0; i--){
            Vote nowVote = votes.get(i);
            if (nowVote.getFeeling() == userKeyword) {
                // 맞춤형
                customVotes.add(nowVote);
                votes.remove(i);
            }
        }
        Collections.reverse(customVotes); // 거꾸로된거 돌리기, 즉 최신거가 앞에 오도록
        Collections.shuffle(votes); // 그 외의 투표들은 셔플
        // 뒤에 합치기 - 토탈 MAIN_SHOW_NUMBER 되도록
        for (int i = 0; i < MAIN_SHOW_NUMBER - customVotes.size(); i++) {
            if (i>= votes.size()) break;
            customVotes.add(votes.get(i));
        }

        return mainpageRepository.getSimpleVoteInformationList(customVotes);
    }
}
