package com.mdstech.sample;

import com.mdstech.sample.controller.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public abstract class BaseClass {

    @Autowired
    ProductController productController;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(productController);
    }
}
