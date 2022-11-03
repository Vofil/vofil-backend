package com.vofil.vofilbackend.VoteTest;

import com.vofil.vofilbackend.vote.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class VoteIntegrationTest {
    /*
    //회원서비스 객체 생성
    //투표 생성, 투표하기, 투표 결과, 포인트
    //private final VoterRepository voterRepository;
    //VoteRegister(VoterRepository voterRepository){
    //    this.voterRepository=voterRepository;
    //}
    VoteService voteService=new VoteService();
    //public Vote createVote(Long id, int gender, int age, VoteCaregory category, int kind, VoteFeeling title, int ending_point)
    @Test
    void createVote(){
        Vote vote=voteService.createVote("1",3,2,"Kprofil",0,"대학오티",100,4,"예쁘다");
        //Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(vote.toString()).isEqualTo("대학오티에 어울리는 사진을 골라주세요");
    }
    @Test
    void voteVote(){//final_pic="설정태그와 값이 같은 사진은 "+max+"번째 사진입니다.";final_pic=max+"번째 사진이 투표수가 가장 많습니다";
        //Vote vote=voteService.createVote("1",3,2,"Kprofil",0,"대학오티",100,4,"예쁘다");
        //VoteRepository voteRepository=new VoteRepository();
       // voteRepository.save(vote);
        //Assertions.assertThat(vote.getId()).isEqualTo(4);
        //int[] result={100,80,0,20};//만드는 법 추가 필요!
        //Voter voter=voteService.voteVote(4,"hi",result);
        //this.voterRepository.save(voter);
        //Assertions.assertThat(voter.resulting()).isEqualTo("1번째 사진이 투표수가 가장 많습니다");
    }
    */

}
