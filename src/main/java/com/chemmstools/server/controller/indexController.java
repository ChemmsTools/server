package com.chemmstools.server.controller;

import com.chemmstools.server.annotations.AuthToken;
import com.chemmstools.server.beans.AuthorizeParams;
import com.chemmstools.server.beans.ResultMessage;
import com.chemmstools.server.beans.User;
import com.chemmstools.server.service.TokenService;
import com.chemmstools.server.service.UserService;
import com.chemmstools.server.utils.RedisUtils;
import com.chemmstools.server.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ResultUtils resultUtils;
    @PostMapping("/login")
    @CrossOrigin
    public ResultMessage login(@RequestBody AuthorizeParams authorizeParams){
        if(userService.authUserUserNameAndPassword(authorizeParams.getUsername(),authorizeParams.getPassword())){
            return resultUtils.sendResult("200","",tokenService.getToken(authorizeParams.getUsername()));
        }
        return resultUtils.sendResult("400","账号或密码错误","");
    }

    @PostMapping("/islogin")
    @CrossOrigin
    @AuthToken
    public ResultMessage isLogin(@RequestBody AuthorizeParams authorizeParams){
        return resultUtils.sendResult("200","",authorizeParams.getToken());
    }
}
