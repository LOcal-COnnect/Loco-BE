package com.likelion.loco.controller;

import com.likelion.loco.dto.StoreReq;
import com.likelion.loco.dto.StoreRes;
import com.likelion.loco.global.BaseException;
import com.likelion.loco.global.BaseResponse;
import com.likelion.loco.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("")
    public BaseResponse<StoreRes.StoreCreateRes> createStore(@RequestBody StoreReq.StoreCreateReq req) throws BaseException {
        return new BaseResponse<>(storeService.createStore(req));
    }

    @PatchMapping("/{storeIdx}")
    public BaseResponse<StoreRes.StoreCreateRes> updateStore(
            @PathVariable Long storeIdx,
            @RequestBody StoreReq.StoreUpdateReq req) throws BaseException {
        StoreRes.StoreCreateRes updatedStore = storeService.updateStore(storeIdx, req);
        return new BaseResponse<>(updatedStore);
    }

    @GetMapping("/{storeName}")
    public BaseResponse<Page<StoreRes.StoreListRes>> getStoresByName(
            @PathVariable String storeName,
            @PageableDefault(page = 0, size = 8) Pageable page) {

        Page<StoreRes.StoreListRes> storeListRes = storeService.getStoreListByName(storeName, page);
        return new BaseResponse<>(storeListRes);
    }

    @GetMapping("/category/{category}")
    public BaseResponse<Page<StoreRes.StoreListRes>> getStoresByCategory(
            @PathVariable String category,
            @PageableDefault(page = 0, size = 8) Pageable page) {

        Page<StoreRes.StoreListRes> storeListRes = storeService.getStoreListByCategory(category, page);
        return new BaseResponse<>(storeListRes);
    }

    @GetMapping("/detail/{storeIdx}")
    public StoreRes.StoreAllGetRes storeAllGetRes(@PathVariable("storeIdx") Long storeIdx){
        try{
            return storeService.getAllInfo(storeIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
