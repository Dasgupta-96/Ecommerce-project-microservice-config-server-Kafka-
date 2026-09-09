package com.codingshuttle.ecommerce.order_service.consumer;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_service.service.TwillioSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsConsumer {

  private final TwillioSmsService smsService;

  @KafkaListener(topics = "sms-notific-topic")
  public void sendSms(OrderRequestDto orderRequestDto) {
    log.info("getting mobile number as object from product service.......... {}", orderRequestDto.getMobileNumber());
    smsService.sendSms(orderRequestDto.getMobileNumber(), orderRequestDto.getMessages());
  }
}
