package com.savvas.jobapp.controller;

import com.savvas.jobapp.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    @GetMapping("/ping")
    public String getBob() {
        return "Hello world!";
    }
}
