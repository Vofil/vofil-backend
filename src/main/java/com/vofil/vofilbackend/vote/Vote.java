package com.vofil.vofilbackend.vote;

public class Vote {
    private int gender;//설정 성별
    private int age;//설정 연령대[0,10,20,30,40,50,60]

    private Long id;//투표 id
    private VoteCaregory category;//투표 사진 종류(카톡프사, ...)
    private int kind;//투표 종류(일반투표, 태그투표)
    private int ending_point;//투표 종료 개수
    //등록시간
    private VoteFeeling title;//투표 제목(키워드)

    //설정태그

    public Vote(Long id, int gender, int age, VoteCaregory category, int kind, VoteFeeling title, int ending_point) {
        this.id = id;
        this.category=category;
        this.age=age;
        this.ending_point=ending_point;
        this.gender=gender;
        this.title=title;
        this.kind=kind;
    }
    public Vote(){

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
