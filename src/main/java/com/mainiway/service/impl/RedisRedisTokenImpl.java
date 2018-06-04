package com.mainiway.service.impl;

import com.mainiway.bean.po.TokenModel;
import com.mainiway.service.RedisTokenService;
import com.mainiway.config.Constants;
import com.mainiway.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangxin
 * Date: Created in 2018/05/25 23:14
 * Copyright: Copyright (c) 2018
 * Description： 通过 Redis 存储和验证 token 的实现类
 */
@Component
public class RedisRedisTokenImpl implements RedisTokenService {

    private RedisTemplate<String, String> redis;

    @Autowired
    public void setRedis(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redis = redisTemplate;
    }

    public TokenModel createToken(String userName){
        String token = userName + "@" + MD5Util.toMD5(userName);
        TokenModel model = new TokenModel(userName, token);
        // 存储到 redis 并设置过期时间
        redis.boundValueOps(userName).set(token, Constants.TOKEN_EXPIRED, TimeUnit.SECONDS);
        return model;
    }

    public TokenModel getToken(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        String[] param = token.split("@");
        if (param.length != 2) {
            return null;
        }
        // 使用 userName 和源 token 简单拼接成的 token，可以增加加密措施
        String userName = param[0];
        return new TokenModel(userName, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserName()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
        redis.boundValueOps(model.getUserName()).expire(Constants.TOKEN_EXPIRED, TimeUnit.SECONDS);
        return true;
    }

    public void deleteToken(String userName) {
        redis.delete(userName);
    }
}