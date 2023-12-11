package com.innoqa.prueba.apirest.controller;

import com.innoqa.prueba.apirest.PriceService;
import com.innoqa.prueba.apirest.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/byParameters")
    public Price findProductByDate(@RequestParam("date") String date,
                                   @RequestParam("productId") int productId,
                                   @RequestParam("brandId") int brandId) {
        return priceService.findProductByDate(date, productId, brandId);
    }
}
