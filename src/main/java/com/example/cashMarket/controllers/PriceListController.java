package com.example.cashMarket.controllers;

import com.example.cashMarket.domain.PriceList;
import com.example.cashMarket.domain.User;
import com.example.cashMarket.exception.PriceListNotFoundException;
import com.example.cashMarket.repo.PriceListRepo;
import com.example.cashMarket.repo.UserRepo;
import com.example.cashMarket.service.impl.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PriceListController {
    @Autowired
    private PriceListRepo priceListRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PriceListService priceListService;


    @Autowired
    public PriceListController(PriceListRepo priceListRepo) {
        this.priceListRepo = priceListRepo;
    }

    @GetMapping("/")
    public String main (Model model) {
        return "index";
    }

    @GetMapping("price/{uuid}")
    public ResponseEntity<PriceList> getPriceListByUuid (@PathVariable ("uuid") String uuid) {
        PriceList priceList = priceListRepo.findByUuid("uuid");
        if(priceList == null) {
            throw new PriceListNotFoundException(uuid);
        }
        return new ResponseEntity<>(priceList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PriceList> create(@RequestBody User user) {
        PriceList _priceList;
        User userFromDb = userRepo.findByCode(user.getCode());
        _priceList = priceListService.createPriceList(userFromDb);
        return new ResponseEntity<>(_priceList, HttpStatus.CREATED);
    }
}
