package com.gpfarms.fruitstand.sales.cart.jdbc;

import java.util.List;

import com.gpfarms.fruitstand.sales.cart.Cart;
import com.gpfarms.fruitstand.sales.cart.CartId;
import com.gpfarms.fruitstand.sales.cart.item.CartItem;
import com.gpfarms.fruitstand.sales.cart.item.ProductId;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class CartJdbc implements Cart {

    private final @NonNull CartId id;

    private final @NonNull JdbcTemplate jdbcTemplate;

    private List<CartItem> items;
    
	@Override
	public CartId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItem> items() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(CartItem toAdd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(ProductId productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void empty() {
		// TODO Auto-generated method stub

	}

}
