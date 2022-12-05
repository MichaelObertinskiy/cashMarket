package com.example.cashMarket.service;

import com.example.cashMarket.domain.Commodity;
import com.example.cashMarket.repo.CommodityRepo;
import com.example.cashMarket.service.helpers.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelService {
    @Autowired
    CommodityRepo commodityRepo;


    public void save(MultipartFile file) {
        try {
            List<Commodity> commodities = ExcelHelper.excelToCommodities(file.getInputStream());
            updateFields(commodities);
//            System.out.println(commodities.size());
//            commodityRepo.saveAll(commodities);
//            commodityRepo.saveAll(updateFields(commodities));
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Commodity> getAllCommodities() {
        return commodityRepo.findAll();
    }

    public void updateFields(List<Commodity> commoditiesFromExcel) {
        for(Commodity commodityEx: commoditiesFromExcel) {
            Commodity commodityFromDB = commodityRepo.getCommodityByBarcode(commodityEx.getBarcode());
            if(commodityFromDB != null){
                commodityEx.setId(commodityFromDB.getId());
                commodityEx.setUuid(commodityFromDB.getUuid());
                commodityRepo.save(commodityEx);
            } else {
                commodityEx.setUuid(UUID.randomUUID());
                commodityEx.setCreatedAt(LocalDateTime.now());
                commodityRepo.save(commodityEx);
            }
        }
    }

     public boolean compaireValues(String valueEx, String valueDB) {
        return false;
     }
}
