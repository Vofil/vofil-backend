package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.SimpleVoteInformation;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainpageRepository {
    @Autowired
    private UserRepository userRepository;
    private final EntityManager em;
    public MainpageRepository(EntityManager em) {
        this.em = em;
    }

    // (본인에 적합한) 최신 투표들 추출 - 끝나지 않은 투표들 추출 후 sort 역순 (Repositoy 에서는 투표들 자체만 추출)
    public List<Vote> getLatestVotes(String user_id) { // user_id: 유저 아이디 (그 사람이 만든 투표는 제외하기 위함)
        User user = userRepository.findById(user_id).get();
        // 올해 년도
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        int nowYear = Integer.parseInt(now.format(formatter));
        int userYear = user.getBirth_year();
        int userGender = user.getGender();
        int ageEnum = (int)((nowYear - userYear + 1) / 10);

        return em.createQuery("select v from Vote v " +
                "where ending_point > 0 and " +
                "v.user_id <> :user_id and " +
                "age=:ageEnum and " +
                        "gender in :genderList",Vote.class)
                .setParameter("user_id",user_id)
                .setParameter("ageEnum",ageEnum)
                .setParameter("genderList", Arrays.asList(userGender, 5))
                .getResultList();
    }

    // (본인 상관없이) 최신 투표들 추출 - 끝나지 않은 투표들 추출 후 sort 역순 (Repositoy 에서는 투표들 자체만 추출)
    public List<Vote> getLatestVotes() {
        return em.createQuery("select v from Vote v " +
                        "where ending_point > 0 ",Vote.class)
                .getResultList();
    }

    @Autowired
    PictureRepository pictureRepository;

    // List<Vote> 받으면 -> List<SVI> 로 리턴해주는 함수!
    public List<SimpleVoteInformation> getSimpleVoteInformationList(List<Vote> votes) {
        int size = votes.size();
        List<SimpleVoteInformation> sviList = new ArrayList<>();

        for (int i = 0 ; i < size ; i++) {
            SimpleVoteInformation svi = new SimpleVoteInformation();
            Vote vote = votes.get(i);
            svi.setRe1(pictureRepository.show(vote.getId(), 1));
            //->svi.setRe1(pictureRepository.showed(vote.getId(), 1)); 근데 SimpleVoteInformation의 Re1을 byte[]로 바꿔야함
            svi.setTitle(vote.getFeeling());
            svi.setVote_id(vote.getId());
            sviList.add(svi);
        }
        return sviList;
    }


}
