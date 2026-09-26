package com.cinebook.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello CineBook";
    }

    @GetMapping("/hello/{name}")
    public  String helloName(@PathVariable String name){
        return  "Hello, " + name;
    }

}