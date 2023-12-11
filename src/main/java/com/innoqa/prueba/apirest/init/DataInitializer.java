package com.innoqa.prueba.apirest.init;

import com.innoqa.prueba.apirest.model.Price;
import com.innoqa.prueba.apirest.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PriceRepository priceRepository;

    @Autowired
    public DataInitializer(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) {
        // Crear los objetos Price iniciales
        List<Price> initialPrices = Arrays.asList(
                new Price(1, "2020-06-14T00:00:00", "2020-12-31T23:59:59", 1, 35455, 0, 35.50, "EUR"),
                new Price(1, "2020-06-14T15:00:00", "2020-06-14T18:30:00", 2, 35455, 1, 25.45, "EUR"),
                new Price(1, "2020-06-15T00:00:00", "2020-06-15T11:00:00", 3, 35455, 1, 30.50, "EUR"),
                new Price(1, "2020-06-15T16:00:00", "2020-12-31T23:59:59", 4, 35455, 1, 38.95, "EUR")
        );

        // Guardar los objetos en la base de datos
        for (Price price : initialPrices) {
            priceRepository.save(price);
        }
    }
}
