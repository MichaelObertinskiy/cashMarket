package com.example.cashMarket.repo;

import com.example.cashMarket.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepo extends JpaRepository<Commodity, Integer> {
    Commodity getCommodityByBarcode(String barcode);
}
