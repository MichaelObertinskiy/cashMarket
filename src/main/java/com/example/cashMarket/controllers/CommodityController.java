package com.example.cashMarket.controllers;

import com.example.cashMarket.domain.Commodity;
import com.example.cashMarket.domain.PriceList;
import com.example.cashMarket.exception.CommodityNotFoundException;
import com.example.cashMarket.exception.PriceListNotFoundException;
import com.example.cashMarket.repo.CommodityRepo;
import com.example.cashMarket.repo.PriceListRepo;
import com.example.cashMarket.service.helpers.JSONString;
import com.example.cashMarket.service.impl.PriceListService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class CommodityController {
    @Autowired
    private CommodityRepo commodityRepo;
    @Autowired
    private PriceListRepo priceListRepo;
    @Autowired
    private PriceListService priceListService;

    @GetMapping("price/{uuid}")
    public ResponseEntity<List<Commodity>> getAllCommodityByPriceListUuid(
            @PathVariable(value = "uuid") String uuid
    ) {
        PriceList priceList = priceListRepo.findByUuid(uuid);
        if(!priceListRepo.existsByUuid(uuid)){
            throw new PriceListNotFoundException(uuid);
        }
        List<Commodity> commodities = priceList.getCommodities();
        return new ResponseEntity<>(commodities, HttpStatus.OK);
    }

    @PostMapping("/price/{uuid}")
    public ResponseEntity<PriceList> addCommodity(
            @PathVariable(value = "uuid") String uuid,
            @RequestBody String request
    ) {
        Gson requestFromFront = new Gson();
        JSONString jsonRequest = requestFromFront.fromJson(request, (Type) JSONString.class);
        Commodity commodityRequest = commodityRepo.getCommodityByBarcode(jsonRequest.getBarcode());
        PriceList _priceList = priceListRepo.findByUuid(uuid);
        if(commodityRequest.getId() == null) {
            throw new CommodityNotFoundException();
        }
        if(_priceList.getId() == null) {
            throw new PriceListNotFoundException(uuid);
        }
        _priceList.addCommodity(commodityRequest);

        return new ResponseEntity<>(
                priceListService
                        .changeCountOfCommodity(_priceList,
                                commodityRequest,
                                jsonRequest.getCount()),
                HttpStatus.OK);
    }

    @PutMapping("/price/{uuid}")
    public ResponseEntity<PriceList> updateCommodityInPriceList(
            @PathVariable(value = "uuid") String uuid,
            @RequestBody String request
    ) {
        Gson requestFromFront = new Gson();
        JSONString jsonRequest = requestFromFront.fromJson(request, (Type) JSONString.class);
        Commodity updatedCommodity = commodityRepo.getCommodityByBarcode(jsonRequest.getBarcode());
        PriceList _priceList = priceListRepo.findByUuid(uuid);
        return new ResponseEntity<>(priceListRepo
                .save(priceListService
                        .changeCountOfCommodity(_priceList, updatedCommodity, jsonRequest.getCount())),
                HttpStatus.OK);
    }

    @DeleteMapping("/price/{uuid}")
    public ResponseEntity<Object> deleteCommodityFromPriceList(
            @PathVariable(value = "uuid") String uuid,
            @RequestBody Commodity deletedCommodity) {
        PriceList _priceList = priceListRepo.findByUuid(uuid);
        if(_priceList == null) {
            throw new PriceListNotFoundException(uuid);
        }
        if(!_priceList.getCommodities().contains(deletedCommodity)) {
            throw new CommodityNotFoundException();
        }
        _priceList.removeCommodity(deletedCommodity.getId());
        priceListRepo.save(priceListService.updatePriceList(_priceList));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
