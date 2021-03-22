package com.an.gradlespringboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_shop")
@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue
    private Long id;

    private String shopName;

    private String shopAddress;

    private String shopType;

    private String shopLocationLat;

    private String shopLocationLng;
}
