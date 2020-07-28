package com.smallclover.nullpointerexception.interceptor;

import com.smallclover.nullpointerexception.controller.functiontest.SimpleCache;
import com.smallclover.nullpointerexception.dto.SiteAccessDto;
import com.smallclover.nullpointerexception.util.IPAddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/07/28 21:27
 */
@Component
@Slf4j
public class SiteAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IPAddressUtils.getIpAddress(request);

        String uri = request.getRequestURI();
        LocalDate today = LocalDate.now();
        var siteAccessDto = new SiteAccessDto(ip, uri, today);
        SimpleCache.simpleCache.put(today, siteAccessDto);
        return true;
    }
}
