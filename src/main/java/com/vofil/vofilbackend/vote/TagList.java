package com.vofil.vofilbackend.vote;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TagList {//태그 투표할 떄
    예쁘다(1),
    귀엽다(2),
    시크하다(3),
    매력있다(4),
    사랑스럽다(5),
    활발하다(6),
    요즘유행(7),
    강아지상(9),
    고양이상(10),

    잘생겼다(20),
    멋있다(21),
    시크하다1(22),
    매력있다1(23),
    듬직하다(24),
    귀엽다1(25),
    요즘스타일(26),
    섹시하다(27),
    청량하다(28),
    강아지상1(29),
    고양이상1(30),
    우아하다(8);
    private final int number;
    TagList(int number){
        this.number=number;
    }
    public int number(){
        return number;
    }
    private static final Map<Integer, TagList> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(TagList::number, Function.identity()));
    public static TagList valueOfNumber(int number){
        return BY_NUMBER.get(number);
    }


}
