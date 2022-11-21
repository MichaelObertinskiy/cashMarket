package com.example.cashMarket.controllers;

import com.example.cashMarket.domain.PriceList;
import com.example.cashMarket.repo.PriceListRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pricelist")
public class PriceListController {

    private final PriceListRepo priceListRepo;

    @Autowired
    public PriceListController(PriceListRepo priceListRepo) {
        this.priceListRepo = priceListRepo;
    }

    @GetMapping("{id}")
    public PriceList getOne(@PathVariable("id") PriceList priceList) {
        return priceList;
    }

    @PostMapping
    public PriceList create(@RequestBody PriceList priceList) {
        priceList.setCreatedAt(LocalDateTime.now());
        return priceListRepo.save(priceList);
    }

//    @PutMapping("{id}")
//    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
//        BeanUtils.copyProperties(message, messageFromDB, "id");
//        return messageRepo.save(messageFromDB);
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Message message) {
//        messageRepo.delete(message);
//    }
}
