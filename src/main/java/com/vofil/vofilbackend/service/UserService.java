package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.repository.UserRepository;
import com.vofil.vofilbackend.security.SecurityController;
import com.vofil.vofilbackend.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    SecurityController securityController; // 구조가 이상하지만 JWT 생성 위함

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
            // JWT 구현 완료
            String token = (String) securityController.createToken(id).get("token");
            return ResponseEntity.ok().body(token);
        }
    }


    // 회원목록조회
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // user_id로 유저 찾고, 그 유저가 N포인트 이상 있다면 N포인트 깎기
    public boolean minusPointsIfHave(int point, String user_id){
        User user = userRepository.findById(user_id).get();
        if (user.getPoint() >= point) {
            user.setPoint( user.getPoint() - point);
            return true; // 잘 깎았다
        }
        else return false; // 없어서 못깎았다.
    }
}
