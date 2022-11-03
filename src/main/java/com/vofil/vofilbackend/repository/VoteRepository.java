package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.Vote;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class VoteRepository {
    private final EntityManager em;
    public VoteRepository(EntityManager em){
        this.em=em;
    }
    //private static Map<Integer, Vote> store = new HashMap<>();
    //private static int sequence=0;
    public Vote save(Vote vote) {//VoteCaregory check2=VoteCaregory.valueOf(category);
        //VoteFeeling check1=VoteFeeling.valueOf(title);
        //vote.setId(++sequence);
        //store.put(vote.getId(), vote);
        //vote.setCategory(VoteCaregory.valueOf(vote.getCategorying()));
        //vote.setTitle(VoteFeeling.valueOf(vote.getFeeling()));
        //vote.setTagList(TagList.valueOf(vote.getTaging()));
        em.persist(vote);
        return vote;
    }

    /*public static Vote findById(int id) {
        return store.get(id);
    }*/
    public Optional<Vote> findById(int id){
        Vote vote= em.find(Vote.class,id);
        return Optional.ofNullable(vote);
    }
    public List<Vote> getAllVotes(){
        return em.createQuery("select u from Vote u",Vote.class)
                .getResultList();
    }
}
