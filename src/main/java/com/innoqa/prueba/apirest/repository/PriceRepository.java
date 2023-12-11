package com.innoqa.prueba.apirest.repository;

import com.innoqa.prueba.apirest.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(
            LocalDateTime startDate, LocalDateTime endDate, int productId, int brandId);

}
