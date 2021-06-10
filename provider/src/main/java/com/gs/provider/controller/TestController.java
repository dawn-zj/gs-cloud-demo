package com.gs.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${server.port:8080}")
    private int port;

    @GetMapping("/index")
    public String index() {
        return "Hello World! Port: " + port;
    }
}
