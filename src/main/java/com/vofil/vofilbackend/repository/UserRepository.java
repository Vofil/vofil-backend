package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository{

    private final EntityManager em;
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    //회원 가입 시
    public User insertUser(User user) {
        em.persist(user);
        return user;
    }

    // id로 그 유저 찾기
    public Optional<User> findById(String id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    // 모든 유저 조회 시
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    // 포인트 지급
    public void givePoint(String userId, long point) {
        em.createQuery("update User u set u.point=u.point + :point" +
                        " where u.id=:userId")
                .setParameter("point",point)
                .setParameter("userId",userId)
                .executeUpdate();
        em.clear();
    }
}
