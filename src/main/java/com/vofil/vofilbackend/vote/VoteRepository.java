package com.vofil.vofilbackend.vote;

import java.util.HashMap;
import java.util.Map;

public class VoteRepository {
    private static Map<Integer, Vote> store = new HashMap<>();
    private static int sequence=0;
    public Vote save(Vote vote) {
        vote.setId(++sequence);
        store.put(vote.getId(), vote);
        return vote;
    }

    public static Vote findById(int id) {
        return store.get(id);
    }
}
