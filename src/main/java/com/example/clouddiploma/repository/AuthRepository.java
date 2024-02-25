package com.example.clouddiploma.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class AuthRepository {

    protected final Map<String, String> tokensAndUsernames = new HashMap<>();

    public void putTokenAndUsername(String token, String username) {
        tokensAndUsernames.put(token, username);
        log.info("User {} authentication. JWT: {}", username, token);
    }

    public void removeTokenAndUsernameByToken(String token) {
        log.info("User {} logout. JWT is disabled.", getUsernameByToken(token));
        tokensAndUsernames.remove(token);
    }

    public String getUsernameByToken(String token) {
        return tokensAndUsernames.get(token);
    }
}