package com.smallclover.nullpointerexception.controller.functiontest;

import com.smallclover.nullpointerexception.dto.SiteAccessDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/07/28 21:50
 */
@Component
public class SimpleCache {
    public static Map<LocalDate, SiteAccessDto> simpleCache = new HashMap<>();
}
