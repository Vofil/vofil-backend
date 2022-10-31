package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity signUp(User user){

        if (findUserById(user.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 ID 입니다.");
        }
        else {
            userRepository.insertUser(user);
            return ResponseEntity.ok().body(user.getName());
        }
    }
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    // 로그인
    public ResponseEntity logIn(String id, String password) {

        Optional<User> foundUser = findUserById(id);
        if (foundUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 ID 입니다.");
        }
        else if (!foundUser.get().getPassword().equals(password)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID와 비밀번호가 일치하지 않습니다.");
        }
        else {
            // 로그인 추가 로직 - JWT, 보안 등 필요시
            return ResponseEntity.ok().body(userRepository.findById(id));
        }
    }


    // 회원목록조회
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
