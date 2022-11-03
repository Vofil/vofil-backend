package com.vofil.vofilbackend.domain;

import com.vofil.vofilbackend.vote.TagList;
import com.vofil.vofilbackend.vote.VoteCaregory;
import com.vofil.vofilbackend.vote.VoteFeeling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//투표 id
    private VoteFeeling title;//투표 제목(키워드)
    private VoteCaregory category;//투표 사진 종류(카톡프사, ...)
    private TagList setting;//설정한 태그
    private String Taging;//설정태그의 string
    private String Categorying;//설정 사진카테고리의 string
    private String Feeling;//설정 투표제목(키워드)의 string

    private int gender;//설정 성별
    private int age;//설정 연령대[0,10,20,30,40,50,60]
    private String User_id;
    private int kind;//투표 종류(0일반투표, 1태그투표)
    private int pic_cnt;//등록하는 사진 개수
    private int ending_point;//투표 종료 개수
    //등록시간
    private int[] final_result;//최종 결과 투표값 리스트(update)자주 되야함

    public int[] getFinal_result() {
        return final_result;
    }

    public void setFinal_result(int[] final_result) {
        this.final_result = final_result;
    }

    public String getTaging() {
        return Taging;
    }

    public void setTaging(String taging) {
        Taging = taging;
    }

    public String getCategorying() {
        return Categorying;
    }

    public void setCategorying(String categorying) {
        Categorying = categorying;
    }

    public String getFeeling() {
        return Feeling;
    }

    public void setFeeling(String feeling) {
        Feeling = feeling;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public void setSetting(TagList setting) {
        this.setting = setting;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id= id;
    }

    public TagList getSetting() {
        return setting;
    }

    public void setTagList(TagList setting) {
        this.setting=setting;
    }
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    public int getPic_cnt() {
        return pic_cnt;
    }

    public void setPic_cnt(int pic_cnt) {
        this.pic_cnt = pic_cnt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public VoteCaregory getCategory() {
        return category;
    }

    public void setCategory(VoteCaregory category) {
        this.category = category;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getEnding_point() {
        return ending_point;
    }

    public void setEnding_point(int ending_point) {
        this.ending_point = ending_point;
    }

    public VoteFeeling getTitle() {
        return title;
    }

    public void setTitle(VoteFeeling title) {
        this.title = title;
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
