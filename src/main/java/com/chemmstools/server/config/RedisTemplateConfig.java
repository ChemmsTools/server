package com.chemmstools.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {
    /**
     * redisTemplate配置
     * 序列化的几种方式:
     * OxmSerializer
     * ByteArrayRedisSerializer
     * GenericJackson2JsonRedisSerializer
     * GenericToStringSerializer
     * StringRedisSerializer
     * JdkSerializationRedisSerializer
     * Jackson2JsonRedisSerializer
     *
     * @param redisConnectionFactory redis连接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
