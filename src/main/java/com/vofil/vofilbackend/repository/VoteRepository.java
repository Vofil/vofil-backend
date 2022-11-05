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
    public Vote save(Vote vote) {//VoteCaregory check2=VoteCaregory.valueOf(category)
        em.persist(vote);
        return vote;
    }
    public Optional<Vote> findById(int id){
        Vote vote= em.find(Vote.class,id);
        return Optional.ofNullable(vote);
    }
    //고치기
    public Vote updateFinal(int id,int result1,int result2, int result3, int result4){
        Vote vote= em.find(Vote.class,id);
        //em.createQuery("delete u from Vote u where u.id= :id",Vote.class).setParameter("id",id).executeUpdate();
        //save(vote1);
        em.createQuery("update Vote u set u.result1=:result1 where u.id=:id").setParameter(id,result1).executeUpdate();
        em.createQuery("update Vote u set u.result2=:result2 where u.id=:id").setParameter(id,result2).executeUpdate();
        em.createQuery("update Vote u set u.result3=:result3 where u.id=:id").setParameter(id,result3).executeUpdate();
        em.createQuery("update Vote u set u.result4=:result4 where u.id=:id").setParameter(id,result4).executeUpdate();
        return vote;
    }
    public List<Vote> getAllVotes(){
        return em.createQuery("select u from Vote u",Vote.class)
                .getResultList();
    }
    //VoteFeeling check1=VoteFeeling.valueOf(title);
    //vote.setId(++sequence);
    //store.put(vote.getId(), vote);
    //vote.setCategory(VoteCaregory.valueOf(vote.getCategorying()));
    //vote.setTitle(VoteFeeling.valueOf(vote.getFeeling()));
    //vote.setTagList(TagList.valueOf(vote.getTaging()));

    /*public static Vote findById(int id) {
        return store.get(id);
    }*/
}
