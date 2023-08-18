package com.likelion.loco.service;

import com.likelion.loco.dto.ProductReq;
import com.likelion.loco.dto.StoreReq;
import com.likelion.loco.dto.StoreRes;
import com.likelion.loco.entities.Product;
import com.likelion.loco.entities.Review;
import com.likelion.loco.entities.Seller;
import com.likelion.loco.entities.Store;
import com.likelion.loco.global.BaseException;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

    private final SellerRepository sellerRepository;

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final StoreMineRepository storeMineRepository;

    public StoreRes.StoreCreateRes createStore(StoreReq.StoreCreateReq req) throws BaseException {
        Seller seller = sellerRepository.findById(req.getSellerIdx())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_SELLER_NOT_FOUND));

        Store newStore = Store.builder()
                .seller(seller)
                .storeName(req.getStoreName())
                .storeLocation(req.getStoreLocation())
                .storePhone(req.getStorePhone())
                .storeTel(req.getStoreTel())
                .storeDesc(req.getStoreDesc())
                .category(req.getCategory())
                .businessNumber(req.getBusinessNumber())
                .build();

        Store store = storeRepository.save(newStore); // Store 저장

        List<Product> productList = new ArrayList<>();
        for (ProductReq.ProductCreateReq productReq : req.getProductList()) {
            Product product = new Product();
            product.setStore(store); // Store에 매핑
            product.setProductName(productReq.getProductName());
            product.setProductPrice(productReq.getProductPrice());
            productList.add(product);
        }
        productRepository.saveAll(productList); // Product 저장

        return new StoreRes.StoreCreateRes(store);
    }

    @Transactional
    public StoreRes.StoreCreateRes updateStore(Long storeIdx, StoreReq.StoreUpdateReq req) throws BaseException {
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.POST_STORE_NOT_FOUND));

        if (req.getStoreName() != null) {
            store.setStoreName(req.getStoreName());
        }
        if (req.getStoreLocation() != null) {
            store.setStoreLocation(req.getStoreLocation());
        }
        if (req.getStorePhone() != null) {
            store.setStorePhone(req.getStorePhone());
        }
        if (req.getStoreTel() != null) {
            store.setStoreTel(req.getStoreTel());
        }
        if (req.getStoreDesc() != null) {
            store.setStoreDesc(req.getStoreDesc());
        }
        if (req.getCategory() != null) {
            store.setCategory(req.getCategory());
        }
            // ProductList 업데이트
            List<Product> productList = new ArrayList<>();
            if (req.getProductList() != null) {
                for (ProductReq.ProductCreateReq productReq : req.getProductList()) {
                    Product product = new Product();
                    product.setStore(store); // Store에 매핑
                    product.setProductName(productReq.getProductName());
                    product.setProductPrice(productReq.getProductPrice());
                    productList.add(product);
                }
            }
            productRepository.saveAll(productList); // Product 저장

            storeRepository.save(store);

            return new StoreRes.StoreCreateRes(store);
        }


    public Page<StoreRes.StoreListRes> getStoreListByName(String storeName, Pageable pageable) {
        Page<Store> stores = storeRepository.findByStoreNameContaining(storeName, pageable);

        return stores.map(store -> {
                    StoreRes.StoreListRes storeListRes = new StoreRes.StoreListRes();
                    storeListRes.setStoreIdx(store.getStoreIdx());
                    storeListRes.setStoreName(store.getStoreName());
                    storeListRes.setStoreLocation(store.getStoreLocation());

                    return storeListRes;
                });
    }

    public Page<StoreRes.StoreListRes> getStoreListByCategory(String category, Pageable pageable) {
        String actualCategory;
        switch (category) {
            case "food1": actualCategory = "곡물류"; break;
            case "food2": actualCategory = "김치류"; break;
            case "food3": actualCategory = "발효식품"; break;
            case "food4": actualCategory = "면류"; break;
            case "food5": actualCategory = "떡류"; break;
            case "food6": actualCategory = "생선과 해산물"; break;
            case "food7": actualCategory = "과일"; break;
            case "food8": actualCategory = "차류"; break;
            case "food9": actualCategory = "과자류"; break;
            case "food10": actualCategory = "한약류"; break;
            case "nonfood1": actualCategory = "의류와 직물"; break;
            case "nonfood2": actualCategory = "도자기와 세라믹"; break;
            case "nonfood3": actualCategory = "목공예품"; break;
            case "nonfood4": actualCategory = "기타 공예품"; break;
            case "nonfood5": actualCategory = "화장품"; break;
            case "nonfood6": actualCategory = "금속 공예품"; break;
            default: actualCategory = category;
        }
        Page<Store> stores = storeRepository.findByCategory(actualCategory, pageable);

        return stores.map(store -> {
            StoreRes.StoreListRes storeListRes = new StoreRes.StoreListRes();
            storeListRes.setStoreIdx(store.getStoreIdx());
            storeListRes.setStoreName(store.getStoreName());
            storeListRes.setStoreLocation(store.getStoreLocation());

            return storeListRes;
        });
    }

    public StoreRes.StoreAllGetRes getAllInfo(Long storeIdx){
        Store store = storeRepository.findById(storeIdx).get(); //store객체
        List<Review> reviewList = reviewRepository.findReviewsByStore(store); //리뷰전체 가져오기;
        Integer storeMineCount  = storeMineRepository.countByStore(store); //찜한갯수
        Float avgReview = reviewRepository.avgReview(storeIdx);

        return new StoreRes.StoreAllGetRes(store,storeMineCount,avgReview,reviewList);
    }





}
