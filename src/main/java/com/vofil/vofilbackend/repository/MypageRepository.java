package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MypageRepository {
    @Autowired
    private UserRepository userRepository;
    private final EntityManager em;
    public MypageRepository(EntityManager em) {
        this.em = em;
    }

    // Vote (자기가 만든) 리턴
    public List<Vote> getUserVotes(String user_id){
        User user = userRepository.findById(user_id).get();
        return em.createQuery("select v from Vote v " +
                        "where v.user_id = :user_id", Vote.class)
                .setParameter("user_id",user_id)
                .getResultList();
    }

    // Voter (내가 투표해준) 리턴
    public List<Vote> getUserVoters(String user_id){

        // 카디샨곱으로 해야 order by가 먹음. in 으로 subquery 시 order 안됨.
        List<Vote> v = em.createQuery( "select v from Vote v, Voter vr where v.id=vr.Vote_id and" +
                        " vr.User_id = :user_id order by vr.id desc", Vote.class)
                .setParameter("user_id",user_id)
                .getResultList();

        return v;
    }

}
