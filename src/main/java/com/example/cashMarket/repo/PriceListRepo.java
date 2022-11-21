package com.example.cashMarket.repo;

import com.example.cashMarket.domain.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepo extends JpaRepository<PriceList, Long> {
}
