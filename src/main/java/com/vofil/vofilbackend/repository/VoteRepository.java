package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.vote.VoteFeeling;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        Optional<Vote> cnt1=findById(id);
        Vote vote= cnt1.get();
        //em.createQuery("delete u from Vote u where u.id= :id",Vote.class).setParameter("id",id).executeUpdate();
        //save(vote1);
        //em.createQuery("update").
//        if(result1==1){
//            em.createQuery("update Vote u set u.result1=u.result1+1 where u.id=:id").setParameter("id",id).executeUpdate();
//        }
//        else if(result2==1){
//            em.createQuery("update Vote u set u.result2=u.result2+1 where u.id=:id").setParameter("id",id).executeUpdate();
//
//        }
//        else if(result3==1){
//            em.createQuery("update Vote u set u.result3=u.result3+1 where u.id=:id").setParameter("id",id).executeUpdate();
//
//        }
//        else if(result4==1){
//            em.createQuery("update Vote u set u.result4=u.result4+1 where u.id=:id").setParameter("id",id).executeUpdate();
//
//        }
//        TypedQuery<Vote> query=em.createQuery("update Vote u set u.result1=:result1 where u.id=:id",Vote.class);
//        query.setParameter("result1",result1);
//        query.setParameter("id",id).executeUpdate();
//        TypedQuery<Vote> query2=em.createQuery("update Vote u set u.result2=:result2 where u.id=:id",Vote.class);
//        query.setParameter("result2",result2);
//        query.setParameter("id",id).executeUpdate();
//        TypedQuery<Vote> query3=em.createQuery("update Vote u set u.result3=:result3 where u.id=:id",Vote.class);
//        query.setParameter("result3",result3);
//        query.setParameter("id",id).executeUpdate();
//        TypedQuery<Vote> query4=em.createQuery("update Vote u set u.result4=:result4 where u.id=:id",Vote.class);
//        query.setParameter("result4",result4);
//        query.setParameter("id",id).executeUpdate();
        em.createQuery("update Vote u set u.result1=:result1 where u.id=:id").setParameter("id",id).setParameter("result1",result1).executeUpdate();
        em.createQuery("update Vote u set u.result2=:result2 where u.id=:id").setParameter("id",id).setParameter("result2",result2).executeUpdate();
        em.createQuery("update Vote u set u.result3=:result3 where u.id=:id").setParameter("id",id).setParameter("result3",result3).executeUpdate();
        em.createQuery("update Vote u set u.result4=:result4 where u.id=:id").setParameter("id",id).setParameter("result4",result4).executeUpdate();
        return vote;
    }
//    public Vote updatePic(int id,String result1,String result2, String result3, String result4){
//        Vote vote= em.find(Vote.class,id);
//        //em.createQuery("delete u from Vote u where u.id= :id",Vote.class).setParameter("id",id).executeUpdate();
//        //save(vote1);
//        em.createQuery("update Vote u set u.re1=:result1 where u.id=:id").setParameter(id,result1).executeUpdate();
//        em.createQuery("update Vote u set u.re2=:result2 where u.id=:id").setParameter(id,result2).executeUpdate();
//        em.createQuery("update Vote u set u.re3=:result3 where u.id=:id").setParameter(id,result3).executeUpdate();
//        em.createQuery("update Vote u set u.re4=:result4 where u.id=:id").setParameter(id,result4).executeUpdate();
//        return vote;
//    }
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


    @Autowired
    VoterRepository voterRepository;

    // vote 하나가 끝나면, 그에 해당하는 voter들의 칭호를 업데이트한다.
    public void updateUserTitleAndKeyword(int voteId) {

        List<Voter> voters = voterRepository.findResult(voteId);
        // 그에 해당하는 voter들의 모든 칭호를 업데이트 (해당되는 애들만)
        for (int i = 0 ; i<voters.size() ; i++) {
            Voter nowVoter = voters.get(i);
            List<Voter> nowVoterResult = em.createQuery("select u from Voter u where u.User_id = :id",Voter.class).setParameter("id",nowVoter.getUser_id()).getResultList();


            // 매 X번 마다, 칭호를 업데이트한다.
            int XXX = 1;
            if (nowVoterResult.size() % XXX == 0) {
                // 대중, 독창 판단 변수
                int isMatched = 0; // 대중적인지 (결과랑 match 됐는지)
                int isNotMatched = 0; // 독창적인지 (결과랑 match 안됐을때)

                // 상황, 분위기, 종류 판별 변수
                int situationCnt = 0;
                int moodCnt = 0;
                int kindCnt = 0;

                String lastKeyword = "";
                // 각각의 vote에 대해, 1.대중/독창 계산 , 2.상황/분위기/종류 계산
                for (int j = 0 ; j<nowVoterResult.size() ; j++) {
                    Vote nowVoterVote = findById(nowVoterResult.get(j).getVote_id()).get(); // vote 추출

                    // 아직 안 끝난 투표는 패스
                    if (nowVoterVote.getEnding_point() != 0) continue;

                    // 1. 대중/독창 계산
                    int[] resultArray = new int[4];
                    resultArray[0] = nowVoterVote.getResult1();
                    resultArray[1] = nowVoterVote.getResult2();
                    resultArray[2] = nowVoterVote.getResult3();
                    resultArray[3] = nowVoterVote.getResult4();
                    int maxResult = -1;
                    for (int k = 0; k<4; k++) {
                        if (maxResult < resultArray[k]) maxResult = resultArray[k];
                    }
                    ArrayList<Integer> maxList = new ArrayList<>();
                    for (int k = 0; k<4; k++) {
                        if (maxResult == resultArray[k]) maxList.add(k+1);
                    }
                    // 내가 투표한 것의 숫자
                    int myResult = -1;
                    if (nowVoterResult.get(j).getResult1() == 1) myResult = 1;
                    else if (nowVoterResult.get(j).getResult2() == 1) myResult = 2;
                    else if (nowVoterResult.get(j).getResult3() == 1) myResult = 3;
                    else myResult = 4;
                    boolean notInFlag = true;
                    for (int k = 0; k<maxList.size(); k++) {
                        if (maxList.get(k) == myResult){
                            notInFlag = false;
                            isMatched += 1;
                            break;
                        }
                    }
                    if (notInFlag) isNotMatched += 1;


                    // 2. 상황/분위기/종류 계산
                    VoteFeeling titles = VoteFeeling.valueOf(nowVoterVote.getFeeling());
                    if (titles.number()<10){
                        situationCnt += 1;
                    }
                    else if(titles.number()<100){
                        moodCnt += 1;
                    }
                    else{
                        kindCnt += 1;
                    }

                    if (j == nowVoterResult.size()-1)
                        lastKeyword = nowVoterVote.getFeeling();
                }

                // 변수들 값들 비교해서 칭호 최종 판별
                String title = "기본 칭호";
                int[] results = {isMatched, isNotMatched, situationCnt, moodCnt, kindCnt};
                int maxVal = -1;
                for (int l = 0; l< results.length; l++) {
                    if (maxVal < results[l]) maxVal = results[l];
                }

                if (situationCnt == maxVal) title= "상황을 잘 보는 눈";
                else if (moodCnt == maxVal) title = "분위기를 잘 보는 눈";
                else if (kindCnt == maxVal) title = "종류를 잘 보는 눈";
                else if (isMatched == maxVal) title = "대중적인 눈";
                else if (isNotMatched == maxVal) title = "독창적인 눈";

                User v = (User) em.createQuery("select u from User u where u.id=:id").setParameter("id", nowVoter.getUser_id()).getSingleResult();

                // 그 유저의 칭호 UPDATE 쿼리
                em.createQuery("update User u set u.title=:title where u.id=:id")
                        .setParameter("id",nowVoter.getUser_id())
                        .setParameter("title",title)
                        .executeUpdate();

                // 그 유저의 keyword update 쿼리 - 일단은 임시적으로, 제일 최근 키워드
                em.createQuery("update User u set u.keyword=:keyword where u.id=:id")
                        .setParameter("id",nowVoter.getUser_id())
                        .setParameter("keyword",lastKeyword)
                        .executeUpdate();
            }
        }
    }

    public int max(int a, int b) {
        if (a>b) return a;
        else return b;
    }
    public int abs(int a) {
        if (a>=0) return a;
        else return -a;
    }
}

