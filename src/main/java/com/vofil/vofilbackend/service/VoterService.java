package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class VoterService {
    VoterRepository voterRepository;
    public VoterService(VoterRepository voterRepository) {
        this.voterRepository=voterRepository;
    }
    public ResponseEntity createVoter(Voter voter){
        voterRepository.save(voter);
        //sql update문 넣기!!
        return ResponseEntity.ok().body(voter.getId());
    }
    public List<Voter> getAllVoters() {
        return voterRepository.getAllVoters();
    }
    /*
    public String resulting(int id){//모든 결과값의 합으로 투표값 array 만들기
        Optional<Voter> cnt1=voterRepository.findById(id);
        Voter cnt= cnt1.get();
        String final_pic;//태그투표일때랑 일반투표일 때 바꿔서 만들기
        int max=0;
        int[] len=cnt.getResult();
        for(int i=0;i<len.length;i++){
            if(len[max]<len[i])
                max=i;
        }
        max++;
        Optional<Vote> vote1=voteRepository.findById(id);
        Vote vote=vote1.get();
        if(vote.getKind()==0){//일반투표
            final_pic=max+"번째 사진이 투표수가 가장 많습니다";
        }
        else{//태그투표
            final_pic="설정태그와 값이 같은 사진은 "+max+"번째 사진입니다.";
        }


        return final_pic;
    }*/
}
