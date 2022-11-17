package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.MypageUserInformation;
import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.repository.MainpageRepository;
import com.vofil.vofilbackend.repository.MypageRepository;
import com.vofil.vofilbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
public class MypageService {
    MypageRepository mypageRepository;
    @Autowired
    private MainpageRepository mainpageRepository;
    @Autowired
    private UserRepository userRepository;

    public MypageService(MypageRepository mypageRepository) {
        this.mypageRepository=mypageRepository;
    }
    public final int MAIN_SHOW_NUMBER = 5; // mainpage, mypage 에서 한번에 보여주는 사진의 개수

    // 내가만든 V 리스트 리턴
    public List<SimpleVoteInformation> getUserVotes(String user_id){
        List<Vote> votes = mypageRepository.getUserVotes(user_id);
        Collections.reverse(votes);
        return mainpageRepository.getSimpleVoteInformationList(votes);
    }

    // 내가해준 V 리스트 리턴
    public List<SimpleVoteInformation> getUserVoters(String user_id){
        List<Vote> votes = mypageRepository.getUserVoters(user_id);
        return mainpageRepository.getSimpleVoteInformationList(votes);
    }

    // MypageUserInformation
    public MypageUserInformation getUserInformation(String user_id) {
        User user = userRepository.findById(user_id).get();

        MypageUserInformation ui = new MypageUserInformation();
        ui.setId(user_id);
        ui.setName(user.getName());
        ui.setTitle(user.getTitle());
        ui.setGender(user.getGender());

        String birth = String.valueOf(user.getBirth_year()) +  "-" + String.valueOf(String.format("%02d", user.getBirth_month())) + "-"
                + String.valueOf(String.format("%02d", user.getBirth_day()));
        ui.setBirth(birth);
        return ui;
    }

    // 칭호만 리턴
    public String getUserTitle(String user_id) {
        User user = userRepository.findById(user_id).get();
        return user.getTitle();
    }

}
