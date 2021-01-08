package com.gpfarms.fruitstand.sales.catalog.category.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gpfarms.fruitstand.sales.cart.item.ProductId;
import com.gpfarms.fruitstand.sales.catalog.FindProducts;
import com.gpfarms.fruitstand.sales.catalog.product.Product;
import com.gpfarms.fruitstand.sales.catalog.product.Products;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class FindProductsJdbc implements FindProducts {

	private final @NonNull JdbcTemplate jdbcTemplate;

	@Override
	public Products all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product byId(ProductId id) {
		// TODO Auto-generated method stub
		return null;
	}

}
