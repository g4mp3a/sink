package com.gpfarms.fruitstand.sales.cart.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
class CartJdbcConfig {

    @Bean
    RetrieveCartJdbc retrieveCartJdbc(JdbcTemplate jdbcTemplate) {
        return new RetrieveCartJdbc(jdbcTemplate);
    }
}