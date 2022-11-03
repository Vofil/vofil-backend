package com.vofil.vofilbackend;

import com.vofil.vofilbackend.repository.UserRepository;
import com.vofil.vofilbackend.service.UserService;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.service.VoteService;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(em);
    }

    @Bean
    public VoteService voteService(){
        return new VoteService(voteRepository(),voterRepository());
    }
    @Bean
    public VoterService voterService(){
        return new VoterService(voterRepository());
    }

    @Bean
    public VoteRepository voteRepository(){
        return new VoteRepository(em);
    }
    @Bean
    public VoterRepository voterRepository(){
        return new VoterRepository(em);
    }


}
