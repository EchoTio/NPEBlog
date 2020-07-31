package com.smallclover.nullpointerexception.interceptor;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.smallclover.nullpointerexception.controller.functiontest.TestServiceImpl;
import com.smallclover.nullpointerexception.dto.SiteAccessDto;
import com.smallclover.nullpointerexception.util.IPAddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @Author: Amadeus
 * @Date: 2020/07/28 21:27
 */
@Component
@Slf4j
public class SiteAccessInterceptor implements HandlerInterceptor {

    @Autowired
    private TestServiceImpl testServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ip = IPAddressUtils.getIpAddress(request);

        String uri = request.getRequestURI();
        LocalDateTime today = LocalDateTime.now();
        var siteAccessDto = new SiteAccessDto(ip, uri, today);
        testServiceImpl.addPageView(siteAccessDto);
        return true;
    }
}
