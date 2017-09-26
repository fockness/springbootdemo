package com.example.springbootdemo.controller;

import com.example.springbootdemo.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/he")
public class HelloController {

//    @Value("${cupSize}")
//    private String cupSize;
//
//    @Value("${age}")
//    private Integer age;
//
//    @Value("${content}")
//    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public Integer say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id){
        return id;
    }
}
