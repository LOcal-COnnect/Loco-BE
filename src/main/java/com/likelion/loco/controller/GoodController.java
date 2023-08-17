package com.likelion.loco.controller;

import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.GoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponseStatus> createGood(@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx) {
        try {
            BaseResponseStatus responseStatus = goodService.createGood(userIdx, promotionIdx);
            if (responseStatus != null && responseStatus.equals(BaseResponseStatus.SUCCESS)) {
                return new ResponseEntity<>(responseStatus, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/promotion/{promotionIdx}")
    public ResponseEntity<Integer> getCountAllGood(@PathVariable("promotionIdx") Long promotionIdx) {
        try {
            Integer count = goodService.getCountGood(promotionIdx);
            if (count != null) {
                return new ResponseEntity<>(count, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userIdx}/promotion/{promotionIdx}")
    public ResponseEntity<Boolean> getMyIsChecked(@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx) {
        try {
            Boolean isChecked = goodService.goodIsChecked(userIdx, promotionIdx);
            if (isChecked != null) {
                return new ResponseEntity<>(isChecked, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userIdx}/promotion/{promotionIdx}")
    public ResponseEntity<HttpStatus> getDeleteIsChecked(@PathVariable("userIdx") Long userIdx, @PathVariable("promotionIdx") Long promotionIdx) {
        try {
            if(goodService.deleteGood(userIdx, promotionIdx)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
