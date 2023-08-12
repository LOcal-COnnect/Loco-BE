package com.likelion.loco.service;

import com.likelion.loco.repository.StoreMineRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreMineService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreMineRepository storeMineRepository;


    public StoreMineService(StoreRepository storeRepository, UserRepository userRepository, StoreMineRepository storeMineRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.storeMineRepository = storeMineRepository;
    }

    public void createStoreMine(Long userIdx,Long storeIdx){

    }
    public void getMyStoreMine(Long userIdx){


    }
    public void deleteStoreMine(Long storeMineIdx){
        try{
            storeMineRepository.deleteById(storeMineIdx);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
