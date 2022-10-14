package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity insertUser(@RequestBody User user) { //Json 형식
        if (userService.findUserById(user.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 ID 입니다.");
        }
        else {
            return ResponseEntity.ok().body(userService.signUp(user));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity logInUser(@PathVariable String id, @RequestBody User user) {
        Optional<User> foundUser = userService.findUserById(id);
        if (foundUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 ID 입니다.");
        }
        else if (!foundUser.get().getPassword().equals(user.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID와 비밀번호가 일치하지 않습니다.");
        }
        else {
            return ResponseEntity.ok().body(userService.logIn(id, user.getPassword())); // 그냥 foundUser를 줘도 되지만 추후에 보안 등 작업은 Service 단에서 할수도
        }
    }

    @GetMapping("")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
