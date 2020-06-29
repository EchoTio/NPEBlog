package com.smallclover.nullpointerexception.service.visit.impl;

import com.smallclover.nullpointerexception.service.visit.VisitService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * @Author: Amadeus
 * @Date: 2020/6/3 21:35
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    // 如果直接使用Lombok会报错无法注入
    // @AllArgsConstructor注解会尝试注入该字段。
    private String articleCntKey;

    public void addIpAccessRecordForArticle(String ip, String id){
        redisTemplate.opsForHash().put(getDate(), ip+"_"+id, id);
    }

    public void addArticleAccessRecord(String id){
        redisTemplate.opsForHash().increment(articleCntKey, id, 1);
    }

    public boolean isVisitToday(String ip, String id){
        String articleId = (String) redisTemplate.opsForHash().get(getDate(), ip +"_" + id);
        if (StringUtils.isEmpty(articleId)){
            return false;
        }
        return true;
    }

    private String getDate(){
        return LocalDate.now().toString();
    }

    public Map<Object, Object> getIpAccessRecordForArticleAll(){
        Map<Object, Object> records = redisTemplate.opsForHash().entries(getDate());
        return records;
    }

    public Map<Object, Object> getArticleAccessRecord(){
        Map<Object, Object> records = redisTemplate.opsForHash().entries(articleCntKey);
        return records;
    }

    public VisitService build(String appName){
        articleCntKey = "article_cnt_" +appName;
        return this;
    }
}
