package com.vofil.vofilbackend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Picture {
    @Id
    int id;
    String re1;
    String re2;
    String re3;
    String re4;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRe1() {
        return re1;
    }

    public void setRe1(String re1) {
        this.re1 = re1;
    }

    public String getRe2() {
        return re2;
    }

    public void setRe2(String re2) {
        this.re2 = re2;
    }

    public String getRe3() {
        return re3;
    }

    public void setRe3(String re3) {
        this.re3 = re3;
    }

    public String getRe4() {
        return re4;
    }

    public void setRe4(String re4) {
        this.re4 = re4;
    }
}
