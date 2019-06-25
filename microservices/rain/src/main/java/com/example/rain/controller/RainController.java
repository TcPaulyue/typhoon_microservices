package com.example.rain.controller;

import com.example.rain.domain.Rain;
import com.example.rain.service.RainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RainController {
    @Autowired
    private RainService rainService;

    @GetMapping(value = "/rain")
    public Rain getRain() {
        return rainService.getRain();
    }

}