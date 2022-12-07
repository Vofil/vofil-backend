package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.repository.UserRepository;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.vote.TagList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class VoterService {
    VoterRepository voterRepository;
    VoteRepository voteRepository;

    UserRepository userRepository;

    public VoterService(VoterRepository voterRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.voterRepository = voterRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }
    public ResponseEntity confirmBool(String id,int Vid){//id 투표를 하는 사람 아이디, Vid 해당 투표 id
        //String id=voter.getUser_id();//투표를 하는 사람 아이디
        String createId=voteRepository.getVote(Vid).getUser_id();//투표를 만든 사람 아이디
        boolean checking=voterRepository.findByVoterId(id,Vid);//투표를 이미했는 지 여부
        if(id.equals(createId)||checking==false){ //if로 있는 지 확인
            return ResponseEntity.ok().body(false);//투표결과 페이지로
        }
        return ResponseEntity.ok().body(true);
    }
    public ResponseEntity createVoter(Voter voter){
        // 몇 포인트 줄지에 대한 변수
        final int VOTER_POINT = 1;

        String id = voter.getUser_id(); //투표를 하는 사람 아이디
        String createId=voteRepository.getVote(voter.getVote_id()).getUser_id();//투표를 만든 사람 아이디
        boolean checking=voterRepository.findByVoterId(voter.getUser_id(),voter.getVote_id());//투표를 이미했는 지 여부
        if(id.equals(createId)||checking==false){ //if로 있는 지 확인
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 사용자는 투표를 할 수 없습니다");
        }

        voterRepository.save(voter);
        extract(voter.getVote_id());
        voteRepository.check(voter.getVote_id());
        // 포인트도 지급하기 !
        userRepository.givePoint(voter.getUser_id(), (long) VOTER_POINT);

        return ResponseEntity.ok().body(voter.getId());
    }
    public ResponseEntity extract(int id){//투표 id가 인자
        List<Voter> finding=voterRepository.findResult(id);
        Optional<Vote> cnt1=voteRepository.findById(id);
        Vote cnt= cnt1.get();
        int N_result1=0;
        int N_result2=0;
        int N_result3=0;
        int N_result4=0;

        if(cnt.getKind()==0){//일반
            for(int i=0;i<finding.size();i++){
                N_result1+=finding.get(i).getResult1();
                N_result2+=finding.get(i).getResult2();
                N_result3+=finding.get(i).getResult3();
                N_result4+=finding.get(i).getResult4();
            }
        }
        else{//태그
            for(int i=0;i<finding.size();i++){
                int SettingNum= TagList.valueOf(cnt.getTaging()).number();
                if(SettingNum==finding.get(i).getResult1())
                    N_result1+=1;

                if(SettingNum==finding.get(i).getResult2())
                    N_result2+=1;

                if(SettingNum==finding.get(i).getResult3())
                    N_result3+=1;

                if(SettingNum==finding.get(i).getResult4())
                    N_result4+=1;

            }

        }
        voteRepository.updateFinal(id, N_result1,N_result2,N_result3,N_result4);
        return ResponseEntity.ok().body(cnt.getId());
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
