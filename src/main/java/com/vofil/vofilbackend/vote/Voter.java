package com.vofil.vofilbackend.vote;

import com.vofil.vofilbackend.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

public class Voter {

    private int Vote_id;

    public int getVote_id() {
        return Vote_id;
    }

    public void setVote_id(int vote_id) {
        Vote_id = vote_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public int[] getResult() {
        return result;
    }
    //해당 투표 id로 투표한 사람에 대해서 투표값 array 를 만든다.
    //그 투표 id에 대해 투표한 user id 리스트 만들기, 투표값 array 초기화, 투표값 array 값 반복문을 통해 넣기
    public void setResult(int[] result) {
        this.result = result;
    }

    private String User_id;
    private int[] result;

    public Voter(int Vote_id, String User_id, int[] result){
        this.Vote_id=Vote_id;
        this.User_id=User_id;
        this.result=result;
    }

    //결과값에서 최종 결과 산출

    public String resulting(){//모든 결과값의 합으로 투표값 array 만들기
        String final_pic;//태그투표일때랑 일반투표일 때 바꿔서 만들기
        int max=0;
        for(int i=0;i<this.result.length;i++){
            if(result[max]<this.result[i])
                max=i;
        }
        max++;
        Vote vote=VoteRepository.findById(this.Vote_id);
        if(vote.getKind()==0){//일반투표
            final_pic=max+"번째 사진이 투표수가 가장 많습니다";
        }
        else{//태그투표
            final_pic="설정태그와 값이 같은 사진은 "+max+"번째 사진입니다.";
        }


        return final_pic;
    }

}
