package com.example.cashMarket.service.impl;

import com.example.cashMarket.domain.PriceList;
import com.example.cashMarket.repo.CommodityRepo;
import com.example.cashMarket.repo.PriceListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PriceListService {
    @Autowired
    PriceListRepo priceListRepo;
    @Autowired
    CommodityRepo commodityRepo;

    public void createPriceList() {
        PriceList priceList = new PriceList();
        priceList.setUuid(UUID.randomUUID().toString());
        priceList.setCreatedAt(LocalDateTime.now());
        priceList.setAmountOfPositions(0);
        priceList.setWeight(0.00);
        priceList.setTotalPrice(0.00);
        priceListRepo.save(priceList);
    }

}
