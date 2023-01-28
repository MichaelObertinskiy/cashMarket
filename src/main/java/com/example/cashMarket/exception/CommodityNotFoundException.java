package com.example.cashMarket.exception;

public class CommodityNotFoundException extends RuntimeException{
    public CommodityNotFoundException () {
        super ("Commodity not found");
    }
}
