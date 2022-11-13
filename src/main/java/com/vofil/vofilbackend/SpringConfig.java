package com.vofil.vofilbackend;

import com.vofil.vofilbackend.repository.PictureRepository;
import com.vofil.vofilbackend.repository.UserRepository;
import com.vofil.vofilbackend.service.PictureService;
import com.vofil.vofilbackend.service.UserService;
import com.vofil.vofilbackend.repository.VoteRepository;
import com.vofil.vofilbackend.service.VoteService;
import com.vofil.vofilbackend.repository.VoterRepository;
import com.vofil.vofilbackend.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
    public PictureService pictureService() {
        return new PictureService(new PictureRepository(em));
    }

    @Bean
    public PictureRepository pictureRepository() {
        return new PictureRepository(em);
    }
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSize(50 * 1024 * 1024);
        return commonsMultipartResolver;
    }
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        //multipartResolver.setDefaultEncoding("UTF-8");
//        //multipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
//        return multipartResolver;
//    }
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
