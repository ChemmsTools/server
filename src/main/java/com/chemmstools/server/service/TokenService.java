package com.chemmstools.server.service;

public interface TokenService {
    boolean authToken(String token);
    String getTokenByUsername(String username);
    String getUsernameByToken(String token);
}
