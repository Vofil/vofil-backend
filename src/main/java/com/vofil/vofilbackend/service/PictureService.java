package com.vofil.vofilbackend.service;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.domain.Vote;
import com.vofil.vofilbackend.domain.Voter;
import com.vofil.vofilbackend.repository.PictureRepository;
import com.vofil.vofilbackend.repository.VoterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Transactional
public class PictureService {
    PictureRepository pictureRepository;
    //VoterRepository voterRepository;
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository=pictureRepository;
    }

    public ResponseEntity createPicture(Picture picture){
        pictureRepository.save(picture);
        //sql update문 넣기!!
        return ResponseEntity.ok().body(picture.getId());
    }
    public ResponseEntity update(int id, String file, int cnt){
        pictureRepository.update(id, file, cnt);
        return ResponseEntity.ok().body(id);
    }
    public ResponseEntity showFile(int id, int cnt){
        return ResponseEntity.ok().body(pictureRepository.show(id,cnt));
    }

}