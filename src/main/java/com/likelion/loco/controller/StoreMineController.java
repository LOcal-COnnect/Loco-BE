package com.likelion.loco.controller;

import com.likelion.loco.entities.Store;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.StoreMineService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponseStatus createStoreMine (@PathVariable("userIdx") Long userIdx, @PathVariable("storeIdx") Long storeIdx){
        try{
            return storeMineService.createStoreMine(userIdx,storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    //찜 갯수 조회
    @GetMapping("/store/{storeIdx}")
    public Integer getCountAllStoreMine(@PathVariable("storeIdx") Long storeIdx){
        try{
            return storeMineService.getCountStoreMine(storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //찜했는지 여부 확인
    @GetMapping("/{useridx}/store/{storeIdx}")
    public Boolean getMyStoreMineIsChecked(@PathVariable("useridx") Long userIdx, @PathVariable("storeIdx") Long storeIdx){
        try{
            return storeMineService.storeMineIsChecked(userIdx, storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //내 찜한 상점 목록 가져오기
    @GetMapping("/users/{userIdx}")
    public List<Store> getAllMyStoreMineList(@PathVariable("userIdx") Long userIdx){
        try{
            return storeMineService.getMyStoreMineList(userIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/{userIdx}/store/{storeIdx}")
    public void deleteMyMineStore(@PathVariable("userIdx") Long userIdx, @PathVariable("storeIdx") Long storeIdx){
        try{
            storeMineService.deleteStoreMine(userIdx,storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
