package com.mdstech.sample.service;

import com.mdstech.sample.bo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Long, Product> products = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        products.put(1L, new Product(1L, "30 Year Fixed"));
        products.put(2L, new Product(2L, "20 Year Fixed"));
        products.put(3L, new Product(3L, "15 Year Fixed"));
    }

    @Override
    public Product getById(Long id) {
        return products.get(id);
    }
}
