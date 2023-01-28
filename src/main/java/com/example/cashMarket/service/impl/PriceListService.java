package com.example.cashMarket.service.impl;

import com.example.cashMarket.domain.Commodity;
import com.example.cashMarket.domain.PriceList;
import com.example.cashMarket.domain.User;
import com.example.cashMarket.repo.CommodityRepo;
import com.example.cashMarket.repo.PriceListRepo;
import com.example.cashMarket.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PriceListService {
    @Autowired
    PriceListRepo priceListRepo;
    @Autowired
    CommodityRepo commodityRepo;
    @Autowired
    UserRepo userRepo;

    public PriceList createPriceList(User user) {
        String uuid = UUID.randomUUID().toString();
        PriceList priceList = new PriceList(
                uuid,
                user,
                0.00,
                0,
                0.00,
                LocalDateTime.now()
        );
        return priceListRepo.save(priceList);
    }

    public PriceList updatePriceList(PriceList priceList) {


        List<Commodity> commodities = priceList.getCommodities();
        double totalPrice = commodities
                .stream()
                .mapToDouble(Commodity::getCost)
                .sum();

        double totalWeight = commodities
                .stream()
                .mapToDouble(Commodity::getWeight)
                .sum();

        priceList.setTotalPrice(totalPrice);
        priceList.setWeight(totalWeight);
        priceList.setAmountOfPositions(commodities.size());
        priceList.setUpdatedAt(LocalDateTime.now());
        priceListRepo.save(priceList);
        return priceList;
    }

    public PriceList changeCountOfCommodity(PriceList priceList, Commodity commodity, Integer count) {
        priceList.removeCommodity(commodity.getId());
        List<Commodity> commodities = priceList.getCommodities();
        for (int i = 0; i < count; i++) {
            commodities.add(commodity);
        }
        updatePriceList(priceList);
        return priceList;
    }

}
