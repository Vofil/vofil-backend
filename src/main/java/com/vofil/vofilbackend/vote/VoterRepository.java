package com.vofil.vofilbackend.vote;

import com.vofil.vofilbackend.domain.User;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoterRepository {
    private final EntityManager em;
    public VoterRepository(EntityManager em) {
        this.em = em;
    }

    private static Map<Integer, Voter> store = new HashMap<>();

    public Voter save(Voter voter) {//이거는 일반투표일때 결과 array 저장방식, 태그투표일때는 따로 만들어야함!!!!!!!
        VoteService voteService=new VoteService();
        int[] result2=voteService.extract(voter.getVote_id());
        voter.setResult(result2);
        store.put(voter.getVote_id(), voter);
        return voter;
    }

    public static Voter findById(int id) {
        return store.get(id);
    }

    //그 투표 id에 대해 투표한 user id 리스트 만들기, 투표값array초기화, 투표값array 값 반복문을 통해 넣기
    public List<Voter> findResult(int id){
        return em.createQuery("select u from Voter u where u.Voter_id = :id",Voter.class).setParameter("id",id).getResultList();
    }

}
