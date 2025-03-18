package com.app.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/login")
    public Boolean login(@RequestParam String id, @RequestParam String password) {
        return "1".equals(id) && "123".equals(password);
    }
}
