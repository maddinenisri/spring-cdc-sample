package com.mdstech.sample.controller;

import com.mdstech.sample.bo.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private final RestTemplate restTemplate;

    private int port;

    public ConsumerController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable("id") Long id) {
        System.out.println("Received Request: " + id);
        Product product = restTemplate.getForObject("http://localhost:"+port+"/product/{id}", Product.class, id);
        System.out.println(product);
        return product;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
