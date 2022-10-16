package com.vofil.vofilbackend.vote;

public class Vote {
    private int gender;//설정 성별
    private int age;//설정 연령대[0,10,20,30,40,50,60]
    private String User_id;
    private int id;//투표 id
    private VoteCaregory category;//투표 사진 종류(카톡프사, ...)
    private int kind;//투표 종류(0일반투표, 1태그투표)
    private int pic_cnt;
    private int ending_point;//투표 종료 개수
    //등록시간
    private VoteFeeling title;//투표 제목(키워드)
    private int id_cnt=1;
    private TagList setting;

    public Vote(String User_id,int gender, int age, String category, int kind, String title, int ending_point,int pic_cnt,String setting) {
        this.User_id=User_id;
        this.id=id;
        this.pic_cnt=pic_cnt;
        VoteCaregory check2=VoteCaregory.valueOf(category);
        this.category=check2;
        this.age=age;
        this.ending_point=ending_point;
        this.gender=gender;
        VoteFeeling check1=VoteFeeling.valueOf(title);
        this.title=check1;
        this.kind=kind;
        TagList check3=TagList.valueOf(setting);
        this.setting=check3;
    }
    public int getId() {

        return id;
    }


    public void setId(int Id) {
        this.id= id;
    }
    public TagList getSetting() {
        return setting;
    }

    public void setGender(TagList setting) {
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

    public String toString(){
        VoteFeeling titles=this.title;
        String check=titles.toString();
        if (titles.number()<10){
            check=check+"에 어울리는 사진을 골라주세요";
        }
        else if(titles.number()<100){
            check=check+"느낌의 사진을 골라주세요";
        }
        else{
            check=check+"에 적합한 사진을 골라주세요";
        }
        return check;
    }


}
