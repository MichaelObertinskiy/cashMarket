package com.example.cashMarket.service.impl;

import com.example.cashMarket.domain.Commodity;
import com.example.cashMarket.repo.CommodityRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CommodityService {
    @Autowired
    private CommodityRepo commodityRepo;
    public Commodity getCommodityByBarcode(String barcode){
        return commodityRepo.getCommodityByBarcode(barcode);
    }

}
