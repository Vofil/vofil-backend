package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.EntityManager;
import javax.swing.plaf.basic.BasicDesktopIconUI;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class PictureRepository {
    private final EntityManager em;

    // 로컬의 사진 경로는 PictureController, PictureRepository 값만 바꾸면 됩니다! 하드코딩 말고 PICTURE_PATH 변수 이용하기!!
    public final String PICTURE_PATH = "/Users/syt06162/Desktop/capstone/";

    public PictureRepository(EntityManager em) {
        this.em = em;
    }
    public Optional<Picture> findById(int id) {
        Picture user = em.find(Picture.class, id);
        return Optional.ofNullable(user);
    }
    public Picture save(Picture picture) {//VoteCaregory check2=VoteCaregory.valueOf(category)
        em.persist(picture);
        return picture;
    }
    public Picture update(int id, String file,int cnt){
        Picture picture=em.find(Picture.class, id);
        if(cnt==1){
            em.createQuery("update Picture u set u.re1=:re1 where u.id=:id").setParameter("re1",file).setParameter("id",id).executeUpdate();
        }
        else if(cnt==2){
            em.createQuery("update Picture u set u.re2=:re2 where u.id=:id").setParameter("re2",file).setParameter("id",id).executeUpdate();
        }
        else if(cnt==3){
            em.createQuery("update Picture u set u.re3=:re3 where u.id=:id").setParameter("re3",file).setParameter("id",id).executeUpdate();
        }
        else if(cnt==4){
            em.createQuery("update Picture u set u.re4=:re4 where u.id=:id").setParameter("re4",file).setParameter("id",id).executeUpdate();
        }
        return picture;
    }
    /*(
    public List<Vote> getAllVotes(){
        return em.createQuery("select u from Vote u",Vote.class)
                .getResultList();
    }*/
    public String show(int id, int cnt){
        Picture picture=em.find(Picture.class, id);
        String s="0"; String checked="0";
        if(cnt==1){
            s=picture.getRe1();
        }
        else if(cnt==2){
            s=picture.getRe2();
        }
        else if(cnt==3){
            s= picture.getRe3();
        }
        else if(cnt==4){
            s= picture.getRe4();
        }
        return s;
    }
    public File showing(int id, int cnt)throws Exception{
       String filePath=PICTURE_PATH;
        String fileName=show(id,cnt);//해당 투표 id의 몇 번째 사진의 originalName을 찾는 함수

        File ProfileFile = new File(filePath+fileName);

        return ProfileFile;

        //FileItem의 Path 설정을 위한 더미 파일 객체, UserProfileImg폴더는 존재하지만 dummy.jpg는 존재하지 않음
        //File DummyFile = new File("/Users/2222.jpg");


//        //더미 파일로 FileItem 구현체를 생성
//        FileItem user1ProfileFileItem = new DiskFileItem(
//                "userProfile",
//                Files.probeContentType(DummyFile.toPath()),
//                false,
//                DummyFile.getName(),
//                (int) DummyFile.length(),
//                DummyFile.getParentFile());
//
//        //FileItem 구현체에 1.jpg 이미지 파일 업로드
//        user1ProfileFileItem.write(user1ProfileFile);
//        user1ProfileFileItem.getOutputStream();
//
//        CommonsMultipartFile commonsMultipartFile = new CommonsMultipartFile(user1ProfileFileItem);
//
//        return commonsMultipartFile;
    }
    public Picture getPicture(int id){
        Picture user = em.find(Picture.class, id);
        return user;
    }
    public List<Picture> getAllPicture(){
        return em.createQuery("select u from Picture u",Picture.class)
                .getResultList();
    }
//    public Picture updatePic(int id, String result1, String result2, String result3, String result4){
//        Vote vote= em.find(Vote.class,id);
//        //em.createQuery("delete u from Vote u where u.id= :id",Vote.class).setParameter("id",id).executeUpdate();
//        //save(vote1);
//        em.createQuery("update Vote u set u.re1=:result1 where u.id=:id").setParameter(id,result1).executeUpdate();
//        em.createQuery("update Vote u set u.re2=:result2 where u.id=:id").setParameter(id,result2).executeUpdate();
//        em.createQuery("update Vote u set u.re3=:result3 where u.id=:id").setParameter(id,result3).executeUpdate();
//        em.createQuery("update Vote u set u.re4=:result4 where u.id=:id").setParameter(id,result4).executeUpdate();
//        return vote;
//    }

}
