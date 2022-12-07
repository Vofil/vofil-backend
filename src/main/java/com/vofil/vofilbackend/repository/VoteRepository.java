package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.*;
import com.vofil.vofilbackend.vote.VoteFeeling;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class VoteRepository {
    private final EntityManager em;
    public VoteRepository(EntityManager em){
        this.em=em;
    }
    public Vote save(Vote vote) {//VoteCaregory check2=VoteCaregory.valueOf(category)
        em.persist(vote);
        return vote;
    }
    public Optional<Vote> findById(int id){
        Vote vote= em.find(Vote.class,id);
        return Optional.ofNullable(vote);
    }
//    public Option<Vote> getAllVotes(){
//        return em.createQuery("select u from Vote u",Vote.class)
//                .getResultList();
//    }
    //고치기
    public Vote check(int id){
        em.createQuery("update Vote u set u.ending_point=u.ending_point-1 where u.id=:id").setParameter("id",id).executeUpdate();
        em.clear();

        Optional<Vote> cnt1=findById(id);
        Vote vote1= cnt1.get();
        if(vote1.getEnding_point()==0){
            updateUserTitleAndKeyword(id);
        }

        return vote1;
    }
//
//    @Modifying
//    @Query("update Vote u set u.ending_point=u.ending_point-1 where u.id=:id")
//    void checkUpdateQuery(@Param("id") int id) {};

    public Vote updateFinal(int id,int result1,int result2, int result3, int result4){
        Optional<Vote> cnt1=findById(id);
        Vote vote= cnt1.get();

        em.createQuery("update Vote u set u.result1=:result1 where u.id=:id").setParameter("id",id).setParameter("result1",result1).executeUpdate();
        em.createQuery("update Vote u set u.result2=:result2 where u.id=:id").setParameter("id",id).setParameter("result2",result2).executeUpdate();
        em.createQuery("update Vote u set u.result3=:result3 where u.id=:id").setParameter("id",id).setParameter("result3",result3).executeUpdate();
        em.createQuery("update Vote u set u.result4=:result4 where u.id=:id").setParameter("id",id).setParameter("result4",result4).executeUpdate();
        return vote;
    }
    public Vote getVote(int id){
        Vote user = em.find(Vote.class, id);
        return user;
    }
    public List<Vote> getAllVotes(){
        return em.createQuery("select u from Vote u",Vote.class)
                .getResultList();
    }

    public List<Graph> getGraph(int id, int cnt) {//성별 분석
        int[][] diagram = new int[2][2];
        int[][] picture = new int[4][2];
        diagram[0][0] = 3;
        diagram[1][0] = 4;
//        List<Voter> voters;
//        voters= em.createQuery("select u from Voter u where u.vote_id = :id and re1= :cnt",Voter.class).
//                setParameter("id",id).setParameter("cnt",cnt).getResultList();
        List<Voter> voters = voterRepository.findResult(id);
        for (int i = 0; i < voters.size(); i++) {
            Voter voter = voters.get(i);
            if (voter.getResult1() != 0) {
                Optional<User> user1 = userRepository.findById(voter.getUser_id());
                User user = user1.get();
                if (user.getGender() == 3)
                    picture[0][0]++;
                else
                    picture[0][1]++;
            }
            if (voter.getResult2() != 0) {
                Optional<User> user1 = userRepository.findById(voter.getUser_id());
                User user = user1.get();
                if (user.getGender() == 3)
                    picture[1][0]++;
                else
                    picture[1][1]++;
            }
            if (voter.getResult3() != 0) {
                Optional<User> user1 = userRepository.findById(voter.getUser_id());
                User user = user1.get();
                if (user.getGender() == 3)
                    picture[2][0]++;
                else
                    picture[2][1]++;
            }
            if (voter.getResult4() != 0) {
                Optional<User> user1 = userRepository.findById(voter.getUser_id());
                User user = user1.get();
                if (user.getGender() == 3)
                    picture[3][0]++;
                else
                    picture[3][1]++;
            }
        }
        diagram[0][1] = (int) (((double) picture[cnt - 1][0] / (picture[cnt - 1][0] + picture[cnt - 1][1])) * 100);
        diagram[1][1] = (int) (((double) picture[cnt - 1][1] / (picture[cnt - 1][0] + picture[cnt - 1][1])) * 100);

        for (int i = 0; i < diagram.length - 1; i++) {
            for (int j = 0; j < diagram.length - 1; j++) {
                if (diagram[j][1] < diagram[j + 1][1]) {
                    int[] temp = diagram[j];
                    diagram[j] = diagram[j + 1];
                    diagram[j + 1] = temp;
                }
            }
        }

        List<Graph> graph = new ArrayList<>();

        for (int i = 0 ; i < diagram.length ; i++) {
            Graph graph1 = new Graph();
            if(diagram[i][0]==3)
                graph1.setName("남자");
            else
                graph1.setName("여자");
            graph1.setPercentage(diagram[i][1]);

            graph.add(graph1);
        }

        return graph;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    VoterRepository voterRepository;

    // vote 하나가 끝나면, 그에 해당하는 voter들의 칭호를 업데이트한다.
    public void updateUserTitleAndKeyword(int voteId) {

        // 칭호 3번째 영역 - 태그 별 칭호 목록
        /****/
        HashMap<String, String> TAG_TITLE_MAP = new HashMap<String, String>();
        TAG_TITLE_MAP.put("예쁘다", "예쁜이");
        TAG_TITLE_MAP.put( "귀엽다", "귀요미");
        TAG_TITLE_MAP.put( "시크하다", "시크왕"); // ..
        TAG_TITLE_MAP.put( "매력있다", "매력만점");
        TAG_TITLE_MAP.put( "사랑스럽다", "러블리");
        TAG_TITLE_MAP.put( "활발하다", "활발활발");
        TAG_TITLE_MAP.put( "요즘유행", "트렌드 리더");
        TAG_TITLE_MAP.put( "우아하다", "귀족");
        TAG_TITLE_MAP.put( "강아지상", "댕댕이");
        TAG_TITLE_MAP.put( "고양이상", "야옹이");
        TAG_TITLE_MAP.put( "잘생겼다", "잘생");
        TAG_TITLE_MAP.put( "멋있다", "어썸맨");
        TAG_TITLE_MAP.put( "듬직하다", "불곰");
        TAG_TITLE_MAP.put( "요즘스타일", "스타일리스트");
        TAG_TITLE_MAP.put( "섹시하다", "섹시가이");
        TAG_TITLE_MAP.put( "청량하다", "쿨가이");

        TAG_TITLE_MAP.put( "기본", "심플리스트");
        /****/

        List<Voter> voters = voterRepository.findResult(voteId);
        for (int tt = 0; tt<voters.size(); tt++)
            System.out.println(voters.get(tt));

        // 그에 해당하는 voter들의 모든 칭호를 업데이트 (해당되는 애들만)
        for (int i = 0 ; i<voters.size() ; i++) {
            Voter nowVoter = voters.get(i);
            List<Voter> nowVoterResult = em.createQuery("select u from Voter u where u.User_id = :id",Voter.class).setParameter("id",nowVoter.getUser_id()).getResultList();

            System.out.println("haha voter id : " + Integer.toString(i));
            for (int jj = 0; jj <nowVoterResult.size(); jj++) {
                System.out.println(nowVoterResult.get(jj).getVote_id());

            }


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

                // 태그 판별 변수
                HashMap<String, Integer> tagCount = new HashMap<String, Integer>();
                tagCount.put("예쁘다", 0);
                tagCount.put( "귀엽다", 0);
                tagCount.put( "시크하다", 0);
                tagCount.put( "매력있다", 0);
                tagCount.put( "사랑스럽다", 0);
                tagCount.put( "활발하다", 0);
                tagCount.put( "요즘유행", 0);
                tagCount.put( "우아하다", 0);
                tagCount.put( "강아지상", 0);
                tagCount.put( "고양이상", 0);
                tagCount.put( "잘생겼다", 0);
                tagCount.put( "멋있다", 0);
                tagCount.put( "듬직하다", 0);
                tagCount.put( "요즘스타일", 0);
                tagCount.put( "섹시하다", 0);
                tagCount.put( "청량하다", 0);

                String lastKeyword = "";
                // 각각의 vote에 대해, 1.대중/독창 계산 , 2.상황/분위기/종류 계산
                for (int j = 0 ; j<nowVoterResult.size() ; j++) {
                    Vote nowVoterVote = findById(nowVoterResult.get(j).getVote_id()).get(); // vote 추출

                    // 아직 안 끝난 투표는 패스
                    if (nowVoterVote.getEnding_point() != 0) continue;

                    // 1. 대중/독창 계산 - 일반투표일때만
                    if (nowVoterVote.getKind() == 0) {
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
                    }

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


                    // temp. 그 유저 키워드는 일단 마지막으로
                    lastKeyword = nowVoterVote.getFeeling();


                    // 3. 태그투표의 경우, 해당 태그 업데이트
                    if (nowVoterVote.getKind() == 0) continue;
                    String nowTag = nowVoterVote.getTaging();
                    tagCount.put(nowTag, tagCount.get(nowTag)+1); // +1

                }

                // 변수들 값들 비교해서 칭호 최종 판별
                String title = "기본 칭호";

                String title_1 = "";
                String title_2 = "";
                String title_3 = "";
                // 1번
                if (isMatched >= isNotMatched) title_1 = "대중적이고";
                else title_1 = "독창적이고";

                // 2번
                if ((situationCnt >= moodCnt) && (situationCnt >= kindCnt)) title_2 = "상황을 잘 보는";
                else if ((kindCnt >= situationCnt) && (kindCnt >= moodCnt) ) title_2 = "종류를 잘 보는";
                else title_2 = "분위기를 잘 보는";

                // 3번
                int maxVal = -1;
                String maxKey = "";
                for (String key : tagCount.keySet()) { // keyset을 이용하기 때문에, 동점에 대해서는 랜덤 우승 처리
                    if (tagCount.get(key) > maxVal) {
                        maxVal = tagCount.get(key);
                        maxKey = key;
                    }
                }
                if (maxVal == 0) title_3 = TAG_TITLE_MAP.get("기본");
                else title_3 = TAG_TITLE_MAP.get(maxKey);

                title = title_1 + ", "+ title_2 + ", "+ title_3;

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

    // 가장 최신의 ID 리턴
    public int getMaxVoteId() {
        int maxId = (int) em.createQuery("select max(v.id) from Vote v").getSingleResult();
        return maxId;
    }
    // vote ID 값 변경하기
    public int reraiseId(int voteId, int currentMax){
        em.createQuery("update Vote v set v.id = :currentMax + 1 where v.id = :voteId")
                .setParameter("currentMax",currentMax)
                .setParameter("voteId",voteId)
                .executeUpdate();
        em.clear();
        return currentMax+1;
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

