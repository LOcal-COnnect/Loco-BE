package com.likelion.loco.controller;

import com.likelion.loco.service.StoreMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/mine")
public class StoreMineController {
    private final StoreMineService storeMineService;

    public StoreMineController(StoreMineService storeMineService) {
        this.storeMineService = storeMineService;
    }


}
