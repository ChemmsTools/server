package com.chemmstools.server.service;

public interface TokenService {
    boolean authToken(String token);
    String getToken(String username);
}
