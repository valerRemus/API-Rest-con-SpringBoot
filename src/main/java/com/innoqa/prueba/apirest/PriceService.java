package com.innoqa.prueba.apirest;

import com.innoqa.prueba.apirest.model.Price;
import com.innoqa.prueba.apirest.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public Price findProductByDate(String date, int productId, int brandId) {
        LocalDateTime searchDate = LocalDateTime.parse(date);

        List<Price> prices = priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandId(
                searchDate, searchDate, productId, brandId);

        return prices.stream()
                .sorted(Comparator.comparingInt(Price::getPriority).reversed())
                .filter(price -> isWithinDateRange(searchDate, price))
                .findFirst()
                .orElse(null);
    }

    // Método auxiliar para verificar si la fecha de un precio está dentro del rango
    private boolean isWithinDateRange(LocalDateTime searchDate, Price price) {
        LocalDateTime startDate = price.getStartDate();
        LocalDateTime endDate = price.getEndDate();
        return !searchDate.isBefore(startDate) && !searchDate.isAfter(endDate);
    }
}
