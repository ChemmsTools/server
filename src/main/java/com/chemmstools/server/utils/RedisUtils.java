package com.chemmstools.server.utils;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisUtils() {

    }

    public void setValue(String key, String value) {
        redisTemplate.boundValueOps(key).set(value);
    }


    public void setValue(String key, String value, int seconds) {
        redisTemplate.boundValueOps(key).set(value, seconds, TimeUnit.SECONDS);
    }

    public String getValue(String key){
        return (String) redisTemplate.boundValueOps(key).get();
    }
}
