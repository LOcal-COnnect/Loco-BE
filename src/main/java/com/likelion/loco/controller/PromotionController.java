package com.likelion.loco.controller;

import com.likelion.loco.dto.PromotionReq;
import com.likelion.loco.dto.PromotionRes;
import com.likelion.loco.global.BaseException;
import com.likelion.loco.global.BaseResponse;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping("")
    public BaseResponse<PromotionRes.PromotionCreateRes> createPromotion(@RequestBody PromotionReq.PromotionCreateReq req) throws BaseException {
        return new BaseResponse<>(promotionService.createPromotion(req));
    }

    @PatchMapping("/{promotionIdx}")
    public BaseResponse<BaseResponseStatus> updatePromotion(@PathVariable Long promotionIdx, @RequestBody PromotionReq.PromotionUpdateReq updateReq) {
        promotionService.updatePromotion(promotionIdx, updateReq);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/{promotionIdx}")
    public BaseResponse<PromotionRes.PromotionDetailRes> getPromotionDetail(@PathVariable Long promotionIdx) throws  BaseException {
        promotionService.increaseViewCount(promotionIdx);
        return new BaseResponse<>(promotionService.getPromotionDetail(promotionIdx));
    }

    @GetMapping("")
    public BaseResponse<Page<PromotionRes.PromotionListRes>> getPromotionList(@PageableDefault(page = 0, size = 8) Pageable page) {
        Page<PromotionRes.PromotionListRes> promotionListRes = promotionService.getPromotionList(page);

        return new BaseResponse<>(promotionListRes);
    }

    @GetMapping("/my/{sellerIdx}")
    public BaseResponse<List<PromotionRes.PromotionMyListRes>> getMyPromotionList(@PathVariable Long sellerIdx) {
        List<PromotionRes.PromotionMyListRes> promotionMyListRes = promotionService.getMyPromotionList(sellerIdx);

        return new BaseResponse<>(promotionMyListRes);
    }




}
