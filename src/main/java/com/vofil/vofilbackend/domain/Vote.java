package com.vofil.vofilbackend.domain;

import com.vofil.vofilbackend.vote.TagList;
import com.vofil.vofilbackend.vote.VoteCaregory;
import com.vofil.vofilbackend.vote.VoteFeeling;

import javax.persistence.*;

@Entity
public class Vote {
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;//투표 id

    int gender;//설정 성별
    int age;//설정 연령대[0,10,20,30,40,50,60]
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTaging() {
        return taging;
    }

    public void setTaging(String taging) {
        this.taging = taging;
    }

    public String getCategorying() {
        return categorying;
    }

    public void setCategorying(String categorying) {
        this.categorying = categorying;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    int kind;//투표 종류(0일반투표, 1태그투표)
    int pic_cnt;//등록하는 사진 개수
    int ending_point;//투표 종료 개수
    int result1;
    int result2;
    int result3;
    int result4;

//    @Enumerated(EnumType.STRING)
//    private VoteFeeling title;//투표 제목(키워드)
//    @Enumerated(EnumType.STRING)
//    private VoteCaregory category;//투표 사진 종류(카톡프사, ...)
//    @Enumerated(EnumType.STRING)
//    private TagList setting;//설정한 태그

    private String taging;//설정태그의 string
    private String categorying;//설정 사진카테고리의 string
    private String feeling;//설정 투표제목(키워드)의 string

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getPic_cnt() {
        return pic_cnt;
    }

    public void setPic_cnt(int pic_cnt) {
        this.pic_cnt = pic_cnt;
    }

    public int getEnding_point() {
        return ending_point;
    }

    public void setEnding_point(int ending_point) {
        this.ending_point = ending_point;
    }

    public int getResult1() {
        return result1;
    }

    public void setResult1(int result1) {
        this.result1 = result1;
    }

    public int getResult2() {
        return result2;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public int getResult3() {
        return result3;
    }

    public void setResult3(int result3) {
        this.result3 = result3;
    }

    public int getResult4() {
        return result4;
    }

    public void setResult4(int result4) {
        this.result4 = result4;
    }

    /*public Vote(String User_id,int gender, int age, String category, int kind, String title, int ending_point,int pic_cnt,String setting) {
        this.User_id=User_id;
        this.id=id;
        this.pic_cnt=pic_cnt;
        VoteCaregory check2=VoteCaregory.valueOf(category); -->이거 repository에 넣기
        this.category=check2;
        this.age=age;
        this.ending_point=ending_point;
        this.gender=gender;
        VoteFeeling check1=VoteFeeling.valueOf(title);
        this.title=check1;
        this.kind=kind;
        TagList check3=TagList.valueOf(setting);
        this.setting=check3;
    }*/

}
