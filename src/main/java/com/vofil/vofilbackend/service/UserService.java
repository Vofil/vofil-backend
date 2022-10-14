package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public String signUp(User user){
        userRepository.insertUser(user);
        return user.getName();
    }
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    // 로그인
    public Optional<User> logIn(String id, String password) {
        // 로그인 추가 로직 - JWT, 보안 등 필요시
        return userRepository.findById(id);
    }


    // 회원목록조회
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
