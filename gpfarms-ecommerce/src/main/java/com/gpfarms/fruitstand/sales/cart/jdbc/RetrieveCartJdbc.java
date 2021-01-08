package com.gpfarms.fruitstand.sales.cart.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gpfarms.fruitstand.sales.cart.Cart;
import com.gpfarms.fruitstand.sales.cart.CartId;
import com.gpfarms.fruitstand.sales.cart.RetrieveCart;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RetrieveCartJdbc implements RetrieveCart {

    private final @NonNull JdbcTemplate jdbcTemplate;

    @Override
    public Cart byId(CartId cartId) {
        return new CartJdbc(cartId, jdbcTemplate);
    }
}