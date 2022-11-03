package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.service.VoteService;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.service.VoterService;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VoterRepository {
    private final EntityManager em;
    public VoterRepository(EntityManager em) {
        this.em = em;
    }
    //private static Map<Integer, Voter> store = new HashMap<>();

    public Voter save(Voter voter) {
        //VoteService voteService=new VoteService(new VoteRepository(em));
        //VoterService voterService=new VoterService(new VoterRepository(em));
        //int[] result2=voterService.extract(voter.getVote_id());
        //voter.setFinal_result(result2);
        //store.put(voter.getVote_id(), voter);
        em.persist(voter);
        return voter;
    }

    public Optional<Voter> findById(int id){
        Voter voter= em.find(Voter.class,id);
        return Optional.ofNullable(voter);
    }

    //그 투표 id에 대해 투표한 user id 리스트 만들기, 투표값array초기화, 투표값array 값 반복문을 통해 넣기
    public List<Voter> findResult(int id){
        return em.createQuery("select u from Voter u where u.Vote_id = :id",Voter.class).setParameter("id",id).getResultList();
    }

}
