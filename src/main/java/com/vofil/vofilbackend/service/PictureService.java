package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.PictureRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Transactional
public class PictureService {
    PictureRepository pictureRepository;
    //VoterRepository voterRepository;
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository=pictureRepository;
    }

    public ResponseEntity createPicture(Picture picture){
        pictureRepository.save(picture);

        return ResponseEntity.ok().body(picture.getId());
    }
    public List<Picture> getAllPicture() {
        return pictureRepository.getAllPicture();
    }
    public Picture getPicture(int id) {
        return pictureRepository.getPicture(id);
    }
    public ResponseEntity update(int id, String file, int cnt){
//        if(pictureRepository.findById(id).isEmpty()){
//            pictureRepository.save(picture);
//        }
        pictureRepository.update(id, file, cnt);
        return ResponseEntity.ok().body(id);
    }
    public String showFile(int id, int cnt){
        return pictureRepository.show(id,cnt);
    }
    public byte[] showedFile(int id, int cnt){
        return pictureRepository.showed(id,cnt);
    }
    public ResponseEntity showing(int id, int cnt){
        try{
            File file=pictureRepository.showing(id,cnt);
            return ResponseEntity.ok().body(file);
        }catch(Exception e){
            return ResponseEntity.ok().body(e);
        }

    }
}
