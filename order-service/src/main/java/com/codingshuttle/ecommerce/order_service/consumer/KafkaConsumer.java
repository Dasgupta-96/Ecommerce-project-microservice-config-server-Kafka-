package com.codingshuttle.ecommerce.order_service.consumer;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

  @KafkaListener(topics = "product-topic")
  public void displayingProductInfo(String message) {
  log.info("getting msg's from product service.......... {}", message);
  }

  @KafkaListener(topics = "product-details-topic")
  public void displayingProductDetails(OrderRequestDto orderRequestDto) {
    log.info("getting orderRequestDto object from product service.......... {}", orderRequestDto);
  }


}
