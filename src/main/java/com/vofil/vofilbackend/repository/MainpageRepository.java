package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainpageRepository {
    @Autowired
    private UserRepository userRepository;
    private final EntityManager em;
    public MainpageRepository(EntityManager em) {
        this.em = em;
    }
    public final int MAIN_SHOW_NUMBER = 4; // mainpage, mypage 에서 한번에 보여주는 사진의 개수

    // 최신 투표들 추출 - 끝나지 않은 투표들 추출 후 sort 역순 (Repositoy 에서는 투표들 자체만 추출)
    public List<Vote> getLatestVotes(String user_id) { // user_id: 유저 아이디 (그 사람이 만든 투표는 제외하기 위함)
        // 올해 년도
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        int nowYear = Integer.parseInt(now.format(formatter));
        int userYear = userRepository.findById(user_id).get().getBirth_year();
        int ageEnum = (int)((nowYear - userYear + 1) / 10);

        List<Vote> allList = em.createQuery("select v from Vote v " +
                "where ending_point > 0 and " +
                "v.user_id <> :user_id and " +
                "age=:ageEnum",Vote.class)
                .setParameter("user_id",user_id)
                .setParameter("ageEnum",ageEnum)
                .getResultList();

        List<Vote> lastList = new ArrayList<>();
        for (int i = 1; i<MAIN_SHOW_NUMBER+1; i++){
            if (allList.size()-i <0) break;
            lastList.add(allList.get(allList.size()-i));
        }
        return lastList;
    }

    @Autowired
    PictureRepository pictureRepository;

    // 중요 함수 - List<Vote> 받으면 -> List<SVI> 로 리턴해주는 함수!
    public List<SimpleVoteInformation> getSimpleVoteInformationList(List<Vote> votes) {
        int size = votes.size();
        List<SimpleVoteInformation> sviList = new ArrayList<>();

        for (int k = 0 ; k < votes.size(); k++)
            System.out.println(votes.get(k).getId());

        for (int i = 0 ; i < size ; i++) {
            SimpleVoteInformation svi = new SimpleVoteInformation();
            Vote vote = votes.get(i);
            svi.setRe1(pictureRepository.show(vote.getId(), 1));
            svi.setTitle(vote.getFeeling());
            svi.setVote_id(vote.getId());
            sviList.add(svi);
        }
        return sviList;
    }


}
