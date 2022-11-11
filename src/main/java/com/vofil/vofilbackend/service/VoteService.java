package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.vote.TagList;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.vote.VoteCaregory;
import com.vofil.vofilbackend.vote.VoteFeeling;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class VoteService {//vote repository에는 vote 정보만 따로 repository 만들어서 거기는 id 등만
    VoteRepository voteRepository;
    VoterRepository voterRepository;
    public VoteService(VoteRepository voteRepository,VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository=voterRepository;
    }
    public List<Vote> getAllVotes() {
        return voteRepository.getAllVotes();
    }
    public ResponseEntity createVote(Vote vote){
//         vote.setCategorying(VoteCaregory.valueOf(vote.getCategorying()));
//         vote.setFeeling(VoteFeeling.valueOf(vote.getFeeling()));
//         vote.setTagList(TagList.valueOf(vote.getTaging()));

        voteRepository.save(vote);
        return ResponseEntity.ok().body(vote.getId());//투표 id
    }

    public ResponseEntity toString(Vote vote){
        VoteFeeling titles=VoteFeeling.valueOf(vote.getFeeling());
        String check=titles.toString();
        if (titles.number()<10){
            check=check+"에 어울리는 사진을 골라주세요";
        }
        else if(titles.number()<100){
            check=check+"느낌의 사진을 골라주세요";
        }
        else{
            check=check+"에 적합한 사진을 골라주세요";
        }
        return ResponseEntity.ok().body(check);
    }
//    public ResponseEntity FileStore(int id, String re1, String re2, String re3, String re4){
////        Optional<Vote> cnt1=voteRepository.findById(id);
////        Vote cnt= cnt1.get();
////
////        cnt.setRe1(re1);
////        cnt.setRe2(re2);
////        cnt.setRe3(re3);
////        cnt.setRe4(re4);
//
//        voteRepository.updatePic(id, re1,re2,re3,re4);
//        return ResponseEntity.ok().body(id);
//    }
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
                int SettingNum=TagList.valueOf(cnt.getTaging()).number();
                if(SettingNum==finding.get(i).getResult1())
                    N_result1+=finding.get(i).getResult1();

                if(SettingNum==finding.get(i).getResult2())
                    N_result2+=finding.get(i).getResult2();

                if(SettingNum==finding.get(i).getResult3())
                    N_result3+=finding.get(i).getResult3();

                if(SettingNum==finding.get(i).getResult4())
                    N_result4+=finding.get(i).getResult4();

            }

        }
//        cnt.setResult1(N_result1);
//        cnt.setResult2(N_result2);
//        cnt.setResult3(N_result3);
//        cnt.setResult4(N_result4);
        voteRepository.updateFinal(id, N_result1,N_result2,N_result3,N_result4);
        return ResponseEntity.ok().body(cnt.getId());
    }

}
/*public Vote save(Vote vote) {//VoteCaregory check2=VoteCaregory.valueOf(category);
        //VoteFeeling check1=VoteFeeling.valueOf(title);
        //vote.setId(++sequence);
        //store.put(vote.getId(), vote);
        vote.setCategory(VoteCaregory.valueOf(vote.getCategorying()));
        vote.setTitle(VoteFeeling.valueOf(vote.getFeeling()));
        vote.setTagList(TagList.valueOf(vote.getTaging()));
        //em.persist(vote);
        return vote;
    }*/
    /*public Voter saving(Voter voter) {
        //VoteService voteService=new VoteService(new VoteRepository(em));
        //VoterService voterService=new VoterService(new VoterRepository(em));
        int[] result2=this.extract(voter.getVote_id());
        voter.setFinal_result(result2);

        return voter;
    }*/
