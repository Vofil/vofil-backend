package com.vofil.vofilbackend.domain;

import com.vofil.vofilbackend.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Voter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int Vote_id;
    private String User_id;
    private int[] result;
    private int[] final_result;

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

    public int[] getFinal_result() {
        return final_result;
    }

    public void setFinal_result(int[] final_result) {
        this.final_result = final_result;
    }

    //결과값에서 최종 결과 산출



    /*public Voter(int Vote_id, String User_id, int[] result,int[] final_result){
        this.Vote_id=Vote_id;
        this.User_id=User_id;
        this.result=result;
        this.final_result=final_result;
    }*/
}
