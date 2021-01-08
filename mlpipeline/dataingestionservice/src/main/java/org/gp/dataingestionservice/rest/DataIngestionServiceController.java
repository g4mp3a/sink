package org.gp.dataingestionservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class DataIngestionServiceController {

  private static final String SOURCE_DATA_API_URL =
      "https://source-data-api-url";

  @Autowired
  private RestTemplate restTemplate;

  /**
   * If configuring Spring actuator endpoints is not desirable, then configure
   * this resource in k8s liveness and readiness probes
   *
   * @return
   */
  @GetMapping("/health")
  public String healthCheck() {

    return "200";
  }

  /**
   * Source pushes data to this endpoint.
   */
  @PostMapping("/push")
  public void push(@RequestBody String payload) {

  }

  /**
   * Endpoint to pull data from source.
   */
  @PostMapping("/pull")
  public void pull(
      @RequestParam(required = false, defaultValue = "0") Integer offset) {

    ResponseEntity<String> response =
        restTemplate.getForEntity(SOURCE_DATA_API_URL, String.class);

  }
}
