package com.likelion.loco.controller;

import com.likelion.loco.entities.Store;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.StoreMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/mine")
public class StoreMineController {
    private final StoreMineService storeMineService;

    public StoreMineController(StoreMineService storeMineService) {
        this.storeMineService = storeMineService;
    }

    @PostMapping("/{userIdx}/store/{storeIdx}")
    public ResponseEntity<BaseResponseStatus> createStoreMine(@PathVariable("userIdx") Long userIdx, @PathVariable("storeIdx") Long storeIdx) {
        try {
            BaseResponseStatus responseStatus = storeMineService.createStoreMine(userIdx, storeIdx);
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
    //찜 갯수 조회
    @GetMapping("/store/{storeIdx}")
    public ResponseEntity<Integer> getCountAllStoreMine(@PathVariable("storeIdx") Long storeIdx) {
        try {
            Integer count = storeMineService.getCountStoreMine(storeIdx);
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
    //찜했는지 여부 확인
    @GetMapping("/{useridx}/store/{storeIdx}")
    public ResponseEntity<Boolean> getMyStoreMineIsChecked(@PathVariable("useridx") Long userIdx, @PathVariable("storeIdx") Long storeIdx) {
        try {
            Boolean isChecked = storeMineService.storeMineIsChecked(userIdx, storeIdx);
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
    //내 찜한 상점 목록 가져오기

    @GetMapping("/users/{userIdx}")
    public ResponseEntity<List<Store>> getAllMyStoreMineList(@PathVariable("userIdx") Long userIdx) {
        try {
            List<Store> storeList = storeMineService.getMyStoreMineList(userIdx);
            if (storeList != null) {
                return new ResponseEntity<>(storeList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{userIdx}/store/{storeIdx}")
    public ResponseEntity<Void> deleteMyMineStore(@PathVariable("userIdx") Long userIdx, @PathVariable("storeIdx") Long storeIdx) {
        try {
            if(storeMineService.deleteStoreMine(userIdx, storeIdx)){
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
