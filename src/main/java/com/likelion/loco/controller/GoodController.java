package com.likelion.loco.controller;

import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.GoodService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/like")
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @PostMapping("/{userIdx}/promotion/{promotionIdx}")
    public BaseResponseStatus createGood (@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx){
        try{
            return goodService.createGood(userIdx,promotionIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    @GetMapping("/promotion/{promotionIdx}")
    public Integer getCountAllGood(@PathVariable("promotionIdx") Long promotionIdx){
        try{
            return goodService.getCountGood(promotionIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{userIdx}/promotion/{promotionIdx}")
    public Boolean getMyIsChecked(@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx){
        try{
            return goodService.goodIsChecked(userIdx, promotionIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/{userIdx}/promotion/{promotionIdx}")
    public void getDeleteIsChecked(@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx){
        try{
            goodService.deleteGood(userIdx, promotionIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
