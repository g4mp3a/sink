package org.gp.dataingestionservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataIngestionServiceConfiguration {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {

    return builder.build();
  }
}
