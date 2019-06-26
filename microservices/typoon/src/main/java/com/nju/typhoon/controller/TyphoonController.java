package com.nju.typhoon.controller;

import com.nju.typhoon.domain.Typhoon;
import com.nju.typhoon.service.TyphoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TyphoonController {
    @Autowired
    private TyphoonService typhoonService;

    @GetMapping(value = "/typhoon")
    public Typhoon getTyphoon() {
        return typhoonService.getTyphoon();
    }
}
