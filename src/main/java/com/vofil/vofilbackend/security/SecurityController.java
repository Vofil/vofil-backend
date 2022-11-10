package com.vofil.vofilbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value="id") String id) {
        String token = securityService.createToken(id, (30*1000*60)); // 30분
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("token", token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value="token") String token) {
        String id = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", id);
        return map;
    }




}
