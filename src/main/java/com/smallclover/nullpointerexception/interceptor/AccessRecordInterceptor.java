package com.smallclover.nullpointerexception.interceptor;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.smallclover.nullpointerexception.service.visit.VisitService;
import com.smallclover.nullpointerexception.util.IPAddressUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/6/9 20:57
 */
@Component
@Slf4j
@AllArgsConstructor
public class AccessRecordInterceptor implements HandlerInterceptor {
    public static final String URL_ATTRIBUTE = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
    private VisitService visitService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IPAddressUtils.getIpAddress(request);
        String uri = request.getRequestURI();
        if (uri.contains("/article/detail/")){
            final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(URL_ATTRIBUTE);
            String articleId = pathVariables.get("id");
            visitService.build("blog");
            if (!visitService.isVisitToday(ip, articleId)){
                visitService.addIpAccessRecordForArticle(ip, articleId);
                visitService.addArticleAccessRecord(articleId);
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
