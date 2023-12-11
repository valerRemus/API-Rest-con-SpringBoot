package com.innoqa.prueba.apirest;

import com.innoqa.prueba.apirest.model.Price;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class TestsPrecios {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        testEndpoint(restTemplate, "2020-06-14T10:00:00", 35455, 1, 35.50);
        testEndpoint(restTemplate, "2020-06-14T16:00:00", 35455, 1, 25.45);
        testEndpoint(restTemplate, "2020-06-14T21:00:00", 35455, 1, 35.50); // No hay información para este escenario
        testEndpoint(restTemplate, "2020-06-15T10:00:00", 35455, 1, 30.50);
        testEndpoint(restTemplate, "2020-06-16T21:00:00", 35455, 1, 38.95);
    }

    private static void testEndpoint(RestTemplate restTemplate, String date, int productId, int brandId, double expectedPrice) {
        String url = "http://localhost:8080/api/prices/byParameters?date=" + date +
                "&productId=" + productId + "&brandId=" + brandId;

        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        System.out.println("Test for date " + date + " - Status code: " + response.getStatusCode());

        if (response.getBody() != null) {
            Price price = response.getBody();
            System.out.println("Price received: " + price.getPrice());

            if (price.getPrice() == expectedPrice) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed! Expected price: " + expectedPrice);
            }
        } else {
            System.out.println("No price information received!");
        }

        System.out.println("------------------------------------");
    }
}

