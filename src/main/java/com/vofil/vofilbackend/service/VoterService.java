package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class VoterService {
    VoterRepository voterRepository;
    VoteRepository voteRepository;
    public VoterService(VoterRepository voterRepository,VoteRepository voteRepository) {
        this.voterRepository=voterRepository;
        this.voteRepository=voteRepository;
    }
    public ResponseEntity createVoter(Voter voter){
        String id=voter.getUser_id();//투표를 하는 사람 아이디
        String createId=voteRepository.getVote(voter.getVote_id()).getUser_id();//투표를 만든 사람 아이디
        boolean checking=voterRepository.findByVoterId(voter.getUser_id(),voter.getVote_id());//투표를 이미했는 지 여부
        if(id.equals(createId)||checking==false){ //if로 있는 지 확인
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 사용자는 투표를 할 수 없습니다");
        }

        voterRepository.save(voter);
        voteRepository.check(voter.getVote_id());
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
