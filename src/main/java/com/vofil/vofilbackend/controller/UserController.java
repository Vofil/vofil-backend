package com.vofil.vofilbackend.controller;

import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

// test 보이면 잘 연동
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity insertUser(@RequestBody User user) { //Json 형식
        return userService.signUp(user);
    }

    @GetMapping("/")
    public ResponseEntity logInUser(@RequestParam String id, @RequestParam String password) {
        return userService.logIn(id, password);
    }

    @GetMapping("")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
