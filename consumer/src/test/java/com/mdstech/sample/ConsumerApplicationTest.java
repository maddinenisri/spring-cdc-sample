package com.mdstech.sample;

import com.mdstech.sample.bo.Product;
import com.mdstech.sample.controller.ConsumerController;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.mdstech.sample:provider:1.0-SNAPSHOT:stubs")
public class ConsumerApplicationTest {

    @StubRunnerPort("provider") int producerPort;

    @Autowired
    ConsumerController consumerController;

//    @Rule
//    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
//            .downloadStub("com.mdstech.sample", "provider", "1.0-SNAPSHOT", "stubs")
//            .withPort(8100)
//            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Before
    public void setUp() {
        consumerController.setPort(producerPort);
    }

    @Test
    public void get_product_from_service_contract() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Product> personResponseEntity = restTemplate.getForEntity("http://localhost:"+producerPort+"/product/1", Product.class);

        // then:
        BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
        BDDAssertions.then(personResponseEntity.getBody().getName()).isEqualTo("30 Year Fixed");

        Product product = consumerController.getById(1L);
        System.out.println(product);
    }
}
