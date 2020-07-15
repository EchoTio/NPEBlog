package com.smallclover.nullpointerexception.dto;

import lombok.Data;

import java.util.List;

/**
 * 关于DTO
 * @Author: Amadeus
 * @Date: 2020/7/15 21:33
 */
@Data
public class AboutDTO {
    private List<String> techStack;
    private String desc;
    private String address;
    private String favourite;
    private String character;
    private String future;
    private String wellKnown;
}
