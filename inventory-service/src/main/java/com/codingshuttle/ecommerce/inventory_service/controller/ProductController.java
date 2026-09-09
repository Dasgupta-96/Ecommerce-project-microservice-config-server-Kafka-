package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Value("${my.variable}")
    private String myVariable;

    private final ProductService productService;
    private final KafkaTemplate kafkaTemplate;

    private final OrdersFeignClient ordersFeignClient;

//    @Value("${kafka.topic.product-topic}")
//    private String PRODUCT_INFO_TOPIC;

    @GetMapping("/fetchOrders")
    public String fetchFromOrdersService(HttpServletRequest httpServletRequest) {

        log.info(httpServletRequest.getHeader("x-custom-header"));

//        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();

//        return restClient.get()
//                .uri(orderService.getUri()+"/orders/core/helloOrders")
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.helloOrders();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory() {
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id) {
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("reduce-stocks")
    public ResponseEntity<Double> calculateProductPrice(@RequestBody OrderRequestDto orderRequestDto) {
        log.info("getting variable data from dev profile: {}", myVariable);
        Double totalPrice = productService.calculateProductPrice(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }

    // sending product details to order service using kafka
    @PostMapping("/send-data/{msg}")
    public ResponseEntity<?> sendProductInfo(@PathVariable String msg) {
//        kafkaTemplate.send(PRODUCT_INFO_TOPIC, msg);
        return ResponseEntity.ok(msg);
    }


}
