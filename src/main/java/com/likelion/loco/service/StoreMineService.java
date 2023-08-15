package com.likelion.loco.service;

import com.likelion.loco.entities.Good;
import com.likelion.loco.entities.Store;
import com.likelion.loco.entities.StoreMine;
import com.likelion.loco.global.BaseResponseStatus;
import com.likelion.loco.repository.StoreMineRepository;
import com.likelion.loco.repository.StoreRepository;
import com.likelion.loco.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public BaseResponseStatus createStoreMine(Long userIdx,Long storeIdx){
        try{
            storeMineRepository.save(StoreMine.builder()
                    .user(userRepository.findByUserIdx(userIdx).get())
                    .store(storeRepository.findById(storeIdx).get())
                    .build()
            );
            return BaseResponseStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //찜한 갯수
    public Integer getCountStoreMine(Long storeIdx){
        try{
            Integer count = storeMineRepository.countByStore(storeRepository.findById(storeIdx).get());

            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //내가 찜한 리스트 가져오기
    public List<Store> getMyStoreMineList(Long userIdx){
        try{
            List<StoreMine> storeMineList = storeMineRepository.findStoreMinesByUser(userRepository.findByUserIdx(userIdx).get());
            List<Store> storeList = new ArrayList<>();


            for(int i = 0; i< storeMineList.size(); i++){
                storeList.add(storeMineList.get(i).getStore());
            }
            return storeList;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    //찜한 여부 확인
    public Boolean storeMineIsChecked(Long userIdx, Long storeIdx){
        try{
            Optional<StoreMine> storeMine = storeMineRepository.findStoreMineByUserAndStore(userRepository.findByUserIdx(userIdx).get(),storeRepository.findById(storeIdx).get());
            if(storeMine.isPresent()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void deleteStoreMine(Long userIdx, Long storeIdx){
        try{
            StoreMine storeMine = storeMineRepository.findStoreMineByUserAndStore(userRepository.findByUserIdx(userIdx).get(),storeRepository.findById(storeIdx).get()).get();
            storeMineRepository.deleteById(storeMine.getStoreMineIdx());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
