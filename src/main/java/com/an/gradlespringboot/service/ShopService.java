package com.an.gradlespringboot.service;

import com.an.gradlespringboot.entity.Shop;
import com.an.gradlespringboot.repository.ShopRepository;
import com.an.gradlespringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopService {

    @Resource
    private ShopRepository shopRepository;

    public Shop getShopByName(String name){
        return shopRepository.findByShopName(name);
    }
}
