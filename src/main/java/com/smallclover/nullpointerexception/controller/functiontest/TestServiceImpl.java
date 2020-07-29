package com.smallclover.nullpointerexception.controller.functiontest;

import com.smallclover.nullpointerexception.dto.SiteAccessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Amadeus
 * @Date: 2020/07/29 21:37
 */
@Service
public class TestServiceImpl {

    private final RedisTemplate<String, Object> redisTemplate;

    public TestServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void addPageView(SiteAccessDto siteAccessDto){
        ListOperations<String, Object> listOp = redisTemplate.opsForList();
        String key = siteAccessDto.getDate().toLocalDate().toString();
    }
}
