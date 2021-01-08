package com.gpfarms.fruitstand.sales.catalog.category.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gpfarms.fruitstand.sales.catalog.FindProductsFromCategory;
import com.gpfarms.fruitstand.sales.catalog.category.Uri;
import com.gpfarms.fruitstand.sales.catalog.product.Products;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final class FindProductsFromCategoryJdbc implements FindProductsFromCategory {

	private final @NonNull JdbcTemplate jdbcTemplate;

	@Override
	public Products byUri(Uri categoryUri) {
		// TODO Auto-generated method stub
		return null;
	}
}