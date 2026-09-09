package com.codingshuttle.ecommerce.inventory_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

  @Value("${kafka.topic.product-topic}")
  private String PRODUCT_INFO_TOPIC;

  @Value("${kafka.topic.product-details-topic}")
  private String PRODUCT_DETAILS;

  @Value("${kafka.topic.sms}")
  private String SMS_NOTIFICATION;

  @Bean
  public NewTopic getNewTopic() {
    return new NewTopic(PRODUCT_INFO_TOPIC, 3, (short) 2);

    // TODO: NOTE: here partition defines parallelism means multiple consumers can read in parallel
    // TODO: NOTE: more partition means more read/ write capacity means high throughput

    // 1 partition means: only one consumer can read from the consumer grp
    // 3 partition means: max 3 consumers can read in parallel

    // consumer 1 : partition 0
    // consumer 2 : partition 1
    // consumer 3 : partition 2

    // replication factor(no of replication factor = no of brokers)
    // TODO: each partition is copied to another broker (fault tolerance)
//      partition-0 → broker-1 (leader), broker-2 (replica)
//      partition-1 → broker-2 (leader), broker-3 (replica)
//      partition-2 → broker-3 (leader), broker-1 (replica)

//    if acks: all
    // TODO: if broker 2 goes down then in partition 0 broker 1 still leader
    // TODO: In partition-1 broker 3 becomes leader

  }

  @Bean
  public NewTopic getNewProductTopic() {
    return new NewTopic(PRODUCT_DETAILS, 3, (short) 2);
  }

  @Bean
  public NewTopic getNewSmsTopic() {
    return new NewTopic(SMS_NOTIFICATION, 3, (short) 2);
  }

}
