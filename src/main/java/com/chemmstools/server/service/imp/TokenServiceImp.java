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

    private final int TOKEN_OVER_TIME_SECONDS=600;

    @Override
    public boolean authToken(String token) {
        if(StringUtils.isBlank(token)){
            return false;
        }
        if(redisUtils.getValue("CHEMMSTOOLSUSER"+token)!=null){
            updateToken(token,redisUtils.getValue("CHEMMSTOOLSUSER"+token));
            return true;
        }
        return false;
    }

    @Override
    public String getToken(String username) {
        if(StringUtils.isBlank(username)){
            return "";
        }
        String tokenRet="";
        if(redisUtils.getValue("CHEMMSTOOLSUSERTOKEN"+username)!=null){
            tokenRet=redisUtils.getValue("CHEMMSTOOLSUSERTOKEN"+username);
        }else {
            tokenRet= UUID.randomUUID().toString();
        }
        updateToken(tokenRet,username);
        return tokenRet;
    }

    private void updateToken(String token,String username){
        redisUtils.setValue("CHEMMSTOOLSUSERTOKEN"+username,token,TOKEN_OVER_TIME_SECONDS);
        redisUtils.setValue("CHEMMSTOOLSUSER"+token,username,TOKEN_OVER_TIME_SECONDS);
    }

}
