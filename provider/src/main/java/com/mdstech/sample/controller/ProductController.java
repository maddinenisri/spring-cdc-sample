package com.mdstech.sample.controller;

import com.mdstech.sample.bo.Product;
import com.mdstech.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }
}
