package com.likelion.loco.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class InMemoryBlacklistTokenRepository {
    private static Set<String> blacklist = new HashSet<>();


    public static void addToBlacklist(String token) {
        blacklist.add(token);
    }


    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
