package com.example.cashMarket.repo;

import com.example.cashMarket.domain.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepo extends JpaRepository<PriceList, Integer> {
}
