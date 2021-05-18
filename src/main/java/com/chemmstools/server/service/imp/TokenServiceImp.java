package com.chemmstools.server.service.imp;

import com.chemmstools.server.service.TokenService;
import com.chemmstools.server.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImp implements TokenService {
    @Autowired
    private RedisUtils redisUtils;

    private final String CHEMMSTOOLS_USER_TOKEN = "CHEMMSTOOLS_USER_TOKEN_";

    private final String CHEMMSTOOLS_USER = "CHEMMSTOOLS_USER_";

    private final int TOKEN_OVER_TIME_SECONDS = 3600;

    @Override
    public boolean authToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        if (redisUtils.getValue(CHEMMSTOOLS_USER + token) != null) {
            updateToken(token, getUsernameByToken(token));
            return true;
        }
        return false;
    }

    @Override
    public String getTokenByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return "";
        }
        String tokenRet = "";
        if (redisUtils.getValue(CHEMMSTOOLS_USER_TOKEN + username) != null) {
            tokenRet = redisUtils.getValue(CHEMMSTOOLS_USER_TOKEN + username);
        } else {
            tokenRet = UUID.randomUUID().toString();
        }
        updateToken(tokenRet, username);
        return tokenRet;
    }

    @Override
    public String getUsernameByToken(String token) {
        return redisUtils.getValue(CHEMMSTOOLS_USER + token);
    }

    private void updateToken(String token, String username) {
        redisUtils.setValue(CHEMMSTOOLS_USER_TOKEN + username, token, TOKEN_OVER_TIME_SECONDS);
        redisUtils.setValue(CHEMMSTOOLS_USER + token, username, TOKEN_OVER_TIME_SECONDS);
    }

}
