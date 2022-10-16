package com.vofil.vofilbackend.vote;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class VoteService {//vote repository에는 vote 정보만 따로 repository 만들어서 거기는 id 등만
    //private final EntityManager em;
    //private final UserRepository userRepository=new UserRepository(em);
    //public VoteSerive VoteRegi()
    VoterRepository voterRepository;
    VoteRepository voteRepository;
    public Vote createVote(String User_id,int gender, int age, String category, int kind, String title, int ending_point,int pic_cnt,String setting){
        //회원정보와 join 예정
        //Optional<User> foundUser=userRepository.findById(User_id);
        return new Vote(User_id,gender, age, category,kind, title, ending_point,pic_cnt,setting);
    }
    public Voter voteVote(int Vote_id, String User_id, int[] result){
        return new Voter(Vote_id,User_id, result);
    }
    public int[] extract(int id){
        List<Voter> finding=voterRepository.findResult(id);
        Vote cnt=voteRepository.findById(id);
        int[] checking=new int[cnt.getPic_cnt()];
        for(int i=0;i<finding.size();i++){
            for(int j=0;j<checking.length;j++){
                int[] result1=finding.get(i).getResult();
                checking[j]+=result1[j];
            }
        }

        return checking;
    }
}
