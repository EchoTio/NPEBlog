package com.smallclover.nullpointerexception.controller.functiontest;

import com.smallclover.nullpointerexception.dto.SiteAccessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Amadeus
 * @Date: 2020/07/29 21:37
 */
@Service
public class TestServiceImpl {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void addPageView(SiteAccessDto siteAccessDto){
        HashOperations hashOp = redisTemplate.opsForHash();
        String date = siteAccessDto.getDate().toLocalDate().toString();
        String page = siteAccessDto.getUrl();
        String ip = siteAccessDto.getIp();
        String key = date + page;
        if (Objects.isNull(hashOp.get(key, ip))){
            //
            hashOp.put(key, ip, 1);
            // pv
            hashOp.increment("pv-" + date, page, 1);
            // uv
            hashOp.put("uv-" + date, page, 1);
        }else {
            // pv
            hashOp.increment("pv-" + date, page, 1);
        }
    }
}
