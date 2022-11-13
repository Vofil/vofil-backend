package com.vofil.vofilbackend.repository;

import com.vofil.vofilbackend.domain.Picture;
import com.vofil.vofilbackend.domain.User;
import com.vofil.vofilbackend.domain.Vote;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.swing.plaf.basic.BasicDesktopIconUI;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.util.List;
import java.util.Optional;

public class PictureRepository {
    private final EntityManager em;
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
