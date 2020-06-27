package com.smallclover.nullpointerexception.service.visit;


import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/6/3 21:34
 */
public interface VisitService {

    void addIpAccessRecordForArticle(String ip, String id);
    void addArticleAccessRecord(String id);
    boolean isVisitToday(String ip, String id);
    Map<Object, Object> getIpAccessRecordForArticleAll();
    Map<Object, Object> getArticleAccessRecord();
    VisitService build(String appName);
}
