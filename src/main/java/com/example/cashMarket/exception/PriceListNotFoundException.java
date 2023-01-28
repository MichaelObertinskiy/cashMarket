package com.example.cashMarket.exception;

public class PriceListNotFoundException extends RuntimeException{
    public PriceListNotFoundException(String uuid){
        super("Not found your Price List with uuid - " + uuid);
    }
}
