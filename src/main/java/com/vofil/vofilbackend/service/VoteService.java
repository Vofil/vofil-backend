package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.vote.TagList;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.vote.VoteCaregory;
import com.vofil.vofilbackend.vote.VoteFeeling;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity createVote(Vote vote){
        //Optional<User> foundUser=userRepository.findById(User_id);
        //return new Vote(User_id,gender, age, category,kind, title, ending_point,pic_cnt,setting);
        vote.setCategory(VoteCaregory.valueOf(vote.getCategorying()));
        vote.setTitle(VoteFeeling.valueOf(vote.getFeeling()));
        vote.setTagList(TagList.valueOf(vote.getTaging()));

        voteRepository.save(vote);
        return ResponseEntity.ok().body(vote.getId());//투표 id
    }
    public ResponseEntity toString(Vote vote){
        VoteFeeling titles=vote.getTitle();
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

    public ResponseEntity extract(int id){//투표 id가 인자
        List<Voter> finding=voterRepository.findResult(id);
        Optional<Vote> cnt1=voteRepository.findById(id);
        Vote cnt= cnt1.get();
        int[] checking=new int[cnt.getPic_cnt()];

        if(cnt.getKind()==0){//일반
            for(int i=0;i<finding.size();i++){
                int[] result1=finding.get(i).getResult();
                for(int j=0;j<checking.length;j++){
                    checking[j]+=result1[j];
                }
            }
        }
        else{//태그
            for(int i=0;i<finding.size();i++){
                int[] result1=finding.get(i).getResult();
                for(int j=0;j<checking.length;j++){
                    int SettingNum=cnt.getSetting().number();
                    if(result1[j]==SettingNum) {
                        checking[j]+=result1[j];
                    }
                }
            }

        }
        cnt.setFinal_result(checking);

        return ResponseEntity.ok().body(cnt.getId());
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

}
