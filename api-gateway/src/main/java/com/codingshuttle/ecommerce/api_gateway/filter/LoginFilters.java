package com.codingshuttle.ecommerce.api_gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * Gateway filters: LoginFilters is a custom filter for security checks, validate jwt
 * JWT validation can be done at the Gateway level, but authorization must ALWAYS be enforced at the microservice level.
 */
@Slf4j
@Component
public class LoginFilters extends AbstractGatewayFilterFactory<LoginFilters.Config> {

  public LoginFilters() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
  return (exchange, chain) -> {
  log.debug("Order filters pre: {}", exchange.getRequest().getURI());
    String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization"); // retrieve JWT token

    if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {

       exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
    String token = tokenHeader.split("Bearer ")[1];

    // TODO: Validate Jwt token and get User details

    // from token i will get the userId using Jwts.parser()
//    exchange.getRequest()
//      .mutate()
//      .header("userId", userId).build(); // sending this userId to downstream microservices and fetch te userId using @ReqHeader

    return chain.filter(exchange);
  };
  }

  public static class Config {

  }
}
