package com.smallclover.nullpointerexception.controller.functiontest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @Author: Amadeus
 * @Date: 2020/07/28 21:45
 */
@RestController
@RequestMapping("/test/cache")
public class TestController {

    @RequestMapping("")
    public ResponseEntity index(){
        return null;
    }
}
