package com.vofil.vofilbackend.domain;

public class SimpleVoteInformation {
    private int vote_id;
    private String title;
    private byte[] re1; // 사진1의 byte[] 형태!

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getRe1() {
        return re1;
    }

    public void setRe1(byte[] re1) {
        this.re1 = re1;
    }
}
