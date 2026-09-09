package com.codingshuttle.ecommerce.order_service.config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  //TODO: Zipkin is a tool it helps developers trace requests across diff parts of a distributed system
  //TODO: Its very useful for understanding how long its takes for a request to be processed where the bottle necks are
// sampling probability = 0.1 means we r tracing 10% of requests. tracing all te requests would costs lot of resources of the app
// as we r tracing start time, end time, app name, putting traceId, spanId as we will not get much info out of it
//TODO: Centralized logging -> Deep Log analysis, real time monitoring and alerting, centralized view for logs for easier debugging and troubleshooting
// TODO: ELK -> ElasticSearch, LogStash, Kibana

  @Bean
  public Capability capability(final MeterRegistry registry) {
    return new MicrometerCapability(registry);
  }
}
