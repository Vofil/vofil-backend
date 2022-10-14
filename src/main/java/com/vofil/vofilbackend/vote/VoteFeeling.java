package com.vofil.vofilbackend.vote;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum VoteFeeling {
    소개팅(1),
    대학오티(2),
    직장(3),
    썸(4),
    동아리(5),
    예쁜(10),
    귀여운(11),
    시크한(12),
    매력있는(13),
    잘생긴(14),
    멋있는(15),
    사랑스러운(16),
    섹시한(17),
    듬직한(18),
    힙한(19),
    카톡프사(100),
    카톡배사(101),
    인스타프사(102),
    인스타게시물(103),
    트위터프사(104),
    트위터게시물(105),
    트위터배사(106);

    private final int number;
    VoteFeeling(int number){
        this.number=number;
    }
    public int number(){
        return number;
    }
    private static final Map<Integer, VoteFeeling> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(VoteFeeling::number, Function.identity()));
    public static VoteFeeling valueOfNumber(int number){
        return BY_NUMBER.get(number);
    }
}
