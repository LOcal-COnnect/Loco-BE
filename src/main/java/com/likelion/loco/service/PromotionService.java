package com.likelion.loco.service;


import com.likelion.loco.dto.PromotionReq;
import com.likelion.loco.dto.PromotionRes;
import com.likelion.loco.entities.Promotion;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.global.BaseException;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.PromotionRepository;
import com.likelion.loco.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final SellerRepository sellerRepository;


    @Transactional
    public PromotionRes.PromotionCreateRes createPromotion(PromotionReq.PromotionCreateReq req) throws BaseException {
        Seller seller = sellerRepository.findById(req.getSellerIdx())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_SELLER_NOT_FOUND));

        Promotion newPromotion = Promotion.builder()
                .seller(seller)
                .promotionTitle(req.getPromotionTitle())
                .promotionContent(req.getPromotionContent())
                .viewCount(0) // Initial view count
                .build();
        Promotion promotion = promotionRepository.save(newPromotion);
        return new PromotionRes.PromotionCreateRes(promotion);
    }

    @Transactional
    public void updatePromotion(Long promotionIdx, PromotionReq.PromotionUpdateReq updateReq) {
        Promotion promotion = promotionRepository.findByPromotionIdx(promotionIdx);
        promotion.setPromotionTitle(updateReq.getPromotionTitle());
        promotion.setPromotionContent(updateReq.getPromotionContent());
        promotionRepository.save(promotion);
    }

    @Transactional(readOnly = true)
    public PromotionRes.PromotionDetailRes getPromotionDetail(Long promotionIdx) {
        Promotion promotion = promotionRepository.findById(promotionIdx)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found"));

        return new PromotionRes.PromotionDetailRes(promotion.getPromotionTitle(), promotion.getPromotionContent(),
                promotion.getViewCount(), promotion.getGoods().size(), promotion.getSeller().getStore().getStoreName(),
                promotion.getComments());
    }



    public Page<PromotionRes.PromotionListRes> getPromotionList(Pageable page) {
        Page<Promotion> promotions = promotionRepository.findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort()));

        return promotions.map(promotion -> {
            PromotionRes.PromotionListRes promotionListRes = new PromotionRes.PromotionListRes();
            promotionListRes.setPromotionIdx(promotion.getPromotionIdx());
            promotionListRes.setCreatedAt(promotion.getCreatedAt());
            promotionListRes.setViewCount(promotion.getViewCount());
            promotionListRes.setGoods(promotion.getGoods().size());
            promotionListRes.setPromotionTitle(promotion.getPromotionTitle());
            promotionListRes.setPromotionContent(promotion.getPromotionContent());
            promotionListRes.setStoreName(promotion.getSeller().getStore().getStoreName());

            return promotionListRes;
        });
    }

    //마이페이지 홍보게시글 조회
    public List<PromotionRes.PromotionMyListRes> getMyPromotionList(Long sellerIdx) {
        Seller seller = sellerRepository.findById(sellerIdx).get();
        List<Promotion> promotions = promotionRepository.findAllBySeller(seller);

        return promotions.stream()
                .map(promotion -> {
                    PromotionRes.PromotionMyListRes promotionMyListRes = new PromotionRes.PromotionMyListRes();
                    promotionMyListRes.setPromotionIdx(promotion.getPromotionIdx());
                    promotionMyListRes.setCreatedAt(promotion.getCreatedAt());
                    promotionMyListRes.setPromotionTitle(promotion.getPromotionTitle());
                    promotionMyListRes.setPromotionContent(promotion.getPromotionContent());

                    return promotionMyListRes;
                })
                .collect(Collectors.toList());
    }



    @Transactional
    public Promotion getPromotionById(Long promotionIdx) throws BaseException{
        return promotionRepository.findById(promotionIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_PROMOTION_NOT_FOUND));
    }

    @Transactional
    public void increaseViewCount(Long promotionIdx) throws BaseException{
        Promotion promotion = promotionRepository.findById(promotionIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_PROMOTION_NOT_FOUND));

        promotion.setViewCount(promotion.getViewCount() + 1);
        promotionRepository.save(promotion);
    }


}
