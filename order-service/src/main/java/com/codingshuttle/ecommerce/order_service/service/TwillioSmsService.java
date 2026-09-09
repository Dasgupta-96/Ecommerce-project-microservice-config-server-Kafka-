package com.codingshuttle.ecommerce.order_service.service;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@Slf4j
public class TwillioSmsService {

  @Value("${twilio.account-sid}")
  private String accSid;

  @Value("${twilio.auth-token}")
  private String authToken;

  @Value("${twilio.from-number}")
  private String fromNumber;

  @PostConstruct
  public void init() { // It initializes the Twilio SDK with your credentials one time
    Twilio.init(accSid, authToken);
  }

  public void sendSms(String to, String message) {
    Message.creator(
      new PhoneNumber(to),
      new PhoneNumber(fromNumber),
      message
    ).create();

    log.info("Sent sms to the ph number: {}", to);
  }

}
