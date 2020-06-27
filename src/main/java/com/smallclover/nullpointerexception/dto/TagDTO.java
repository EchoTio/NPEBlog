package com.smallclover.nullpointerexception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Amadeus
 * @Date: 2020/6/24 21:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    private String word;
    private int count;
}
