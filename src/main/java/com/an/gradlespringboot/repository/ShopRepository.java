package com.an.gradlespringboot.repository;

import com.an.gradlespringboot.entity.Shop;
import com.an.gradlespringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {

    // 自定义查询
    @Query("from Shop shop where shop.shopName=:name")
    Shop findByShopName(@Param("name") String name);
}
