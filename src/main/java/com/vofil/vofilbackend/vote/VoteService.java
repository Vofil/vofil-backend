package com.vofil.vofilbackend.vote;

public class VoteService {
    public Vote createVote(Long id,int gender, int age, VoteCaregory category, int kind, VoteFeeling title, int ending_point){
        //회원정보와 join 예정
        return new Vote(id,gender, age, category,kind, title, ending_point);
    }
}
