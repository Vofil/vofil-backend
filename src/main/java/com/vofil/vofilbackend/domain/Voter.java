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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int Vote_id;
    private String User_id;
    private int result1;
    private int result2;
    private int result3;
    private int result4;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getResult1() {
        return result1;
    }

    public void setResult1(int result1) {
        this.result1 = result1;
    }

    public int getResult2() {
        return result2;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public int getResult3() {
        return result3;
    }

    public void setResult3(int result3) {
        this.result3 = result3;
    }

    public int getResult4() {
        return result4;
    }

    public void setResult4(int result4) {
        this.result4 = result4;
    }


    /*public Voter(int Vote_id, String User_id, int[] result,int[] final_result){
        this.Vote_id=Vote_id;
        this.User_id=User_id;
        this.result=result;
        this.final_result=final_result;
    }*/
}
