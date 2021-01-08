package com.gpfarms.fruitstand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.gpfarms.fruitstand.common.events.EventPublisher;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
@EnableAsync
public class FruitstandEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitstandEcommerceApplication.class, args);
	}

	@Bean
	EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		return applicationEventPublisher::publishEvent;
	}

	@Bean
	LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
