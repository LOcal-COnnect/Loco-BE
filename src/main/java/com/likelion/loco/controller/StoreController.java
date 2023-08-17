package com.likelion.loco.controller;

import com.likelion.loco.dto.StoreRes;
import com.likelion.loco.service.StoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/stores")
public class StoreController {
    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("{storeDetailIdx}")
    public StoreRes.StoreAllGetRes storeAllGetRes(@PathVariable("storeDetailIdx") Long storeIdx){
        try{
            return storeService.getAllInfo(storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
