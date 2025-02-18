package com.example.middleware_interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/time")
    public String getCurrentTime() {
        return new Date().toString();
    }
}
