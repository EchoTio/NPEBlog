package com.smallclover.nullpointerexception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: Amadeus
 * @Date: 2020/07/28 21:40
 */
@Data
@AllArgsConstructor
public class SiteAccessDto {
    private String ip;
    private String url;
    private LocalDate date;
}
