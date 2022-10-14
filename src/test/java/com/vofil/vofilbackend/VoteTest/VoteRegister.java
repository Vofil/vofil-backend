package com.vofil.vofilbackend.VoteTest;

import com.vofil.vofilbackend.vote.Vote;
import com.vofil.vofilbackend.vote.VoteCaregory;
import com.vofil.vofilbackend.vote.VoteFeeling;
import com.vofil.vofilbackend.vote.VoteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class VoteRegister {
    //회원서비스 객체 생ㄹ성
    VoteService voteService=new VoteService();
    //public Vote createVote(Long id, int gender, int age, VoteCaregory category, int kind, VoteFeeling title, int ending_point)
    @Test
    void createVote(){
        Vote vote=voteService.createVote(1L,3,1,VoteCaregory.Kprofil,0,VoteFeeling.대학오티,100);
        //Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(vote.toString()).isEqualTo("대학오티에 어울리는 사진을 골라주세요");
    }
}
